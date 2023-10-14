package com.onnivirtanen.inventory.domain.port.in;

import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.command.AddNewProductCommand;
import com.onnivirtanen.inventory.domain.command.NewShelfLocationCommand;
import com.onnivirtanen.inventory.domain.command.ProductMissingCommand;
import com.onnivirtanen.inventory.domain.command.ReStockProductCommand;
import com.onnivirtanen.inventory.domain.command.RemoveProductCommand;

import java.util.List;

public interface InventoryService {

    Product addNewProduct(AddNewProductCommand request);

    Product reStockProduct(ReStockProductCommand request);

    void removeProductFromSelection(RemoveProductCommand request);

    List<Product> findAllProducts();

    void assignNewShelfLocation(NewShelfLocationCommand request);

    void markProductMissing(ProductMissingCommand request);
}
