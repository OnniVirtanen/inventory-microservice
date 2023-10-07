package com.onnivirtanen.inventory.infrastructure.jpa;

import com.onnivirtanen.inventory.domain.entity.ProductEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEventEntity {

    @Id
    private UUID productEventId;
    private ProductEvent.ProductEventType eventType;
    private LocalDateTime timestamp;

    public static List<ProductEventEntity> fromDomainObject(List<ProductEvent> productEvents) {
        List<ProductEventEntity> productEventEntities = new ArrayList<>();

        for (ProductEvent productEvent : productEvents) {
            ProductEventEntity productEventEntity = new ProductEventEntity();

            productEventEntity.setProductEventId(productEvent.getProductEventId());
            productEventEntity.setEventType(productEvent.getEventType());
            productEventEntity.setTimestamp(productEvent.getTimestamp());

            productEventEntities.add(productEventEntity);
        }

        return productEventEntities;
    }

    public ProductEvent toDomainObject() {
        return new ProductEvent(
                this.getProductEventId(),
                this.eventType
        );
    }
}
