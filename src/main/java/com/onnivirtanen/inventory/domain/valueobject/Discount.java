package com.onnivirtanen.inventory.domain.valueobject;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.util.Objects;

@Getter
@Embeddable
public class Discount implements ValueObject {

    private final Integer discountPercentage;

    public Discount(Integer discountPercentage) {
        validate(discountPercentage);

        this.discountPercentage = discountPercentage;
    }

    protected Discount() {
        discountPercentage = null;
    }

    private static void validate(Integer discountPercentage) {
        if (discountPercentage <= 0 || discountPercentage >= 100) {
            throw new IllegalArgumentException(
                    "Discount cannot be less than or equal to 0 OR more than or equal to 100."
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

