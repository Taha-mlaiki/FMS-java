package com.farm_management.fms.modules.users;

import com.farm_management.fms.modules.users.dto.ProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('WORKER')")
    public ProfileResponse getUserProfile(@AuthenticationPrincipal User currentUser){
        return new ProfileResponse(
                currentUser.getId(),
                currentUser.getFullName(),
                currentUser.getEmail(),
                currentUser.getRole(),
                currentUser.getCreatedAt(),
                currentUser.getUpdatedAt()
        );
    }
}
