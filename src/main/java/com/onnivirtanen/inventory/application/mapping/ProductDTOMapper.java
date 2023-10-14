package com.onnivirtanen.inventory.application.mapping;

import com.onnivirtanen.inventory.domain.model.aggregate.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductDTOMapper {
    ProductDTOMapper INSTANCE = Mappers.getMapper( ProductDTOMapper.class );

    ProductDTO toDTO(Product product);

    Product fromDTO(ProductDTO productDTO);
}
