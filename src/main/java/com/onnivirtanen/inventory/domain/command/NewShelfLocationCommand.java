package com.onnivirtanen.inventory.domain.command;

import com.onnivirtanen.inventory.domain.model.valueobject.ShelfLocation;
import lombok.NonNull;

import java.util.UUID;

public record NewShelfLocationCommand(

        @NonNull
        UUID productId,
        @NonNull
        ShelfLocation location
) {
}
