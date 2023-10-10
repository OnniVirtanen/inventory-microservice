package com.onnivirtanen.inventory.domain.exception;

public class EntityObjectArgumentException extends DomainException {

    public EntityObjectArgumentException(String errorMessage) {
        super(errorMessage);
    }

    public EntityObjectArgumentException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }

    public EntityObjectArgumentException(Throwable cause) {
        super(cause);
    }
}
