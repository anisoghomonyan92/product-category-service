package com.example.productcategoryservice.endpoint;

import com.example.productcategoryservice.dto.categoryDto.CategoryResponseDto;
import com.example.productcategoryservice.dto.categoryDto.CreateCategoryDto;
import com.example.productcategoryservice.dto.categoryDto.UpdateCategory;
import com.example.productcategoryservice.mapper.CategoryMapper;
import com.example.productcategoryservice.model.Category;
import com.example.productcategoryservice.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryEndpoint {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public List<CategoryResponseDto> getAllAuthors(){
        return categoryMapper.map(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable("id") int id){
        Category category = categoryService.findById(id);
        return categoryMapper.map(category);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody CreateCategoryDto createCategoryDto){
        Category sevedCategory=categoryService.save(categoryMapper.map(createCategoryDto));
        return ResponseEntity.ok(sevedCategory);
    }


    @PutMapping
    public ResponseEntity<Category> updateCategory(@RequestBody UpdateCategory updateCategory){
        Category editCategory=categoryService.update(categoryMapper.map(updateCategory));
        return ResponseEntity.ok(editCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable("id") int id){
        categoryService.delete(id);
        return ResponseEntity.noContent().build();

    }
}
