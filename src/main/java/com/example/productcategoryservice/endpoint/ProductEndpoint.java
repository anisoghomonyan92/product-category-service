package com.example.productcategoryservice.endpoint;

import com.example.productcategoryservice.dto.productDto.CreateProductDto;
import com.example.productcategoryservice.dto.productDto.ProductResponseDto;
import com.example.productcategoryservice.dto.productDto.UpdateProduct;
import com.example.productcategoryservice.mapper.ProductMapper;
import com.example.productcategoryservice.model.Category;
import com.example.productcategoryservice.model.Product;
import com.example.productcategoryservice.security.CurrentUser;
import com.example.productcategoryservice.service.CategoryService;
import com.example.productcategoryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductDto createProductDto,
                                                 @AuthenticationPrincipal CurrentUser currentUser) {
        Product userProduct = productMapper.map(createProductDto);
        userProduct.setUser(currentUser.getUser());
        Product sevedProduct = productService.save(userProduct);
        return ResponseEntity.ok(sevedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody UpdateProduct updateProductDto,
                                                 @AuthenticationPrincipal CurrentUser currentUser,
                                                 @PathVariable("id") int id) {
        Product userProduct = productMapper.map(updateProductDto);
        userProduct.setUser(currentUser.getUser());
        productService.save(userProduct);
        Optional<Product> byId = productService.findProductById(id);
        if (byId.isPresent() && currentUser.getUser().getId() == byId.get().getUser().getId()) {

            return ResponseEntity.ok(productService.update(productMapper.map(updateProductDto)));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") int id,
                                           @AuthenticationPrincipal CurrentUser currentUser) {
        productService.deleteById(id, currentUser);
        return ResponseEntity.noContent().build();
    }

}
