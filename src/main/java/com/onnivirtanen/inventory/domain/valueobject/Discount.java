package com.onnivirtanen.inventory.domain.valueobject;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class Discount implements ValueObject {

    private final Integer discountPercentage;

    public Discount(Integer discountPercentage) {
        validate(discountPercentage);

        this.discountPercentage = discountPercentage;
    }

    private static void validate(Integer discountPercentage) {
        if (discountPercentage < 0 || discountPercentage >= 100) {
            throw new IllegalArgumentException(
                    "Discount cannot be less than 0 OR more than or equal to 100."
            );
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return Objects.equals(discountPercentage, discount.discountPercentage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(discountPercentage);
    }

    @Override
    public String toString() {
        return "Discount{" +
                "discountPercentage=" + discountPercentage +
                '}';
    }
}

