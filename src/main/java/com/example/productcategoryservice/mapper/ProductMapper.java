package com.example.productcategoryservice.mapper;

import com.example.productcategoryservice.dto.productDto.CreateProductDto;
import com.example.productcategoryservice.dto.productDto.ProductResponseDto;
import com.example.productcategoryservice.dto.productDto.UpdateProduct;

import com.example.productcategoryservice.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product map(CreateProductDto createProductDto);

    ProductResponseDto map(Product product);

    List<ProductResponseDto> map(List<Product> productList);

   Product map(UpdateProduct updateProductDto);
}
