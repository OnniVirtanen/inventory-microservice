package com.onnivirtanen.inventory.infrastructure.inmemory;

import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.model.valueobject.EANBarcode;
import com.onnivirtanen.inventory.domain.port.out.InventoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * TODO
 */
public class InMemoryInventoryRepository implements InventoryRepository {

    private final Map<UUID, Product> products = new HashMap<>();

    @Override
    public void save(Product product) {

    }

    @Override
    public void deleteById(UUID productId) {

    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        return Optional.empty();
    }

    @Override
    public void update(Product product) {

    }

    @Override
    public boolean productExistsByEAN(EANBarcode eanBarcode) {
        return false;
    }
}
