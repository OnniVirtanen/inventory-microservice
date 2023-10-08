package com.onnivirtanen.inventory.domain.usecases;

import com.onnivirtanen.inventory.domain.aggregate.Product;
import com.onnivirtanen.inventory.domain.exception.ProductNotFoundException;
import com.onnivirtanen.inventory.domain.repository.InventoryRepository;
import com.onnivirtanen.inventory.domain.request.AddNewProductRequest;
import com.onnivirtanen.inventory.domain.request.ReStockProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InventoryService implements UseCase {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addNewProduct(AddNewProductRequest request) {
        Product product = Product.from(request);
        repository.save(product);
    }

    @Override
    public void reStockProduct(ReStockProductRequest request) {
        Product product = repository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("No patient found by given id"));

        product.reStock(request.quantity());

        repository.update(product);
    }

    @Override
    public void removeProductFromSelection(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public List<Product> findAllProducts() {
        return repository.findAll();
    }
}
