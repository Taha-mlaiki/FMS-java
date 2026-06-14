package com.farm_management.fms.modules.auth;

import com.farm_management.fms.common.exceptions.InvalidCredentialsException;
import com.farm_management.fms.common.utils.HashUtil;
import com.farm_management.fms.common.utils.JwtUtil;
import com.farm_management.fms.modules.auth.dto.LoginResponse;
import com.farm_management.fms.modules.auth.dto.RegisterResponse;
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
    private final JwtUtil jwtUtil;
    public RegisterResponse register(RegisterRequest registerReq){
        User user = new User();
        user.setFullName(registerReq.getFullName());
        user.setEmail(registerReq.getEmail());
        // the password will hash it in the next version
        String hashedPassword = HashUtil.hashPassword(registerReq.getPassword());
        user.setPassword(hashedPassword);

        User userRes = userService.createUserInternal(user);
        String token = jwtUtil.generateToken(userRes);
        return new RegisterResponse("Loged in successfully",200);
    }

    public LoginResponse login(LoginRequest body){
        User user = userService.getUserByEmail(body.getEmail());
        if(!HashUtil.verifyPassword(user.getPassword(),body.getPassword())){
            throw new InvalidCredentialsException("Invalid Credentials");
        }
        String token = jwtUtil.generateToken(user);
        return new LoginResponse(token,"Loged in successfully");
    }
}
