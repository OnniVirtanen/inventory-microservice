package com.onnivirtanen.inventory.domain.model.valueobject;

import com.onnivirtanen.inventory.domain.exception.ValueObjectArgumentException;
import lombok.Getter;

import java.io.Serializable;

@Getter
public final class EANBarcode implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    private final String barcode;

    public EANBarcode(String barcode) {
        validate(barcode);
        this.barcode = barcode;
    }

    private static void validate(String barcode) {
        if (barcode == null || !(barcode.length() == 8 || barcode.length() == 13)) {
            throw new ValueObjectArgumentException("EAN barcode must be either 8 or 13 digits long.");
        }
        if (!barcode.matches("[0-9]+")) {
            throw new ValueObjectArgumentException("EAN barcode can only contain digits.");
        }
        if (barcode.length() == 13 && !isValidEAN13(barcode)) {
            throw new ValueObjectArgumentException("Invalid EAN-13 barcode.");
        }
        if (barcode.length() == 8 && !isValidEAN8(barcode)) {
            throw new ValueObjectArgumentException("Invalid EAN-8 barcode.");
        }
    }

    private static boolean isValidEAN13(String barcode) {
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            int digit = Integer.parseInt(String.valueOf(barcode.charAt(i)));
            if (i % 2 == 1) { // even-indexed digit (0-based index)
                sum += digit * 3;
            } else {
                sum += digit;
            }
        }
        int mod = sum % 10;
        int checkDigit = (mod == 0) ? 0 : 10 - mod;

        return checkDigit == Integer.parseInt(String.valueOf(barcode.charAt(12)));
    }

    private static boolean isValidEAN8(String barcode) {
        int sum = 0;
        for (int i = 0; i < 7; i++) {
            int digit = Integer.parseInt(String.valueOf(barcode.charAt(i)));
            if (i % 2 == 1) { // even-indexed digit (0-based index)
                sum += digit;
            } else {
                sum += digit * 3;
            }
        }
        int mod = sum % 10;
        int checkDigit = (mod == 0) ? 0 : 10 - mod;

        return checkDigit == Integer.parseInt(String.valueOf(barcode.charAt(7)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EANBarcode that = (EANBarcode) o;
        return barcode.equals(that.barcode);
    }

    @Override
    public int hashCode() {
        return barcode.hashCode();
    }

    @Override
    public String toString() {
        return "EANBarcode{" +
                "barcode='" + barcode + '\'' +
                '}';
    }
}
