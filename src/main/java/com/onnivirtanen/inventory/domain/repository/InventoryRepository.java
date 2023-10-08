package com.onnivirtanen.inventory.domain.repository;

import com.onnivirtanen.inventory.domain.aggregate.Product;
import com.onnivirtanen.inventory.domain.valueobject.EANBarcode;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository {

    void save(Product product);

    void deleteById(UUID productId);

    List<Product> findAll();

    Optional<Product> findById(UUID productId);

    void update(Product product);

    boolean productExistsByEAN(EANBarcode eanBarcode);
}
