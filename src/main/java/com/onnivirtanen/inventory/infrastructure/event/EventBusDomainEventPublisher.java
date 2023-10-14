package com.onnivirtanen.inventory.infrastructure.event;

import com.onnivirtanen.inventory.domain.event.DomainEvent;
import com.onnivirtanen.inventory.domain.port.out.DomainEventPublisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EventBusDomainEventPublisher implements DomainEventPublisher {

    private final Logger logger = LoggerFactory.getLogger(EventBusDomainEventPublisher.class);

    @Override
    public void publish(DomainEvent<?> event) {
        logger.info(event.toString());
    }
}
