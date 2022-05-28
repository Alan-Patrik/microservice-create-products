package com.alanpatrik.createproducts.modules.product;

import com.alanpatrik.createproducts.modules.product.dto.ProductRequestDTO;
import com.alanpatrik.createproducts.modules.product.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    List<ProductResponseDTO> getAll();

    ProductResponseDTO create(ProductRequestDTO productRequestDTO);

    ProductResponseDTO update(Long id, ProductRequestDTO productRequestDTO);

    void delete(Long id);
}
