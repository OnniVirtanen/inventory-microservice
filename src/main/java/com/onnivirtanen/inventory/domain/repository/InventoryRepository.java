package com.onnivirtanen.inventory.domain.repository;

import com.onnivirtanen.inventory.domain.aggregate.Product;

public interface InventoryRepository {
    void save(Product product);
}
