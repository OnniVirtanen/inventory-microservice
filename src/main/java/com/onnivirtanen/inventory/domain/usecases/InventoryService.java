package com.onnivirtanen.inventory.domain.usecases;

import com.onnivirtanen.inventory.domain.aggregate.Product;
import com.onnivirtanen.inventory.domain.exception.ProductAlreadyExistsException;
import com.onnivirtanen.inventory.domain.exception.ProductNotFoundException;
import com.onnivirtanen.inventory.domain.repository.InventoryRepository;
import com.onnivirtanen.inventory.domain.request.AddNewProductRequest;
import com.onnivirtanen.inventory.domain.request.NewShelfLocationRequest;
import com.onnivirtanen.inventory.domain.request.ProductMissingRequest;
import com.onnivirtanen.inventory.domain.request.ReStockProductRequest;
import com.onnivirtanen.inventory.domain.request.RemoveProductRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService implements UseCase {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addNewProduct(AddNewProductRequest request) {
        boolean productExists = repository.productExistsByEAN(request.barcode());
        if (productExists) {
            throw new ProductAlreadyExistsException("Product with same EAN already exists.");
        }

        Product product = Product.from(request);
        repository.save(product);
    }

    @Override
    public void reStockProduct(ReStockProductRequest request) {
        Product product = repository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        product.reStock(request.quantity());

        repository.update(product);
    }

    @Override
    public void removeProductFromSelection(RemoveProductRequest request) {
        repository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        repository.deleteById(request.productId());
    }

    @Override
    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    @Override
    public void assignNewShelfLocation(NewShelfLocationRequest request) {
        Product product = repository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        product.assignShelfLocation(request.location());

        repository.update(product);
    }

    @Override
    public void markProductMissing(ProductMissingRequest request) {
        Product product = repository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        product.markProductMissing(request.quantity());

        repository.update(product);
    }
}
