package com.farm_management.fms.modules.users;

import com.farm_management.fms.common.validation.OnCreate;
import com.farm_management.fms.common.validation.OnUpdate;
import com.farm_management.fms.modules.users.dto.UserRequest;
import com.farm_management.fms.modules.users.dto.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
public class UserController {

    final private UserService userService ;

    public  UserController(UserService userService){
        this.userService = userService ;
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(
            @Validated(OnCreate.class) @RequestBody UserRequest body
    ){
        return userService.createUser(body);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(
            @PathVariable("userId") Long userId,
            @Validated(OnUpdate.class) @RequestBody UserRequest body
    ){
        return userService.updateUser(userId,body);
    }

}
