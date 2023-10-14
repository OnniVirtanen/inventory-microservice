package com.onnivirtanen.inventory.domain.model.valueobject;

import com.onnivirtanen.inventory.domain.exception.ValueObjectArgumentException;
import lombok.Getter;

import java.io.Serializable;
import java.util.Objects;

@Getter
public final class ShelfLocation implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private final String location;

    public ShelfLocation(String location) {
        validate(location);

        this.location = location;
    }

    private static void validate(String location) {
        if (location == null) {
            throw new ValueObjectArgumentException("Location cannot be null.");
        }
        if (!isCorrectShelfNumber(location)) {
            throw new ValueObjectArgumentException(
                    "Shelf location must start with one uppercase letter and it must be followed by two numbers."
            );
        }
    }

    private static boolean isCorrectShelfNumber(String location) {
        final String PATTERN = "^[A-Z][0-9]{2}$";
        return location.matches(PATTERN);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShelfLocation that = (ShelfLocation) o;
        return Objects.equals(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }

    @Override
    public String toString() {
        return "ShelfLocation{" +
                "location='" + location + '\'' +
                '}';
    }
}
