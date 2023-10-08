package com.onnivirtanen.inventory.domain.exception;

public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException(final String errorMessage) {
        super(errorMessage);
    }

    public ProductAlreadyExistsException(final String errorMessage, final Throwable cause) {
        super(errorMessage, cause);
    }

    public ProductAlreadyExistsException(final Throwable cause) {
        super(cause);
    }
}
