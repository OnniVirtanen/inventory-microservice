package com.onnivirtanen.inventory.infrastructure.jpa;

import com.onnivirtanen.inventory.domain.aggregate.Product;
import com.onnivirtanen.inventory.domain.entity.Category;
import com.onnivirtanen.inventory.domain.entity.ProductEvent;
import com.onnivirtanen.inventory.domain.valueobject.Discount;
import com.onnivirtanen.inventory.domain.valueobject.EANBarcode;
import com.onnivirtanen.inventory.domain.valueobject.Price;
import com.onnivirtanen.inventory.domain.valueobject.ShelfLocation;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity {

    @Id
    private UUID id;
    @OneToMany
    private List<ProductEventEntity> eventHistory;
    private String barcode;
    private BigDecimal price;
    @ManyToOne
    private CategoryEntity category;
    private String location;
    private Integer discount;
    private long quantity;

    public static ProductEntity fromDomainObject(Product product) {
        ProductEntity entity = new ProductEntity();

        entity.setId(product.getId());
        entity.setEventHistory(ProductEventEntity.fromDomainObject(product.getEventHistory()));
        entity.setBarcode(product.getBarcode().getBarcode());
        entity.setPrice(product.getPrice().getAmount());
        entity.setCategory(CategoryEntity.fromDomainObject(product.getCategory()));
        entity.setLocation(product.getLocation().getLocation());
        entity.setDiscount(product.getDiscount().getDiscountPercentage());
        entity.setQuantity(product.getQuantity());

        return entity;
    }

    public Product toDomainObject() {

        return new Product(
                this.id,
                new EANBarcode(this.barcode),
                new Price(this.price),
                this.category.toDomainObject(),
                new ShelfLocation(this.location),
                this.eventHistory.toDomainObject(),
                new Discount(this.discount),
                this.quantity
        );
    }
}
