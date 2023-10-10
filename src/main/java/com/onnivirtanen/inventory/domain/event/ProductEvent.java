package com.onnivirtanen.inventory.domain.event;

import com.onnivirtanen.inventory.domain.event.DomainEvent;
import com.onnivirtanen.inventory.domain.model.aggregate.Product;

public class ProductEvent extends DomainEvent<Product> {
    protected ProductEvent(EventType eventType, Product product, String message) {
        super(eventType, product, message);
    }
}
