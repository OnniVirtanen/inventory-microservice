package com.onnivirtanen.inventory.domain.aggregate;

import com.onnivirtanen.inventory.domain.entity.Category;
import com.onnivirtanen.inventory.domain.request.AddNewProductRequest;
import com.onnivirtanen.inventory.domain.valueobject.Discount;
import com.onnivirtanen.inventory.domain.valueobject.EANBarcode;
import com.onnivirtanen.inventory.domain.valueobject.Price;
import com.onnivirtanen.inventory.domain.entity.ProductEvent;
import com.onnivirtanen.inventory.domain.valueobject.ShelfLocation;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Product implements Aggregate {

    private final UUID id;
    private final List<ProductEvent> eventHistory;
    private EANBarcode barcode;
    private Price price;
    private Category category;
    private ShelfLocation location;
    private Discount discount;
    private long quantity;

    public Product(UUID id, EANBarcode barcode, Price price, Category category, ShelfLocation location,
                   List<ProductEvent> eventHistory, Discount discount, long quantity) {
        this.id = id;
        this.barcode = barcode;
        this.price = price;
        this.category = category;
        this.location = location;
        this.eventHistory = eventHistory;
        this.discount = discount;
        this.quantity = quantity;
    }

    public void assignNewBarCode(EANBarcode barcode) {
        this.barcode = barcode;
    }

    public void assignNewPrice(Price price) {
        this.price = price;
    }

    public void assignNewCategory(Category category) {
        this.category = category;
    }

    public void reStock(long quantity) {
        this.quantity = this.quantity + quantity;
    }

    public void markProductMissing(long quantityMissing) {
        if (this.quantity - quantityMissing < 0) {
            throw new IllegalArgumentException("Product cannot have more items missing than there is stock.");
        }

        this.quantity = this.quantity - quantityMissing;
    }

    public void removeDiscount() {
        this.discount = null;
    }

    public boolean isDiscounted() {
        return (this.discount == null);
    }

    public void addDiscount(Discount discount) {
        this.discount = discount;
    }

    public void assignShelfLocation(ShelfLocation location) {
        this.location = location;
    }

    public void addEvent(ProductEvent event) {
        this.eventHistory.add(event);
    }

    public static Product from(AddNewProductRequest request) {
        ProductEvent createEvent = new ProductEvent(null, ProductEvent.ProductEventType.PRODUCT_CREATED);

        return new Product(
                null,
                request.barcode(),
                request.price(),
                request.category(),
                request.location(),
                new ArrayList<>(Collections.singleton(createEvent)),
                request.discount(),
                request.quantity()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", barcode=" + barcode +
                ", price=" + price +
                ", category=" + category +
                ", location=" + location +
                ", eventHistory=" + eventHistory +
                ", quantity=" + quantity +
                '}';
    }
}
