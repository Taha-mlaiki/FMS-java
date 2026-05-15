package com.farm_management.fms.modules.users;


import com.farm_management.fms.common.exceptions.EmailAlreadyExistException;
import com.farm_management.fms.modules.users.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.farm_management.fms.modules.users.dto.UserRequest;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository ;

    public UserResponse createUser(UserRequest userData){
            if(userRepository.existsByEmail(userData.getEmail())){
                throw new EmailAlreadyExistException("This email already exist");
            };

           User user = new User();
           user.setFullName(userData.getFullName());
           user.setEmail(userData.getEmail());
           user.setPassword(userData.getPassword());
           userRepository.save(user);
           return new UserResponse(user.getId(),user.getFullName(),user.getEmail());
    }

    public UserResponse updateUser(Long userId , UserRequest userData){
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        user.setFullName(userData.getFullName());
        user.setEmail(userData.getEmail());
        userRepository.save(user);
        return new UserResponse(user.getId(),user.getFullName(),user.getEmail());
    }
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
