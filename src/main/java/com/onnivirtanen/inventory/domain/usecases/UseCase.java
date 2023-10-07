package com.onnivirtanen.inventory.domain.usecases;

import com.onnivirtanen.inventory.domain.request.AddNewProductRequest;
import com.onnivirtanen.inventory.domain.request.ReStockProductRequest;

public interface UseCase {

    void addNewProduct(AddNewProductRequest request);

    void ReStockProduct(ReStockProductRequest request);
}
