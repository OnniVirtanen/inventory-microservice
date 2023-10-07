package com.onnivirtanen.inventory.domain.entity;

import lombok.Getter;

import java.util.Objects;
import java.util.UUID;

@Getter
public class Category implements Entity {

    private UUID categoryId;
    private final CategoryName name;

    public Category(CategoryName name) {
        validate(name);

        this.name = name;
    }

    private static void validate(CategoryName name) {
        if (name == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(categoryId, category.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryId);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", name=" + name +
                '}';
    }

    public enum CategoryName {
        GROCERIES,
        FRESH_PRODUCE,
        MEAT_AND_POULTRY,
        DAIRY,
        BAKERY,
        FROZEN_FOODS,
        BEVERAGES,
        ALCOHOLIC_DRINKS,
        HEALTH_AND_BEAUTY,
        HOUSEHOLD_CLEANING,
        ELECTRONICS,
        CLOTHING,
        FOOTWEAR,
        SPORTS_AND_LEISURE,
        TOYS_AND_GAMES,
        KITCHENWARE,
        FURNITURE,
        GARDEN_AND_OUTDOORS,
        AUTOMOTIVE,
        PET_CARE,
        STATIONERY,
        BOOKS_AND_MAGAZINES,
        PHARMACY,
        BABY_PRODUCTS,
        JEWELRY_AND_ACCESSORIES;
    }
}
