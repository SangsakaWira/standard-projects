package com.wira.sasangka.standardprojects.feature.products.service;

import com.wira.sasangka.standardprojects.feature.util.BaseResponse;
import com.wira.sasangka.standardprojects.feature.products.dto.ProductDto;
import com.wira.sasangka.standardprojects.feature.products.dto.ProductRequest;
import com.wira.sasangka.standardprojects.feature.products.entity.model.Product;
import com.wira.sasangka.standardprojects.exceptions.ResourceNotFoundException;
import com.wira.sasangka.standardprojects.feature.products.entity.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.wira.sasangka.standardprojects.constant.AppConstants.PRODUCT_NOT_FOUND_MSG;
import static com.wira.sasangka.standardprojects.constant.AppConstants.messageFormat;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public BaseResponse<Page<ProductDto>> getAllProduct(Pageable pageable) {
        Page<ProductDto> dtoPage = productRepository
                .findAll(pageable)
                .map(this::toDto);

        return BaseResponse.<Page<ProductDto>>builder()
                .responseCode(HttpStatus.OK.value())
                .responseMessage("Found record products")
                .data(dtoPage)
                .build();
    }

    @Override
    public BaseResponse<ProductDto> getProductById(String productId) {
        Product persisted = this.findProductById(productId);
        ProductDto productDto = this.toDto(persisted);

        return BaseResponse.<ProductDto>builder()
                .responseCode(HttpStatus.OK.value())
                .responseMessage(messageFormat("Found Record with id ", productId))
                .data(productDto)
                .build();
    }

    @Override
    public BaseResponse<ProductDto> addNewProduct(ProductRequest request) {
        Product product = new Product();
        Product entity = this.toEntity(request, product);

        productRepository.save(entity);

        ProductDto productDto = this.toDto(entity);

        return BaseResponse.<ProductDto>builder()
                .responseCode(HttpStatus.CREATED.value())
                .responseMessage(messageFormat("Product {} created successfully ", product.getId()))
                .data(productDto)
                .build();
    }

    @Override
    public BaseResponse<ProductDto> updateProduct(String id, ProductRequest request) {
        Product persisted = this.findProductById(id);
        Product entity = this.toEntity(request, persisted);

        productRepository.save(entity);

        ProductDto productDto = this.toDto(entity);

        return BaseResponse.<ProductDto>builder()
                .responseCode(HttpStatus.CREATED.value())
                .responseMessage(messageFormat("Product {} created successfully ", id))
                .data(productDto)
                .build();
    }

    @Override
    public BaseResponse<Void> deleteProductById(String id) {
        Product persisted = this.findProductById(id);
        productRepository.delete(persisted);

        return BaseResponse.<Void>builder()
                .responseCode(HttpStatus.OK.value())
                .responseMessage(messageFormat("Product {} deleted successfully", id))
                .build();
    }


    private Product findProductById(String id) {
        String message = messageFormat(PRODUCT_NOT_FOUND_MSG, id);

        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(message));
    }

    private ProductDto toDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .name(product.getName())
                .description(product.getDescription())
                .qty(product.getQty())
                .price(product.getPrice())
                .build();
    }

    private Product toEntity(ProductRequest request, Product product) {
        product.setName(request.name());
        product.setDescription(request.description());
        product.setQty(request.qty());
        product.setPrice(request.price());

        return product;
    }
}
