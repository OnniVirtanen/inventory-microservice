package com.onnivirtanen.inventory.application;

import com.onnivirtanen.inventory.domain.request.AddNewProductRequest;
import com.onnivirtanen.inventory.domain.usecases.UseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/inventory/")
public class InventoryController {

    private final UseCase useCase;

    public InventoryController(UseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    ResponseEntity<String> addNewProduct(AddNewProductRequest request) {
        useCase.addNewProduct(request);
        return ResponseEntity.ok("Product added successfully.");
    }
}
