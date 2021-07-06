/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.debezium.kafkarunner;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import org.jboss.logging.Logger;

import io.debezium.kafka.KafkaCluster;
import io.debezium.util.Testing;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class KafkaClusterRunner {

    private static final Logger LOG = Logger.getLogger(KafkaClusterRunner.class);

    private KafkaCluster kafka;

    public void startKafka(@Observes StartupEvent se) throws IOException {
        LOG.info("Starting Kafka");

        File dataDir = Testing.Files.createTestingDirectory("kafka_test_cluster");
        Testing.Files.delete(dataDir);

        Properties props = new Properties();
        props.put("zookeeper.session.timeout.ms", "20000");

        kafka = new KafkaCluster().usingDirectory(dataDir)
                .deleteDataPriorToStartup(true)
                .deleteDataUponShutdown(true)
                .addBrokers(1)
                .withKafkaConfiguration(props)
                .withPorts(-1, 9092)
                .startup();
    }

    public void stopKafka(@Observes ShutdownEvent se) {
        if (kafka != null) {
            kafka.shutdown();
        }

        LOG.info("Stopped Kafka");
    }
}
