package com.onnivirtanen.inventory.infrastructure.jpa.entity;


import com.onnivirtanen.inventory.domain.model.entity.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID categoryId;
    private String name;

    public CategoryEntity(Category value) {
        if (value.getCategoryId() != null) {
            this.categoryId = value.getCategoryId();
        }
        this.name = value.getName().toString();
    }
}
