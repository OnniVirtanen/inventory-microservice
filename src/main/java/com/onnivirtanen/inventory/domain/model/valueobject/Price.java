package com.onnivirtanen.inventory.domain.model.valueobject;

import com.onnivirtanen.inventory.domain.exception.ValueObjectArgumentException;
import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
public final class Price implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private final BigDecimal amount;

    public Price(BigDecimal amount) {
        validate(amount);

        this.amount = amount;
    }

    public Price(double amount) {
        this(new BigDecimal(amount));
    }

    public Price(int amount) {
        this(new BigDecimal(amount));
    }

    public Price(String amount) {
        this(new BigDecimal(amount));
    }

    private static void validate(BigDecimal amount) {
        if (amount == null) {
            throw new ValueObjectArgumentException("Price cannot be null.");
        }
        if (isNegativeAmount(amount)) {
            throw new ValueObjectArgumentException("Price cannot be negative.");
        }
        if (isZero(amount)) {
            throw new ValueObjectArgumentException("Price cannot be zero.");
        }
    }

    private static boolean isNegativeAmount(BigDecimal amount) {
        return (amount.compareTo(BigDecimal.ZERO) < 0);
    }

    private static boolean isZero(BigDecimal amount) {
        return (amount.compareTo(BigDecimal.ZERO) == 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return Objects.equals(amount, price.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return "Price{" +
                "amount=" + amount +
                '}';
    }
}
