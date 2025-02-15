package com.raghu.ride.models;


import com.raghu.ride.entity.UserEntity;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegisterDTO {

    public RegisterDTO() {
    }

    public RegisterDTO(UserEntity u) {
        this.firstName = u.getFirstName();
        this.lastName = u.getLastName();
        this.phone = u.getPhone();
    }

    @Size(min = 5, max = 50, message = "First name must be between 5 and 50 characters long")
    private String firstName;

    @Size(min = 3, max = 50, message = "Last name must be between 3 and 50 characters long")
    private String lastName;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
    private String phone;

    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).+$", message = "Password must contain at least one uppercase letter, one lowercase letter, and one special character.")
    private String password;
    


}
