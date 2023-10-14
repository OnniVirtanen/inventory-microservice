package com.onnivirtanen.inventory.domain.model.aggregate;

import com.onnivirtanen.inventory.domain.event.DomainEvent;
import com.onnivirtanen.inventory.domain.event.MarkProductMissingEvent;
import com.onnivirtanen.inventory.domain.event.ProductCreatedEvent;
import com.onnivirtanen.inventory.domain.event.ProductRestockedEvent;
import com.onnivirtanen.inventory.domain.exception.AggregateObjectArgumentException;
import com.onnivirtanen.inventory.domain.model.entity.Category;
import com.onnivirtanen.inventory.domain.command.AddNewProductCommand;
import com.onnivirtanen.inventory.domain.model.valueobject.Discount;
import com.onnivirtanen.inventory.domain.model.valueobject.EANBarcode;
import com.onnivirtanen.inventory.domain.model.valueobject.Price;
import com.onnivirtanen.inventory.domain.model.valueobject.Quantity;
import com.onnivirtanen.inventory.domain.model.valueobject.ShelfLocation;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
public class Product implements Aggregate, Serializable {

    private static final long serialVersionUID = 1L;

    private final List<DomainEvent> domainEvents = new ArrayList<>();
    private final UUID id;
    private EANBarcode barcode;
    private Price price;
    private Category category;
    private ShelfLocation location;
    private Discount discount;
    private Quantity quantity;

    public Product(UUID id, EANBarcode barcode, Price price, Category category, ShelfLocation location,
                   Discount discount, Quantity quantity) {
        this.id = id;
        this.barcode = barcode;
        this.price = price;
        this.category = category;
        this.location = location;
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

    public void reStock(Quantity quantity) {
        this.quantity = new Quantity(this.quantity.getAmount() + quantity.getAmount());
        domainEvents.add(new ProductRestockedEvent(this, "Product restocked"));
    }

    public void markProductMissing(Quantity quantityMissing) {
        if (this.quantity.getAmount() - quantityMissing.getAmount() < 0) {
            throw new AggregateObjectArgumentException("Product cannot have more items missing than there is stock.");
        }

        this.quantity = new Quantity(this.quantity.getAmount() - quantityMissing.getAmount());
        domainEvents.add(new MarkProductMissingEvent(this, quantityMissing
                + " products was marked missing"));
    }

    public void removeDiscount() {
        this.discount = new Discount(0);
    }

    public boolean isDiscounted() {
        if (this.discount == null) {
            return false;
        } else return this.discount.getDiscountPercentage() != 0;
    }

    public void addDiscount(Discount discount) {
        this.discount = discount;
    }

    public void assignShelfLocation(ShelfLocation location) {
        this.location = location;
    }

    public static Product from(AddNewProductCommand request) {
        Product product = new Product(
                null,
                request.barcode(),
                request.price(),
                request.category(),
                request.location(),
                request.discount(),
                request.quantity()
        );

        product.addDomainEvent(new ProductCreatedEvent(product, "product created"));

        return product;
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
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public List<DomainEvent> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    @Override
    public void clearDomainEvents() {
        domainEvents.clear();
    }

    private void addDomainEvent(DomainEvent domainEvent) {
        domainEvents.add(domainEvent);
    }
}
