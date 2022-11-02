package com.example.productcategoryservice.dto.userDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserDto {
    private String name;
    private String surname;
    private String email;
    private String password;

}
