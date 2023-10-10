package com.onnivirtanen.inventory.domain.command;

import lombok.NonNull;

import java.util.UUID;

public record RemoveProductCommand(

        @NonNull
        UUID productId
) {

}
