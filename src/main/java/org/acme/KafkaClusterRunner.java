package org.acme;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import io.debezium.kafka.KafkaCluster;
import io.debezium.util.Testing;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class KafkaClusterRunner {

	private KafkaCluster kafka;

	public void startKafka(@Observes StartupEvent se) throws IOException {
		System.out.println("Starting Kafka");

        File dataDir = Testing.Files.createTestingDirectory("history_cluster");
        Testing.Files.delete(dataDir);

        Properties props = new Properties();
//        props.put("auto.create.topics.enable", "false");
        props.put("zookeeper.session.timeout.ms", "20000");

        // Configure the extra properties to
        kafka = new KafkaCluster().usingDirectory(dataDir)
                .deleteDataPriorToStartup(true)
                .deleteDataUponShutdown(true)
                .addBrokers(1)
                .withKafkaConfiguration(props)
                .withPorts(-1, 9093)
                .startup();
	}

	public void stopKafka(@Observes ShutdownEvent se) {
        if (kafka != null) {
            kafka.shutdown();
        }

		System.out.println("Stopped Kafka");
	}
}
