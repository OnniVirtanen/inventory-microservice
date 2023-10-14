package com.onnivirtanen.inventory.application;

import com.onnivirtanen.inventory.application.mapping.ProductDTO;
import com.onnivirtanen.inventory.application.mapping.ProductDTOMapper;
import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.port.in.InventoryService;
import com.onnivirtanen.inventory.domain.command.AddNewProductCommand;
import com.onnivirtanen.inventory.domain.command.NewShelfLocationCommand;
import com.onnivirtanen.inventory.domain.command.ProductMissingCommand;
import com.onnivirtanen.inventory.domain.command.ReStockProductCommand;
import com.onnivirtanen.inventory.domain.command.RemoveProductCommand;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/v1/inventory/")
public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping(path = "product", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProductDTO> addNewProduct(@RequestBody AddNewProductCommand request) {
        Product product = inventoryService.addNewProduct(request);
        return ResponseEntity.ok(ProductDTOMapper.INSTANCE.toDTO(product));
    }

    @GetMapping(path = "products", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProductDTO>> findAllProducts() {
        List<ProductDTO> products = inventoryService.findAllProducts()
                .stream()
                .map(ProductDTOMapper.INSTANCE::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(products);
    }

    @PutMapping(path = "product/restock", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> reStockProduct(@RequestBody ReStockProductCommand request) {
        inventoryService.reStockProduct(request);
        return ResponseEntity.ok("Product restocked successfully.");
    }

    @DeleteMapping(path = "product", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> removeProductFromSelection(@RequestBody RemoveProductCommand request) {
        inventoryService.removeProductFromSelection(request);
        return ResponseEntity.ok("Product removed successfully.");
    }

    @PutMapping(path = "product/new-shelf-location", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> assignNewShelfLocation(@RequestBody NewShelfLocationCommand request) {
        inventoryService.assignNewShelfLocation(request);
        return ResponseEntity.ok("Assigned new shelf location successfully.");
    }

    @PutMapping(path = "product/missing", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> markProductMissing(@RequestBody ProductMissingCommand request) {
        inventoryService.markProductMissing(request);
        return ResponseEntity.ok("Product marked missing successfully.");
    }
}
