package com.onnivirtanen.inventory.domain.event;

import com.onnivirtanen.inventory.domain.model.aggregate.Product;

public class ProductRestockedEvent extends ProductEvent {

    public ProductRestockedEvent(Product product, String message) {
        super(EventType.UPDATE, product, message);
    }
}
