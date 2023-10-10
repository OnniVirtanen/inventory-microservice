package com.onnivirtanen.inventory.infrastructure.event;

import com.onnivirtanen.inventory.domain.event.DomainEvent;
import com.onnivirtanen.inventory.domain.port.out.DomainEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher implements DomainEventPublisher {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public EventPublisher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publish(DomainEvent<?> event) {
        kafkaTemplate.send("product", event.toString());
    }
}
