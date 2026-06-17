package com.farm_management.fms.modules.farms;


import com.farm_management.fms.common.validation.OnCreate;
import com.farm_management.fms.modules.farms.dtos.FarmRequestDto;
import com.farm_management.fms.modules.farms.dtos.FarmResponseDto;
import com.farm_management.fms.modules.users.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/farms")
public class FarmController {
    final private FarmService farmService;

    @PostMapping("")
    @PreAuthorize("hasAnyRole('OWNER')")
    public FarmResponseDto CreateFarm(
            @AuthenticationPrincipal User currentUser,
            @Validated(OnCreate.class) @RequestBody FarmRequestDto body
            ){
            return this.farmService.createFarm(currentUser.getId(),body);
    }
}
