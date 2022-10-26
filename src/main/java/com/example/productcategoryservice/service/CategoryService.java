package com.example.productcategoryservice.service;

import com.example.productcategoryservice.model.Category;
import com.example.productcategoryservice.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public void delete(int id) {
        categoryRepository.deleteById(id);
    }

    public Category save(Category map) {
        return categoryRepository.save(map);
    }


    public Category update(Category map) {
        if(map.getId()==0){
            ResponseEntity.badRequest().build();
        }
       return categoryRepository.save(map);
    }

    public Category findById(int id) {
        Optional<Category> byId = categoryRepository.findById(id);
        return byId.orElse(null);
    }
}
