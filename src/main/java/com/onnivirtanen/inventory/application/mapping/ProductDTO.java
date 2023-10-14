package com.onnivirtanen.inventory.application.mapping;

import com.onnivirtanen.inventory.domain.model.entity.Category;
import com.onnivirtanen.inventory.domain.model.valueobject.*;

import java.util.UUID;

public record ProductDTO(

        UUID id,
        EANBarcode barcode,
        Price price,
        Category category,
        ShelfLocation location,
        Discount discount,
        Quantity quantity
) {

}