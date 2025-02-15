package com.raghu.ride.models;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginDTO {
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phone;
    
    private String password;
}
