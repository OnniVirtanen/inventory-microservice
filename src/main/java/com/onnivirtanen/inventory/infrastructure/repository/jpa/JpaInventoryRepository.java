package com.onnivirtanen.inventory.infrastructure.repository.jpa;

import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.port.out.InventoryRepository;
import com.onnivirtanen.inventory.domain.model.valueobject.EANBarcode;
import com.onnivirtanen.inventory.infrastructure.repository.jpa.entity.ProductEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class JpaInventoryRepository implements InventoryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = ProductEntityMapper.INSTANCE.toEntity(product);
        entityManager.persist(productEntity);
        return ProductEntityMapper.INSTANCE.toProduct(productEntity);
    }

    @Override
    public void deleteById(UUID productId) {
        ProductEntity productEntity = entityManager.find(ProductEntity.class, productId);
        if (productEntity != null) {
            entityManager.remove(productEntity);
        }
    }

    @Override
    public List<Product> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> cq = cb.createQuery(ProductEntity.class);
        Root<ProductEntity> rootEntry = cq.from(ProductEntity.class);
        CriteriaQuery<ProductEntity> all = cq.select(rootEntry);
        TypedQuery<ProductEntity> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList().stream().map(ProductEntityMapper.INSTANCE::toProduct)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        ProductEntity productEntity = entityManager.find(ProductEntity.class, productId);
        return Optional.ofNullable(productEntity)
                .map(ProductEntityMapper.INSTANCE::toProduct);
    }

    @Override
    public Product update(Product product) {
        ProductEntity productEntity = ProductEntityMapper.INSTANCE.toEntity(product);
        ProductEntity entity = entityManager.merge(productEntity);
        return ProductEntityMapper.INSTANCE.toProduct(entity);
    }

    @Override
    public boolean productExistsByEAN(EANBarcode eanBarcode) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ProductEntity> root = cq.from(ProductEntity.class);

        cq.select(cb.count(root)).where(cb.equal(root.get("barcode"), eanBarcode.getBarcode()));
        Long count = entityManager.createQuery(cq).getSingleResult();

        return (count > 0);
    }
}
