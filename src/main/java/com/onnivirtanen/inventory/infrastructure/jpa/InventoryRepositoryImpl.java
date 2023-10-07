package com.onnivirtanen.inventory.infrastructure.jpa;

import com.onnivirtanen.inventory.domain.aggregate.Product;
import com.onnivirtanen.inventory.domain.repository.InventoryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class InventoryRepositoryImpl implements InventoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Product product) {
        entityManager.persist(ProductMapper.INSTANCE.toEntity(product));
    }

    @Override
    public void deleteById(UUID id) {

    }

}