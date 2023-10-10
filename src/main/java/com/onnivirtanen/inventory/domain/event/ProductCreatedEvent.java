package com.onnivirtanen.inventory.domain.event;

import com.onnivirtanen.inventory.domain.model.aggregate.Product;

public class ProductCreatedEvent extends ProductEvent {

    public ProductCreatedEvent(Product product, String message) {
        super(EventType.CREATION, product, message);
    }
}
