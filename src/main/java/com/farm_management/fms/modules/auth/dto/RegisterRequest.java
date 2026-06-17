package com.farm_management.fms.modules.auth.dto;

import com.farm_management.fms.common.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterRequest {

    @NotBlank(message = "full name is required")
    @Size(
            min = 3,
            message = "fullName must at least contain 3 characters"
    )
    private String fullName;

    @Email(message = "invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "role must be OWNER or WORKER")
    private Role role;

    @NotBlank(message = "password is required")
    @Size(
            min = 8,
            message = "password must at least contain 8 characters"
    )
    private String password;
}