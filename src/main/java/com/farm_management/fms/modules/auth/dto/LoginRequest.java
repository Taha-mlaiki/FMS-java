package com.farm_management.fms.modules.auth.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginRequest {

    @Email
    @NotBlank(message = "Email is required")
    private String email ;

    @NotBlank(message = "password is required")
    @Size(min = 8,message = "password must at least contain 8 characters")
    private String password ;
}
