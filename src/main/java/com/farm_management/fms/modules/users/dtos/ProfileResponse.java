package com.farm_management.fms.modules.users.dtos;

import com.farm_management.fms.common.enums.Role;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
public class ProfileResponse {
    public Long id;
    public String fullName;
    public String email;
    public Role role;
    public LocalDateTime CreateAt;
    public LocalDateTime updatedAt;
}
