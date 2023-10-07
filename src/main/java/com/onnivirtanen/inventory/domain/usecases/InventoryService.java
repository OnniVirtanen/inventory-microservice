package com.onnivirtanen.inventory.domain.usecases;

import com.onnivirtanen.inventory.domain.aggregate.Product;
import com.onnivirtanen.inventory.domain.repository.InventoryRepository;
import com.onnivirtanen.inventory.domain.request.AddNewProductRequest;
import com.onnivirtanen.inventory.domain.request.ReStockProductRequest;
import org.springframework.stereotype.Service;

@Service
public class InventoryService implements UseCase {

    private final InventoryRepository repository;

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void addNewProduct(AddNewProductRequest request) {
        Product product = new Product(
                null,
                request.
        );


        // save
    }

    @Override
    public void ReStockProduct(ReStockProductRequest request) {

    }
}
