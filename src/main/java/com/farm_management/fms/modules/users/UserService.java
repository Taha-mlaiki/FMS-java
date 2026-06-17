package com.farm_management.fms.modules.users;


import com.farm_management.fms.common.exceptions.ApiException;
import com.farm_management.fms.modules.users.dtos.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.farm_management.fms.modules.users.dtos.UserRequest;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository ;
    public UserResponse createUser(UserRequest userData){
           User user = new User();
           user.setFullName(userData.getFullName());
           user.setEmail(userData.getEmail());
           user.setPassword(userData.getPassword());
           User userRes =  createUserInternal(user);
           return new UserResponse(userRes.getId(),userRes.getFullName(),userRes.getEmail());
    }
    public User createUserInternal(User userData){
        if(userRepository.existsByEmail(userData.getEmail())){
            throw new ApiException(HttpStatus.CONFLICT, "This email already exist");
        }
        return userRepository.save(userData);
    }

    public UserResponse updateUser(Long userId , UserRequest userData){
        User user = userRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found"));
        user.setFullName(userData.getFullName());
        user.setEmail(userData.getEmail());
        userRepository.save(user);
        return new UserResponse(user.getId(),user.getFullName(),user.getEmail());
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(()-> new ApiException(HttpStatus.NOT_FOUND, "No user found with this email"));
    }
}
