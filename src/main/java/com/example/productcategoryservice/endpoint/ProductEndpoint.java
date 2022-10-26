package com.example.productcategoryservice.endpoint;

import com.example.productcategoryservice.dto.productDto.CreateProductDto;
import com.example.productcategoryservice.dto.productDto.ProductResponseDto;
import com.example.productcategoryservice.dto.productDto.UpdateProduct;
import com.example.productcategoryservice.mapper.ProductMapper;
import com.example.productcategoryservice.model.Category;
import com.example.productcategoryservice.model.Product;
import com.example.productcategoryservice.service.CategoryService;
import com.example.productcategoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductEndpoint {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ProductResponseDto> getAllProducts() {
        return productMapper.map(productService.findAll());
    }

    @GetMapping("/{id}")
    public ProductResponseDto getProductById(@PathVariable("id") int id) {
        Product product = productService.findById(id);
        return productMapper.map(product);
    }

    @GetMapping("/byCategory/{id}")
    public List<ProductResponseDto> getProductByCategory(@PathVariable("id") int id, Category category) {
        List<Product> productsByCategory = productService.findByCategoryId(category);
        return productMapper.map(productsByCategory);

    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductDto createProductDto) {
        Product sevedProduct = productService.save(productMapper.map(createProductDto));
        return ResponseEntity.ok(sevedProduct);
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody UpdateProduct updateProduct) {
        Product editProduct = productService.update(productMapper.map(updateProduct));
        return ResponseEntity.ok(editProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable("id") int id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
