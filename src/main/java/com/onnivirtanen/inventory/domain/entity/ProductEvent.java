package com.onnivirtanen.inventory.domain.entity;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public final class ProductEvent implements Entity {

    private final UUID productEventId;
    private final ProductEventType eventType;
    private final LocalDateTime timestamp;

    public ProductEvent(UUID productEventId, ProductEventType eventType) {
        validate(productEventId, eventType);

        this.productEventId = productEventId;
        this.eventType = eventType;
        this.timestamp = LocalDateTime.now();
    }

    private static void validate(UUID productEventId, ProductEventType eventType) {
        if (productEventId == null || eventType == null) {
            throw new IllegalArgumentException("Product event cannot have null fields.");
        }
    }

    public enum ProductEventType {
        PRODUCT_CREATED("New product added to the inventory."),
        PRICE_CHANGED("Price updated."),
        CATEGORY_CHANGED("Category updated."),
        EXPIRATION_DATE_UPDATED("Expiration or 'best by' date for product set or updated.");

        private final String description;

        ProductEventType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
