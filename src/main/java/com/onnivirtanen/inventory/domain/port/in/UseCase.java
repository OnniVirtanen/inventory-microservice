package com.onnivirtanen.inventory.domain.port.in;

import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.model.request.AddNewProductRequest;
import com.onnivirtanen.inventory.domain.model.request.NewShelfLocationRequest;
import com.onnivirtanen.inventory.domain.model.request.ProductMissingRequest;
import com.onnivirtanen.inventory.domain.model.request.ReStockProductRequest;
import com.onnivirtanen.inventory.domain.model.request.RemoveProductRequest;

import java.util.List;

public interface UseCase {

    void addNewProduct(AddNewProductRequest request);

    void reStockProduct(ReStockProductRequest request);

    void removeProductFromSelection(RemoveProductRequest request);

    List<Product> findAllProducts();

    void assignNewShelfLocation(NewShelfLocationRequest request);

    void markProductMissing(ProductMissingRequest request);
}
