package com.example.productcategoryservice.endpoint;

import com.example.productcategoryservice.dto.userDto.CreateUserDto;
import com.example.productcategoryservice.dto.userDto.UserAuthDto;
import com.example.productcategoryservice.dto.userDto.UserAuthResponseDto;
import com.example.productcategoryservice.mapper.UserMapper;
import com.example.productcategoryservice.model.Role;
import com.example.productcategoryservice.model.User;
import com.example.productcategoryservice.service.UserService;
import com.example.productcategoryservice.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserEndpoint {

    private final UserMapper userMapper;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;


    @PostMapping("/user")
    public ResponseEntity<?> register(@RequestBody CreateUserDto createUserDto){
        Optional<User> existingUser = userService.findByEmail(createUserDto.getEmail());
        if(existingUser.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        User user = userMapper.map(createUserDto);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userMapper.map(userService.save(user)));
    }

    @PostMapping("user/auth")
    public ResponseEntity<?> auth(@RequestBody UserAuthDto userAuthDto){
        Optional<User> byEmail = userService.findByEmail(userAuthDto.getEmail());
        if(byEmail.isPresent()){
            User user = byEmail.get();
            if (passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword())){
                return ResponseEntity.ok(UserAuthResponseDto.builder()
                        .token(jwtTokenUtil.generateToken(user.getEmail()))
                        .user(userMapper.map(user))
                        .build()
                );
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
