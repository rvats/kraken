package com.kraken.runtime.context.environment;

import com.google.common.collect.ImmutableList;
import com.kraken.runtime.client.properties.RuntimeClientProperties;
import com.kraken.runtime.context.api.environment.EnvironmentPublisher;
import com.kraken.runtime.context.entity.ExecutionContextBuilder;
import com.kraken.runtime.context.entity.ExecutionContextBuilderTest;
import com.kraken.runtime.entity.environment.ExecutionEnvironmentEntry;
import com.kraken.tools.properties.api.ApplicationProperties;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class EnvironmentIntegrationTest {

  List<EnvironmentPublisher> publishers;
  BaseChecker checker;
  ExecutionContextBuilder contextBuilder;

  @Mock
  ApplicationProperties application;
  @Mock
  RuntimeClientProperties client;

  @Before
  public void before() {
    given(application.getVersion()).willReturn("version");
    given(client.getUrl()).willReturn("url");
    contextBuilder = ExecutionContextBuilderTest.EXECUTION_CONTEXT_BUILDER;
    publishers = ImmutableList.of(
        new ContextPublisher(),
        new HostIdsPublisher(),
        new KrakenVersionPublisher(application),
        new RuntimeUrlPublisher(client)
    );
    checker = new BaseChecker();
  }

  @Test
  public void shouldCheck() {
    final var published = Flux
        .fromIterable(this.publishers)
        .filter(publisher -> publisher.test(contextBuilder.getTaskType()))
        .reduce(contextBuilder, (context, publisher) -> publisher.apply(context)).block();
    assertThat(published).isNotNull();
    assertThat(checker.test(published.getTaskType())).isTrue();
    final var map = published.getEntries().stream()
        .filter(entry -> entry.getScope().equals("") || entry.getScope().equals("hostId"))
        .collect(Collectors.toMap(ExecutionEnvironmentEntry::getKey, ExecutionEnvironmentEntry::getValue));
    checker.accept(map);
  }

}
