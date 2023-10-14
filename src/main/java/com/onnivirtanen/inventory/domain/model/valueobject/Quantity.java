package com.onnivirtanen.inventory.domain.model.valueobject;

import com.onnivirtanen.inventory.domain.exception.ValueObjectArgumentException;

import java.io.Serializable;
import java.util.Objects;

public final class Quantity implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private final Long amount;

    public Quantity(Long amount) {
        validate(amount);

        this.amount = amount;
    }

    public Long getAmount() {
        return amount;
    }

    private static void validate(Long amount) {
        if (amount == null) {
            throw new ValueObjectArgumentException("Quantity cannot be null.");
        }
        if (isNegativeAmount(amount)) {
            throw new ValueObjectArgumentException("Quantity cannot be negative.");
        }
    }


    private static boolean isNegativeAmount(Long amount) {
        return (amount < 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity = (Quantity) o;
        return Objects.equals(amount, quantity.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Quantity{" +
                "amount=" + amount +
                '}';
    }
}
