package com.onnivirtanen.inventory.domain.model.request;

import com.onnivirtanen.inventory.domain.model.valueobject.Quantity;
import lombok.NonNull;

import java.util.UUID;

public record ProductMissingRequest(

    @NonNull
    UUID productId,
    @NonNull
    Quantity quantity
) {
}
