package com.daw.finalProject.kafka.producer;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.annotation.PreDestroy;

public class KafkaProducerUtil {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerUtil.class);
    private static KafkaProducer<String, String> producer;

    static {
        try {
            Properties props = new Properties();
            String bootstrapServers = System.getenv("KAFKA_BOOTSTRAP_SERVERS");
            if (bootstrapServers == null || bootstrapServers.isEmpty()) {
                bootstrapServers = "localhost:9092"; // Valor por defecto
                logger.warn("KAFKA_BOOTSTRAP_SERVERS no está definido. Usando localhost:9092 por defecto.");
            } else {
                logger.info("Configurando KafkaProducer con bootstrap servers: {}", bootstrapServers);
            }
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

            producer = new KafkaProducer<>(props);
        } catch (Exception e) {
            logger.error("Error al inicializar KafkaProducer", e);
            throw new ExceptionInInitializerError(e);
        }
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