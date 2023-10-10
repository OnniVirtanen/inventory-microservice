package com.onnivirtanen.inventory.domain.model.aggregate;

import com.onnivirtanen.inventory.domain.event.DomainEvent;

import java.util.List;
import java.util.UUID;

/**
 * Marker interface for aggregates
 */
public interface Aggregate {

    // getId() method is needed for events
    UUID getId();

    List<DomainEvent> getDomainEvents();

    void clearDomainEvents();
}
