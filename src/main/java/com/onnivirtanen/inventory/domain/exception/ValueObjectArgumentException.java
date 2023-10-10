package com.onnivirtanen.inventory.domain.exception;

public class ValueObjectArgumentException extends DomainException {

    public ValueObjectArgumentException(String errorMessage) {
        super(errorMessage);
    }

    public ValueObjectArgumentException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public ValueObjectArgumentException(Throwable cause) {
        super(cause);
    }
}