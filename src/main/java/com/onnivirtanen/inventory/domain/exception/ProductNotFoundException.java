package com.onnivirtanen.inventory.domain.exception;

public class ProductNotFoundException extends DomainException {

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
