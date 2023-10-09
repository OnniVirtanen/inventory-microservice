package com.onnivirtanen.inventory.application;

import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.port.in.UseCase;
import com.onnivirtanen.inventory.domain.model.request.AddNewProductRequest;
import com.onnivirtanen.inventory.domain.model.request.NewShelfLocationRequest;
import com.onnivirtanen.inventory.domain.model.request.ProductMissingRequest;
import com.onnivirtanen.inventory.domain.model.request.ReStockProductRequest;
import com.onnivirtanen.inventory.domain.model.request.RemoveProductRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping(path = "product", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> addNewProduct(@RequestBody AddNewProductRequest request) {
        useCase.addNewProduct(request);
        return ResponseEntity.ok("Product added successfully.");
    }

    @GetMapping(path = "products", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<Product>> findAllProducts() {
        List<Product> products = useCase.findAllProducts();
        return ResponseEntity.ok(products);
    }

    @PutMapping(path = "product/restock", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> reStockProduct(@RequestBody ReStockProductRequest request) {
        useCase.reStockProduct(request);
        return ResponseEntity.ok("Product restocked successfully.");
    }

    @DeleteMapping(path = "product", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> removeProductFromSelection(@RequestBody RemoveProductRequest request) {
        useCase.removeProductFromSelection(request);
        return ResponseEntity.ok("Product removed successfully.");
    }

    @PutMapping(path = "product/new-shelf-location", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> assignNewShelfLocation(@RequestBody NewShelfLocationRequest request) {
        useCase.assignNewShelfLocation(request);
        return ResponseEntity.ok("Assigned new shelf location successfully.");
    }

    @PutMapping(path = "product/missing", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> markProductMissing(@RequestBody ProductMissingRequest request) {
        useCase.markProductMissing(request);
        return ResponseEntity.ok("Product marked missing successfully.");
    }
}
