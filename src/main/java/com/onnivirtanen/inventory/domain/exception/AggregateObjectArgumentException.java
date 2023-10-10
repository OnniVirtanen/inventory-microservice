package com.onnivirtanen.inventory.domain.exception;

public class AggregateObjectArgumentException extends DomainException {

    public AggregateObjectArgumentException(String errorMessage) {
        super(errorMessage);
    }

    public AggregateObjectArgumentException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public AggregateObjectArgumentException(Throwable cause) {
        super(cause);
    }
}
