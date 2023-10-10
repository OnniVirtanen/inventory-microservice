package com.onnivirtanen.inventory.domain.command;

import com.onnivirtanen.inventory.domain.model.entity.Category;
import com.onnivirtanen.inventory.domain.model.valueobject.Discount;
import com.onnivirtanen.inventory.domain.model.valueobject.EANBarcode;
import com.onnivirtanen.inventory.domain.model.valueobject.Price;
import com.onnivirtanen.inventory.domain.model.valueobject.Quantity;
import com.onnivirtanen.inventory.domain.model.valueobject.ShelfLocation;
import lombok.NonNull;

public record AddNewProductCommand(

        @NonNull
        EANBarcode barcode,
        @NonNull
        Price price,
        @NonNull
        Category category,
        @NonNull
        ShelfLocation location,
        @NonNull
        Discount discount,
        @NonNull
        Quantity quantity
) {

}
