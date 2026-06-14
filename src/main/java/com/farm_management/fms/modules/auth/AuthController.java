package com.farm_management.fms.modules.auth;

import com.farm_management.fms.modules.auth.dto.LoginResponse;
import com.farm_management.fms.modules.auth.dto.RegisterResponse;
import com.farm_management.fms.modules.auth.dto.LoginRequest;
import com.farm_management.fms.modules.auth.dto.RegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    final private AuthService authService ;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(
            @Valid @RequestBody RegisterRequest requestBody
            ){
        return authService.register(requestBody);
    }
    @PostMapping("/login")
    public LoginResponse login(
             @Valid @RequestBody LoginRequest body
            ){
        return authService.login(body);
    }
}
