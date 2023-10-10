package com.onnivirtanen.inventory.domain.usecases;

import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.exception.ProductAlreadyExistsException;
import com.onnivirtanen.inventory.domain.exception.ProductNotFoundException;
import com.onnivirtanen.inventory.domain.port.in.UseCase;
import com.onnivirtanen.inventory.domain.port.out.InventoryRepository;
import com.onnivirtanen.inventory.domain.command.AddNewProductCommand;
import com.onnivirtanen.inventory.domain.command.NewShelfLocationCommand;
import com.onnivirtanen.inventory.domain.command.ProductMissingCommand;
import com.onnivirtanen.inventory.domain.command.ReStockProductCommand;
import com.onnivirtanen.inventory.domain.command.RemoveProductCommand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UseCaseImpl implements UseCase {

    private final InventoryRepository repository;

    public UseCaseImpl(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addNewProduct(AddNewProductCommand request) {
        boolean productExists = repository.productExistsByEAN(request.barcode());
        if (productExists) {
            throw new ProductAlreadyExistsException("Product with same EAN already exists.");
        }

        Product product = Product.from(request);
        repository.save(product);
    }

    @Override
    public void reStockProduct(ReStockProductCommand request) {
        Product product = repository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        product.reStock(request.quantity());

        repository.update(product);
    }

    @Override
    public void removeProductFromSelection(RemoveProductCommand request) {
        repository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        repository.deleteById(request.productId());
    }

    @Override
    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    @Override
    public void assignNewShelfLocation(NewShelfLocationCommand request) {
        Product product = repository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        product.assignShelfLocation(request.location());

        repository.update(product);
    }

    @Override
    public void markProductMissing(ProductMissingCommand request) {
        Product product = repository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        product.markProductMissing(request.quantity());

        repository.update(product);
    }
}
