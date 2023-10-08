package com.onnivirtanen.inventory.domain.request;

import com.onnivirtanen.inventory.domain.valueobject.ShelfLocation;
import lombok.NonNull;

import java.util.UUID;

public record NewShelfLocationRequest(

        @NonNull
        UUID productId,
        @NonNull
        ShelfLocation location
) {
}
