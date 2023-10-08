package com.onnivirtanen.inventory.infrastructure.jpa.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product_event")
public class ProductEventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productEventId;
    private String eventType;
    private LocalDateTime timestamp;
}
