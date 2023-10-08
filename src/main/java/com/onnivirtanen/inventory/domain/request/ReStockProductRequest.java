package com.onnivirtanen.inventory.domain.request;

import lombok.NonNull;

import java.util.UUID;

public record ReStockProductRequest(

        @NonNull
        UUID productId,
        long quantity
) {

}
