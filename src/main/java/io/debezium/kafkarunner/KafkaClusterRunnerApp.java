/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.debezium.kafkarunner;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

public class KafkaClusterRunnerApp implements QuarkusApplication {

    private static final String WAIT_FOR_EXIT_PROPERTY = "WAIT_FOR_EXIT";

    @Override
    public int run(String... args) throws Exception {
        if (Boolean.parseBoolean(System.getenv(WAIT_FOR_EXIT_PROPERTY))) {
            Quarkus.waitForExit();
        }
        return 0;
    }
}
