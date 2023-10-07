package com.onnivirtanen.inventory.domain.request;

import java.util.UUID;

public record ReStockProductRequest(
        UUID productId,
        long quantity
) {

}
