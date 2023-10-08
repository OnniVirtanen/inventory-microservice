package com.onnivirtanen.inventory.infrastructure.jpa;

import com.onnivirtanen.inventory.domain.aggregate.Product;
import com.onnivirtanen.inventory.domain.entity.Category;
import com.onnivirtanen.inventory.domain.valueobject.Discount;
import com.onnivirtanen.inventory.domain.valueobject.EANBarcode;
import com.onnivirtanen.inventory.domain.valueobject.Price;
import com.onnivirtanen.inventory.domain.valueobject.ShelfLocation;
import com.onnivirtanen.inventory.infrastructure.jpa.entity.CategoryEntity;
import com.onnivirtanen.inventory.infrastructure.jpa.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper( ProductMapper.class );

    ProductEntity productToProductEntity(Product product);
    Product productEntityToProduct(ProductEntity productEntity);

    /*
    Maps product to product entity
     */
    default String eanBarcodeToString(EANBarcode value) {
        return (value == null) ? null : value.getBarcode();
    }

    default BigDecimal priceToBigDecimal(Price value) {
        return (value == null) ? null : value.getAmount();
    }

    default CategoryEntity categoryToCategoryEntity(Category value) {
        return (value == null) ? null : new CategoryEntity(value);
    }

    default String shelfLocationToString(ShelfLocation value) {
        return (value == null) ? null : value.getLocation();
    }

    default Integer discountToInteger(Discount value) {
        return (value == null) ? null : value.getDiscountPercentage();
    }

        /*
    Maps product entity to product
     */

    default EANBarcode stringToEanBarcode(String value) {
        return new EANBarcode(value);
    }

    default Price bigDecimalToPrice(BigDecimal value) {
        return new Price(value);
    }

    default Discount integerToDiscount(Integer value) {
        return new Discount(value);
    }

    default Category categoryEntityToCategory(CategoryEntity value) {

        return new Category(
                value.getCategoryId(),
                value.getName()
        );
    }

    default ShelfLocation stringToShelfLocation(String value) {
        return new ShelfLocation(value);
    }
}
