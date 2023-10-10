package com.onnivirtanen.inventory.domain.command;

import com.onnivirtanen.inventory.domain.model.valueobject.Quantity;
import lombok.NonNull;

import java.util.UUID;

public record ReStockProductCommand(

        @NonNull
        UUID productId,
        @NonNull
        Quantity quantity
) {

}
