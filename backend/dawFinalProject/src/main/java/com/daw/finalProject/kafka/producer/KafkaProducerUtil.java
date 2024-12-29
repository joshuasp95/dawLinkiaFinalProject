package com.daw.finalProject.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import jakarta.annotation.PreDestroy;

public class KafkaProducerUtil {

    private static KafkaProducer<String, String> producer;

    static {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "kafka:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        producer = new KafkaProducer<>(props);
    }

    public static KafkaProducer<String, String> getProducer() {
        return producer;
    }

    public static void closeProducer() {
        if (producer != null) {
            producer.close();
        }
    }

    @PreDestroy
    public static void shutdown() {
        closeProducer();
    }

}
