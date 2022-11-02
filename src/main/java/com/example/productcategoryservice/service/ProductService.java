package com.example.productcategoryservice.service;

import com.example.productcategoryservice.model.Category;
import com.example.productcategoryservice.model.Product;
import com.example.productcategoryservice.repository.ProductRepository;
import com.example.productcategoryservice.security.CurrentUser;
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

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findByCategoryId(Category category) {
        return productRepository.findAllByCategory(category);
    }

    public void deleteById(int id, CurrentUser currentUser) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent() && currentUser.getUser().getId() == byId.get().getUser().getId()) {
            productRepository.deleteById(id);
        }
        ResponseEntity.notFound().build();
    }

    public Optional<Product> findProductById(int id) {
        return productRepository.findById(id);
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }
}
