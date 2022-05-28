package com.alanpatrik.createproducts.modules.product;

import com.alanpatrik.createproducts.exceptions.CustomBadRequestException;
import com.alanpatrik.createproducts.exceptions.CustomNotFoundException;
import com.alanpatrik.createproducts.http.HttpResponseDTO;
import com.alanpatrik.createproducts.modules.product.dto.ProductRequestDTO;
import com.alanpatrik.createproducts.modules.product.dto.ProductResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<HttpResponseDTO<List<ProductResponseDTO>>> getAll() {
        List<ProductResponseDTO> productResponseDTOList = productService.getAll();
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, productResponseDTOList));
    }

    @PostMapping
    public ResponseEntity<HttpResponseDTO<ProductResponseDTO>> create(@RequestBody ProductRequestDTO productRequestDTO) throws CustomBadRequestException {
        ProductResponseDTO productResponseDTO = productService.create(productRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, productResponseDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpResponseDTO<ProductResponseDTO>> update(@PathVariable Long id, @RequestBody ProductRequestDTO productRequestDTO) throws CustomNotFoundException {
        ProductResponseDTO productResponseDTO = productService.update(id, productRequestDTO);
        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.OK, productResponseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpResponseDTO> delete(@PathVariable Long id) throws CustomNotFoundException {
        productService.delete(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .contentType(MediaType.APPLICATION_JSON)
                .body(new HttpResponseDTO<>(HttpStatus.NO_CONTENT));
    }
}
