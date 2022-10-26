package com.example.productcategoryservice.service;

import com.example.productcategoryservice.model.Category;
import com.example.productcategoryservice.model.Product;
import com.example.productcategoryservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(int id) {
        Optional<Product> byId = productRepository.findById(id);
        return byId.orElse(null);
    }

    public Product save(Product map) {
        return productRepository.save(map);
    }

    public Product update(Product map) {
        if (map.getId() == 0) {
            ResponseEntity.badRequest().build();
        }
        return productRepository.save(map);
    }

    public void delete(int id) {
        productRepository.deleteById(id);
    }

    public List<Product> findByCategoryId(Category category) {
        return productRepository.findAllByCategory(category);
    }

}
