package com.onnivirtanen.inventory.infrastructure.jpa;

import com.onnivirtanen.inventory.domain.entity.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryEntity {

    @Id
    private UUID categoryId;
    private String name;

    public static CategoryEntity fromDomainObject(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity();

        categoryEntity.setCategoryId(category.getCategoryId());
        categoryEntity.setName(category.getName().toString());

        return categoryEntity;
    }

    public Category toDomainObject() {
        Category.CategoryName categoryNameEnum = Category.CategoryName.valueOf(this.name);

        return new Category(
                this.categoryId,
                categoryNameEnum
        );
    }
}