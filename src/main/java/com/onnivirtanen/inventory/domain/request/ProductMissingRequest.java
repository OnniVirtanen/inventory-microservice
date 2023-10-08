package com.onnivirtanen.inventory.domain.request;

import com.onnivirtanen.inventory.domain.valueobject.Quantity;
import lombok.NonNull;

import java.util.UUID;

public record ProductMissingRequest(

    @NonNull
    UUID productId,
    @NonNull
    Quantity quantity
) {
}
