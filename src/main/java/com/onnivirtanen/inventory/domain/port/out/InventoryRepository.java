package com.onnivirtanen.inventory.domain.port.out;

import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.model.valueobject.EANBarcode;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InventoryRepository {

    Product save(Product product);

    void deleteById(UUID productId);

    List<Product> findAll();

    Optional<Product> findById(UUID productId);

    Product update(Product product);

    boolean productExistsByEAN(EANBarcode eanBarcode);
}
