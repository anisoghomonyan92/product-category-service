package com.example.productcategoryservice.repository;

import com.example.productcategoryservice.model.Category;
import com.example.productcategoryservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByCategory(Category category);

    void deleteById(int id);

}
