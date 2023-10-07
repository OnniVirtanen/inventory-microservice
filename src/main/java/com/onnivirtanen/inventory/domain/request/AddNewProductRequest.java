package com.onnivirtanen.inventory.domain.request;

import com.onnivirtanen.inventory.domain.entity.Category;
import com.onnivirtanen.inventory.domain.valueobject.EANBarcode;
import com.onnivirtanen.inventory.domain.valueobject.Price;
import com.onnivirtanen.inventory.domain.valueobject.ShelfLocation;

public record AddNewProductRequest(

        EANBarcode barcode,
        Price price,
        Category category,
        ShelfLocation location,
        long quantity
) {

}
