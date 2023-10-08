package com.onnivirtanen.inventory.domain.usecases;

import com.onnivirtanen.inventory.domain.aggregate.Product;
import com.onnivirtanen.inventory.domain.request.AddNewProductRequest;
import com.onnivirtanen.inventory.domain.request.NewShelfLocationRequest;
import com.onnivirtanen.inventory.domain.request.ProductMissingRequest;
import com.onnivirtanen.inventory.domain.request.ReStockProductRequest;
import com.onnivirtanen.inventory.domain.request.RemoveProductRequest;

import java.util.List;

public interface UseCase {

    void addNewProduct(AddNewProductRequest request);

    void reStockProduct(ReStockProductRequest request);

    void removeProductFromSelection(RemoveProductRequest request);

    List<Product> findAllProducts();

    void assignNewShelfLocation(NewShelfLocationRequest request);

    void markProductMissing(ProductMissingRequest request);
}
