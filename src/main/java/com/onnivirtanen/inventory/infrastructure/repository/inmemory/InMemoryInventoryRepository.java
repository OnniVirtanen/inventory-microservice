package com.onnivirtanen.inventory.infrastructure.repository.inmemory;

import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.model.valueobject.EANBarcode;
import com.onnivirtanen.inventory.domain.port.out.InventoryRepository;

import java.lang.reflect.Field;
import java.util.*;

public class InMemoryInventoryRepository implements InventoryRepository {

    private final Map<UUID, Product> products = new HashMap<>();

    @Override
    public Product save(Product product) {
        assignIdIfNotPresent(product);
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public void deleteById(UUID productId) {
        products.remove(productId);
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        return Optional.ofNullable(products.get(productId));
    }

    @Override
    public Product update(Product product) {
        if (product.getId() == null) {
            throw new IllegalArgumentException("Product ID cannot be null");
        }
        products.put(product.getId(), product);
        return product;
    }

    @Override
    public boolean productExistsByEAN(EANBarcode eanBarcode) {
        return products.values().stream().anyMatch(product -> eanBarcode.equals(product.getBarcode()));
    }

    private void assignIdIfNotPresent(Product product) {
        assignIdToEntity(product, Product.class, "id");

        if (product.getCategory() != null) {
            assignIdToEntity(product.getCategory(), product.getCategory().getClass(), "categoryId");
        }
    }

    private void assignIdToEntity(Object entity, Class<?> clazz, String fieldName) {
        try {
            Field idField = clazz.getDeclaredField(fieldName);
            idField.setAccessible(true);
            if (idField.get(entity) == null) {
                idField.set(entity, UUID.randomUUID());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to set ID for class: " + clazz.getSimpleName(), e);
        }
    }
}
