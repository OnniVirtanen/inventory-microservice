package com.onnivirtanen.inventory.domain.usecases;

import com.onnivirtanen.inventory.domain.command.AddNewProductCommand;
import com.onnivirtanen.inventory.domain.command.ReStockProductCommand;
import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.model.entity.Category;
import com.onnivirtanen.inventory.domain.model.valueobject.*;

import java.util.UUID;

public class TestData {

    private TestData() {}

    public static AddNewProductCommand addNewProductCommand() {
        return new AddNewProductCommand(
                new EANBarcode("12344567"),
                new Price("5.0"),
                new Category("FRESH_PRODUCE"),
                new ShelfLocation("A05"),
                new Discount(0),
                new Quantity((long) 7)
        );
    }

    public static ReStockProductCommand reStockProductCommand() {
        return new ReStockProductCommand(
                UUID.randomUUID(),
                new Quantity((long) 7)
        );
    }

    public static Product product() {
        return new Product(
                UUID.randomUUID(),
                new EANBarcode("12344567"),
                new Price("5.0"),
                new Category("FRESH_PRODUCE"),
                new ShelfLocation("A05"),
                new Discount(0),
                new Quantity((long) 7)
        );
    }
}
