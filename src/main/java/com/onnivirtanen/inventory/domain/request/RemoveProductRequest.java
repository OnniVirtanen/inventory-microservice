package com.onnivirtanen.inventory.domain.request;

import lombok.NonNull;

import java.util.UUID;

public record RemoveProductRequest(

        @NonNull
        UUID productId
) {

}
