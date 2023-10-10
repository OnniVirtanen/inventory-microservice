package com.onnivirtanen.inventory.domain.event;

import com.onnivirtanen.inventory.domain.model.aggregate.Product;

public class MarkProductMissingEvent extends ProductEvent {
    public MarkProductMissingEvent(Product product, String message) {
        super(EventType.UPDATE, product, message);
    }
}
