package com.onnivirtanen.inventory.domain.exception;

/**
 * Domain exception happens in domain layer and application must deal with the exception. It does
 * not matter if the application is web, commandline or other. DomainExceptions stay the same.
 */
public abstract class DomainException extends RuntimeException {

    public DomainException(final String errorMessage) {
        super(errorMessage);
    }

    public DomainException(final String errorMessage, final Throwable cause) {
        super(errorMessage, cause);
    }

    public DomainException(final Throwable cause) {
        super(cause);
    }
}
