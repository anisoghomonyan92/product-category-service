package com.example.productcategoryservice.dto.productDto;

import com.example.productcategoryservice.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto {

    private String title;
    private int caunt;
    private double price;
    private Category category;

}
