package com.onnivirtanen.inventory.domain.model.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(final String errorMessage) {
        super(errorMessage);
    }

    public ProductNotFoundException(final String errorMessage, final Throwable cause) {
        super(errorMessage, cause);
    }

    public ProductNotFoundException(final Throwable cause) {
        super(cause);
    }
}
