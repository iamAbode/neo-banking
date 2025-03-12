package com.neo.account.service.impl;

import com.neo.account.service.NotificationService;
import com.neo.common.event.AccountCreationEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * @Author ABODE
 * @Date 2025/03/09 4:27 PM
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaNotificationService implements NotificationService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String bootstrapServers = "localhost:9092";

    @Override
    @Async
    public void sendMessage(String topic, Object message) {
        if (isKafkaAvailable()) {
            log.info("Start - Sending accountCreationEvent {} to Kafka topic account-creation", message);
            kafkaTemplate.send(topic, message);
            log.info("End - Complete accountCreationEvent {} to Kafka topic account-creation", message);
        } else {
            log.info("Kafka is unavailable. Message not sent.");
        }
        
    }

    private boolean isKafkaAvailable() {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        try (AdminClient adminClient = AdminClient.create(properties)) {
            adminClient.listTopics().names().get(); // Try fetching topic names
            return true; // Kafka is available
        } catch (Exception e) {
            return false; // Kafka is not available
        }
    }
}
