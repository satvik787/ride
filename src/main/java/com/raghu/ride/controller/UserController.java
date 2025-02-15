package com.raghu.ride.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raghu.ride.aop.LoginRequired;
import com.raghu.ride.entity.UserEntity;
import com.raghu.ride.exception.ClientException;
import com.raghu.ride.models.LoginDTO;
import com.raghu.ride.models.ResponseDTO;
import com.raghu.ride.models.RegisterDTO;
import com.raghu.ride.service.AuthService;
import com.raghu.ride.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @LoginRequired
    @GetMapping("/me")
    public ResponseEntity<ResponseDTO<Object>> me(HttpServletRequest request) {
        long userId = (Long) request.getAttribute("userId");
        return ResponseEntity.ok().body(ResponseDTO.success("User details", userService.getUserByID(userId)));
    }
    
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO<RegisterDTO>> login(@Valid @RequestBody LoginDTO user,HttpServletResponse response,BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body(ResponseDTO.error(result));
        }
        UserEntity existingUser = userService.login(user);
        response.setHeader("Authorization", this.authService.generateToken(existingUser.getId(), 3));
        return ResponseEntity.ok().body(ResponseDTO.success("Login successful",new RegisterDTO(existingUser)));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDTO<RegisterDTO>> register(@Valid @RequestBody RegisterDTO user,HttpServletResponse response,BindingResult result) {
        if(result.hasErrors()) {
            return ResponseEntity.badRequest().body(ResponseDTO.error(result));
        }
        UserEntity newUser = userService.register(user);
        response.setHeader("Authorization", this.authService.generateToken(newUser.getId(), 3));
        return ResponseEntity.ok().body(ResponseDTO.success("Registration successful", new RegisterDTO(newUser)));

    }

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<ResponseDTO<Object>> handleException(ClientException e) {
        return ResponseEntity.status(e.getStatusCode()).body(ResponseDTO.builder().success(false).errors(List.of(e.getMessage())).build());
    }

}
