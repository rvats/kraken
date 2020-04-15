package com.kraken.tools.environment;

import com.kraken.tools.obfuscation.ExcludeFromObfuscation;
import lombok.NonNull;

@ExcludeFromObfuscation
public enum KrakenEnvironmentKeys {
  JAVA_OPTS,
  KRAKEN_GATLING_LOGS_INFO,
  KRAKEN_GATLING_LOGS_DEBUG,

  KRAKEN_TASK_ID,
  KRAKEN_TASK_TYPE,
  KRAKEN_HOST_ID,
  KRAKEN_DESCRIPTION,
  KRAKEN_CONTAINER_LABEL,
  KRAKEN_EXPECTED_COUNT,
  KRAKEN_APPLICATION_ID,
  KRAKEN_USER_ID,

  KRAKEN_VERSION,
  KRAKEN_INFLUXDB_URL,
  KRAKEN_INFLUXDB_DATABASE,
  KRAKEN_INFLUXDB_USER,
  KRAKEN_INFLUXDB_PASSWORD,
  KRAKEN_ANALYSIS_URL,
  KRAKEN_RUNTIME_URL,
  KRAKEN_STORAGE_URL,

  KRAKEN_GATLING_JAVA_OPTS,
  KRAKEN_GATLING_SIMULATION_NAME,
  KRAKEN_GATLING_SIMULATION_CLASS_NAME,
  KRAKEN_GATLING_SIMULATION_PACKAGE_NAME,
  KRAKEN_GATLING_HAR_PATH_REMOTE,
  KRAKEN_GATLING_CONTAINER_NAME,
  KRAKEN_GATLING_CONTAINER_LABEL,
  KRAKEN_GATLING_SIDEKICK_NAME,
  KRAKEN_GATLING_SIDEKICK_LABEL,

  KRAKEN_SECURITY_URL,
  KRAKEN_SECURITY_REALM,
  KRAKEN_SECURITY_WEB_ID,
  KRAKEN_SECURITY_CONTAINER_ID,
  KRAKEN_SECURITY_CONTAINER_SECRET,
  KRAKEN_SECURITY_ACCESS_TOKEN,
  KRAKEN_SECURITY_REFRESH_TOKEN,
}
