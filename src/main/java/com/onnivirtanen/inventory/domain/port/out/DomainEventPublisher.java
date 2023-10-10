package com.onnivirtanen.inventory.domain.port.out;

import com.onnivirtanen.inventory.domain.event.DomainEvent;

public interface DomainEventPublisher {
    void publish(DomainEvent<?> event);
}
