package com.example.productcategoryservice.dto.productDto;
import com.example.productcategoryservice.model.Category;
import com.example.productcategoryservice.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private int Id;
    private String title;
    private int caunt;
    private double price;
    private Category category;
    private User user;
}
