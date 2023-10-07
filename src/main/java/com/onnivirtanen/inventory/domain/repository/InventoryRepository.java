package com.onnivirtanen.inventory.domain.repository;

import com.onnivirtanen.inventory.domain.aggregate.Product;

import java.util.UUID;

public interface InventoryRepository {
    void save(Product product);

    void deleteById(UUID id);
}
