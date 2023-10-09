package com.onnivirtanen.inventory.domain.model.request;

import lombok.NonNull;

import java.util.UUID;

public record RemoveProductRequest(

        @NonNull
        UUID productId
) {

}
