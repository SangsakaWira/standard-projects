package com.wira.sasangka.standardprojects.feature.products.controller;

import com.wira.sasangka.standardprojects.feature.util.BaseResponse;
import com.wira.sasangka.standardprojects.feature.products.dto.ProductDto;
import com.wira.sasangka.standardprojects.feature.products.dto.ProductRequest;
import com.wira.sasangka.standardprojects.feature.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.wira.sasangka.standardprojects.constant.ApiUrlConstant.PRODUCTS_URL;

@RestController
@RequestMapping(PRODUCTS_URL)
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<BaseResponse<Page<ProductDto>>> getProductPages(@RequestParam(defaultValue = "10") int size,
                                                                          @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        BaseResponse<Page<ProductDto>> response = productService.getAllProduct(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BaseResponse<ProductDto>> getProductById(@PathVariable String id) {
        BaseResponse<ProductDto> response = productService.getProductById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse<ProductDto>> addProduct(@RequestBody ProductRequest request) {
        BaseResponse<ProductDto> response = productService.addNewProduct(request);
        return ResponseEntity.ok(response);
    }

    @PatchMapping(
            path = "/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BaseResponse<ProductDto>> editProduct(@PathVariable String id,
                                                       @RequestBody ProductRequest request) {
        BaseResponse<ProductDto> response = productService.updateProduct(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse<Void>> deleteProduct(@PathVariable String id) {
        BaseResponse<Void> response = productService.deleteProductById(id);
        return ResponseEntity.ok(response);
    }
}
