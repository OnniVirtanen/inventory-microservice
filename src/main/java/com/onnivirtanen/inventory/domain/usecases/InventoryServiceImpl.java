package com.onnivirtanen.inventory.domain.usecases;

import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.exception.ProductAlreadyExistsException;
import com.onnivirtanen.inventory.domain.exception.ProductNotFoundException;
import com.onnivirtanen.inventory.domain.port.in.InventoryService;
import com.onnivirtanen.inventory.domain.port.out.DomainEventPublisher;
import com.onnivirtanen.inventory.domain.port.out.InventoryRepository;
import com.onnivirtanen.inventory.domain.command.AddNewProductCommand;
import com.onnivirtanen.inventory.domain.command.NewShelfLocationCommand;
import com.onnivirtanen.inventory.domain.command.ProductMissingCommand;
import com.onnivirtanen.inventory.domain.command.ReStockProductCommand;
import com.onnivirtanen.inventory.domain.command.RemoveProductCommand;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;
    private final DomainEventPublisher eventPublisher;

    public InventoryServiceImpl(InventoryRepository repository, DomainEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    @Transactional
    public Product addNewProduct(AddNewProductCommand request) {
        boolean productExists = repository.productExistsByEAN(request.barcode());
        if (productExists) {
            throw new ProductAlreadyExistsException("Product with same EAN already exists.");
        }

        Product product = Product.from(request);
        Product savedProduct = repository.save(product);

        publishDomainEvents(product);
        return savedProduct;
    }

    @Override
    @Transactional
    public Product reStockProduct(ReStockProductCommand request) {
        Product product = repository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        product.reStock(request.quantity());
        Product updatedProduct = repository.update(product);

        publishDomainEvents(product);
        return updatedProduct;
    }

    @Override
    @Transactional
    public void removeProductFromSelection(RemoveProductCommand request) {
        repository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        repository.deleteById(request.productId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void assignNewShelfLocation(NewShelfLocationCommand request) {
        Product product = repository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        product.assignShelfLocation(request.location());

        repository.update(product);
    }

    @Override
    @Transactional
    public void markProductMissing(ProductMissingCommand request) {
        Product product = repository.findById(request.productId())
                .orElseThrow(() -> new ProductNotFoundException("No product found by given id"));

        product.markProductMissing(request.quantity());
        repository.update(product);

        publishDomainEvents(product);
    }

    private void publishDomainEvents(Product product) {
        product.getDomainEvents().forEach(eventPublisher::publish);
        product.clearDomainEvents();
    }
}
