package com.raghu.ride.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

import org.springframework.validation.BindingResult; 

@Data
@Builder
public class ResponseDTO<T> {
    private T data;
    private String message;
    private boolean success;
    private List<String> errors;

    public static <T> ResponseDTO<T> success(String message, T data) {
        return ResponseDTO.<T>builder()
            .success(true)
            .message(message)
            .data(data)
            .build();
    }

    public static <T> ResponseDTO<T> error(BindingResult result) {
        return ResponseDTO.<T>builder()
            .data(null)
            .success(false)
            .errors(result.getAllErrors().stream().map(e -> e.getDefaultMessage()).toList())
            .build();
    }

}
