package com.farm_management.fms.modules.farms.dtos;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.CurrentTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
public class FarmResponseDto {
    public Long id;
    public String name;
    public String description;
    public LocalDateTime createAt;
    public LocalDateTime updateAt;
}
