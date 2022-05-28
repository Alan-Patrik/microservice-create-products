package com.alanpatrik.createproducts.modules.product;

import com.alanpatrik.createproducts.modules.product.dto.ProductRequestDTO;
import com.alanpatrik.createproducts.modules.product.dto.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper = ProductMapper.INSTANCE;

    public Product verifyIfExistProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<ProductResponseDTO> getAll() {
        List<ProductResponseDTO> productResponseDTOList = productRepository.findAll().stream().map(product -> {
            ProductResponseDTO productResponseDTO = new ProductResponseDTO();

            productResponseDTO.setId(product.getId());
            productResponseDTO.setName(product.getName());
            productResponseDTO.setPrice(product.getPrice());
            productResponseDTO.setQuantity(product.getQuantity());
            productResponseDTO.setCreationDate(product.getCreationDate());
            productResponseDTO.setLastModifiedDate(product.getLastModifiedDate());

            return productResponseDTO;
        }).collect(Collectors.toList());

        return productResponseDTOList;
    }

    @Override
    public ProductResponseDTO create(ProductRequestDTO productRequestDTO) {
        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setQuantity(productRequestDTO.getQuantity());

        product = productRepository.save(product);
        return mapper.toResponse(product);
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO productRequestDTO) {
        Product receivedProduct = verifyIfExistProductById(id);
        receivedProduct.setName(productRequestDTO.getName());
        receivedProduct.setPrice(productRequestDTO.getPrice());
        receivedProduct.setQuantity(productRequestDTO.getQuantity());

        receivedProduct = productRepository.save(receivedProduct);
        return mapper.toResponse(receivedProduct);
    }

    public void delete(Long id) {
        Product receivedProduct = verifyIfExistProductById(id);
        productRepository.delete(receivedProduct);
    }
}
