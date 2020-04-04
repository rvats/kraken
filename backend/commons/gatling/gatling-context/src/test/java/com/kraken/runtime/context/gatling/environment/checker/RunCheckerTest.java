package com.kraken.runtime.context.gatling.environment.checker;

import com.google.common.collect.ImmutableMap;
import com.kraken.runtime.entity.task.TaskType;
import com.kraken.tests.utils.TestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.kraken.tools.environment.KrakenEnvironmentKeys.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {RunChecker.class})
public class RunCheckerTest {

  @Autowired
  RunChecker checker;

  @Test
  public void shouldTest() {
    assertThat(checker.test(TaskType.GATLING_RUN)).isTrue();
    assertThat(checker.test(TaskType.GATLING_DEBUG)).isFalse();
    assertThat(checker.test(TaskType.GATLING_RECORD)).isFalse();
  }


  @Test(expected = NullPointerException.class)
  public void shouldFailCheck() {
    checker.accept(ImmutableMap.of());
  }

  @Test
  public void shouldSucceed() {
    final var env = ImmutableMap.<String, String>builder()
        .put(KRAKEN_GATLING_SIMULATION_NAME.name(), "value")
        .put(KRAKEN_INFLUXDB_URL.name(), "value")
        .put(KRAKEN_INFLUXDB_DATABASE.name(), "value")
        .put(KRAKEN_INFLUXDB_USER.name(), "value")
        .put(KRAKEN_INFLUXDB_PASSWORD.name(), "value")
        .put(KRAKEN_STORAGE_URL.name(), "value")
        .build();
    checker.accept(env);
  }

  @Test
  public void shouldTestUtils() {
    TestUtils.shouldPassNPE(RunChecker.class);
  }
}