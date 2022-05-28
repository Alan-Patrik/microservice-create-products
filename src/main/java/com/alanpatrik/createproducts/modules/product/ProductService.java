package com.alanpatrik.createproducts.modules.product;

import com.alanpatrik.createproducts.exceptions.CustomBadRequestException;
import com.alanpatrik.createproducts.exceptions.CustomNotFoundException;
import com.alanpatrik.createproducts.modules.product.dto.ProductRequestDTO;
import com.alanpatrik.createproducts.modules.product.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    List<ProductResponseDTO> getAll();

    ProductResponseDTO create(ProductRequestDTO productRequestDTO) throws CustomBadRequestException;

    ProductResponseDTO update(Long id, ProductRequestDTO productRequestDTO) throws CustomNotFoundException;

    void delete(Long id) throws CustomNotFoundException;
}
