package com.alanpatrik.createproducts.modules.product;

import com.alanpatrik.createproducts.exceptions.CustomBadRequestException;
import com.alanpatrik.createproducts.exceptions.CustomNotFoundException;
import com.alanpatrik.createproducts.modules.product.dto.ProductRequestDTO;
import com.alanpatrik.createproducts.modules.product.dto.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper = ProductMapper.INSTANCE;

    private Product verifyIfExistProductById(Long id) throws CustomNotFoundException {
        return productRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(
                String.format("Produto com o id %s não foi encontrado.", id)));
    }

    private void verifyIfProductAlreadyExists(String name) throws CustomBadRequestException {
        Optional<Product> product = productRepository.findByName(name);

        if (product.isPresent()) {
            throw new CustomBadRequestException(
                    String.format("Produto com o nome %s já foi cadastrado", name)
            );
        }
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
    public ProductResponseDTO create(ProductRequestDTO productRequestDTO) throws CustomBadRequestException {
        verifyIfProductAlreadyExists(productRequestDTO.getName());

        Product product = new Product();
        product.setName(productRequestDTO.getName());
        product.setPrice(productRequestDTO.getPrice());
        product.setQuantity(productRequestDTO.getQuantity());

        product = productRepository.save(product);
        return mapper.toResponse(product);
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO productRequestDTO) throws CustomNotFoundException {
        Product receivedProduct = verifyIfExistProductById(id);
        receivedProduct.setName(productRequestDTO.getName());
        receivedProduct.setPrice(productRequestDTO.getPrice());
        receivedProduct.setQuantity(productRequestDTO.getQuantity());

        receivedProduct = productRepository.save(receivedProduct);
        return mapper.toResponse(receivedProduct);
    }

    @Override
    public void delete(Long id) throws CustomNotFoundException {
        Product receivedProduct = verifyIfExistProductById(id);
        productRepository.delete(receivedProduct);
    }
}
