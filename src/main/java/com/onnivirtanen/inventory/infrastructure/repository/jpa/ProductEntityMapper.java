package com.onnivirtanen.inventory.infrastructure.repository.jpa;

import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import com.onnivirtanen.inventory.domain.model.entity.Category;
import com.onnivirtanen.inventory.domain.model.valueobject.Discount;
import com.onnivirtanen.inventory.domain.model.valueobject.EANBarcode;
import com.onnivirtanen.inventory.domain.model.valueobject.Price;
import com.onnivirtanen.inventory.domain.model.valueobject.Quantity;
import com.onnivirtanen.inventory.domain.model.valueobject.ShelfLocation;
import com.onnivirtanen.inventory.infrastructure.repository.jpa.entity.CategoryEntity;
import com.onnivirtanen.inventory.infrastructure.repository.jpa.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;

@Mapper
public interface ProductEntityMapper {
    ProductEntityMapper INSTANCE = Mappers.getMapper( ProductEntityMapper.class );

    ProductEntity toEntity(Product product);
    Product toProduct(ProductEntity productEntity);

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

    default Long quantityToLong(Quantity value) {
        return (value == null) ? null : value.getAmount();
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

    default Quantity longToQuantity(Long value) {
        return new Quantity(value);
    }
}
