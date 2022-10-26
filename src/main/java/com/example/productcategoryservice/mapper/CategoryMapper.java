package com.example.productcategoryservice.mapper;

import com.example.productcategoryservice.dto.categoryDto.CategoryResponseDto;
import com.example.productcategoryservice.dto.categoryDto.CreateCategoryDto;
import com.example.productcategoryservice.dto.categoryDto.UpdateCategory;
import com.example.productcategoryservice.model.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category map(CreateCategoryDto createCategoryDto);

    CategoryResponseDto map(Category category);

    List<CategoryResponseDto> map(List<Category> categoryList);

    Category map(UpdateCategory updateCategory);





}
