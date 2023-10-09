package com.onnivirtanen.inventory.domain.model.request;

import com.onnivirtanen.inventory.domain.model.valueobject.ShelfLocation;
import lombok.NonNull;

import java.util.UUID;

public record NewShelfLocationRequest(

        @NonNull
        UUID productId,
        @NonNull
        ShelfLocation location
) {
}
