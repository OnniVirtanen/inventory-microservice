package com.onnivirtanen.inventory.domain.usecases;

import com.onnivirtanen.inventory.domain.aggregate.Product;
import com.onnivirtanen.inventory.domain.request.AddNewProductRequest;
import com.onnivirtanen.inventory.domain.request.ReStockProductRequest;

import java.util.List;
import java.util.UUID;

public interface UseCase {

    void addNewProduct(AddNewProductRequest request);

    void reStockProduct(ReStockProductRequest request);

    void removeProductFromSelection(UUID id);

    List<Product> findAllProducts();
}
