package com.farm_management.fms.modules.auth;

import com.farm_management.fms.common.exceptions.InvalidCredentialsException;
import com.farm_management.fms.common.utils.HashUtil;
import com.farm_management.fms.modules.auth.dto.AuthResponse;
import com.farm_management.fms.modules.auth.dto.LoginRequest;
import com.farm_management.fms.modules.auth.dto.RegisterRequest;
import com.farm_management.fms.modules.users.User;
import com.farm_management.fms.modules.users.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final UserService userService ;

    public AuthResponse register(RegisterRequest registerReq){
        User user = new User();
        user.setFullName(registerReq.getFullName());
        user.setEmail(registerReq.getEmail());
        // the password will hash it in the next version
        String hashedPassword = HashUtil.hashPassword(registerReq.getPassword());
        user.setPassword(hashedPassword);

        User userRes = userService.createUserInternal(user);

        return new AuthResponse(userRes.getFullName(), userRes.getEmail());
    }

    public AuthResponse login(LoginRequest body){
        User user = userService.getUserByEmail(body.getEmail());
        if(!HashUtil.verifyPassword(user.getPassword(),body.getPassword())){
            throw new InvalidCredentialsException("Invalid Credentials");
        }
        return new AuthResponse(user.getFullName(), user.getEmail());
    }
}
