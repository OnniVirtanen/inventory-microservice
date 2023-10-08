package com.onnivirtanen.inventory.domain.entity;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public final class ProductEvent implements Entity {

    private final UUID productEventId;
    private final ProductEventType eventType;
    private final LocalDateTime timestamp;

    public ProductEvent(UUID productEventId, ProductEventType eventType, LocalDateTime timestamp) {
        validate(eventType);

        this.productEventId = productEventId;
        this.eventType = eventType;
        this.timestamp = timestamp;
    }

    private static void validate(ProductEventType eventType) {
        if (eventType == null) {
            throw new IllegalArgumentException("Product event cannot be null.");
        }
    }

    public enum ProductEventType {
        PRODUCT_CREATED("New product added to the inventory."),
        PRICE_CHANGED("Price updated."),
        CATEGORY_CHANGED("Category updated."),
        EXPIRATION_DATE_UPDATED("Expiration or 'best by' date for product set or updated."),
        PRODUCT_RESTOCKED("Product restocked.");

        private final String description;

        ProductEventType(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }
}
