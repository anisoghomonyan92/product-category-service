package com.example.productcategoryservice.dto.userDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuthResponseDto {

    private String token;
    private UserDto user;


}
