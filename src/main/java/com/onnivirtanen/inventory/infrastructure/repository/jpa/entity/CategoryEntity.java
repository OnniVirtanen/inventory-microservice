package com.onnivirtanen.inventory.infrastructure.repository.jpa.entity;


import com.onnivirtanen.inventory.domain.model.entity.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "category")
public class CategoryEntity implements Serializable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryEntity that = (CategoryEntity) o;
        return categoryId.equals(that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }
}
