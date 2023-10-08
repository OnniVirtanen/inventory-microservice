package com.onnivirtanen.inventory.application;

import com.onnivirtanen.inventory.domain.aggregate.Product;
import com.onnivirtanen.inventory.domain.request.AddNewProductRequest;
import com.onnivirtanen.inventory.domain.usecases.UseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/inventory/")
public class InventoryController {

    private final UseCase useCase;

    public InventoryController(UseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    ResponseEntity<String> addNewProduct(@RequestBody AddNewProductRequest request) {
        useCase.addNewProduct(request);
        return ResponseEntity.ok("Product added successfully.");
    }

    @GetMapping
    ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = useCase.findAllProducts();
        return ResponseEntity.ok(products);
    }
}
