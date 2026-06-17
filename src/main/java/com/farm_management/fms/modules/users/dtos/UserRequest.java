package com.farm_management.fms.modules.users.dtos;

import com.farm_management.fms.common.validation.OnCreate;
import com.farm_management.fms.common.validation.OnUpdate;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @NotBlank(message = "Full name cannot be empty", groups = {OnCreate.class, OnUpdate.class})
    private String fullName ;

    @Email(message = "Email should be correct",groups = {OnCreate.class,OnUpdate.class})
    @NotBlank(message = "Email is required",groups = {OnCreate.class})
    private String email;

    @NotBlank(message = "password is required",groups = {OnCreate.class})
    @Size(min = 6,groups = {OnCreate.class,OnUpdate.class})
    private String password;
}
