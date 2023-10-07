package com.onnivirtanen.inventory.domain.request;

import com.onnivirtanen.inventory.domain.entity.Category;
import com.onnivirtanen.inventory.domain.valueobject.EANBarcode;
import com.onnivirtanen.inventory.domain.valueobject.Price;
import com.onnivirtanen.inventory.domain.valueobject.ShelfLocation;
import lombok.NonNull;

public record AddNewProductRequest(

        @NonNull
        EANBarcode barcode,
        @NonNull
        Price price,
        @NonNull
        Category category,
        @NonNull
        ShelfLocation location,
        long quantity
) {

}
