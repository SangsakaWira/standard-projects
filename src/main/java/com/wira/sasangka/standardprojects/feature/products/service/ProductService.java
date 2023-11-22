package com.wira.sasangka.standardprojects.feature.products.service;

import com.wira.sasangka.standardprojects.feature.util.BaseResponse;
import com.wira.sasangka.standardprojects.feature.products.dto.ProductDto;
import com.wira.sasangka.standardprojects.feature.products.dto.ProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {
    BaseResponse<Page<ProductDto>> getAllProduct(Pageable pageable);

    BaseResponse<ProductDto> getProductById(String productId);

    @Transactional
    BaseResponse<ProductDto> addNewProduct(ProductRequest request);

    @Transactional
    BaseResponse<ProductDto> updateProduct(String id, ProductRequest request);

    @Transactional
    BaseResponse<Void> deleteProductById(String id);
}
