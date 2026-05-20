package com.farm_management.fms.modules.auth;

import com.farm_management.fms.common.exceptions.InvalidCredentialsException;
import com.farm_management.fms.modules.auth.dto.AuthResponse;
import com.farm_management.fms.modules.auth.dto.LoginRequest;
import com.farm_management.fms.modules.auth.dto.RegisterRequest;
import com.farm_management.fms.modules.users.User;
import com.farm_management.fms.modules.users.UserService;
import com.farm_management.fms.modules.users.domain.CreateUserCommand;
import com.farm_management.fms.modules.users.dto.UserRequest;
import com.farm_management.fms.modules.users.dto.UserResponse;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserService userService ;

    public AuthResponse register(RegisterRequest registerReq){
        User user = new User();
        user.setFullName(registerReq.getFullName());
        user.setEmail(registerReq.getEmail());
        // the password will hash it in the next version
        user.setPassword(registerReq.getPassword());

        User userRes = userService.createUserInternal(user);

        return new AuthResponse(userRes.getFullName(), userRes.getEmail());
    }

    public AuthResponse login(LoginRequest body){
        User user = userService.getUserByEmail(body.getEmail());
        if(!user.getPassword().equals(body.getPassword())){
            throw new InvalidCredentialsException("Invalid Credentials");
        }
        return new AuthResponse(user.getFullName(), user.getEmail());
    }
}
