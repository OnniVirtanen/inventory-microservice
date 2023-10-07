package com.onnivirtanen.inventory.domain.usecases;

public interface ReceiveProduct extends UseCase {
    boolean receiveProduct(ReceiveProductCommand);
}
