package com.example.productcategoryservice.dto.categoryDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategory {
    private int Id;
    private String name;
}
