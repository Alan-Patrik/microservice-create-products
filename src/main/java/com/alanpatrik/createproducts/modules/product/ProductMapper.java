package com.alanpatrik.createproducts.modules.product;

import com.alanpatrik.createproducts.modules.product.dto.ProductResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    default ProductResponseDTO toResponse(Product product) {
        ProductResponseDTO productResponseDTO = new ProductResponseDTO();
        productResponseDTO.setId(product.getId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setQuantity(product.getQuantity());
        productResponseDTO.setCreationDate(product.getCreationDate());
        productResponseDTO.setLastModifiedDate(product.getLastModifiedDate());

        return productResponseDTO;
    }
}
