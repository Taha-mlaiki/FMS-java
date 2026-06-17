package com.farm_management.fms.modules.farms.dtos;

import com.farm_management.fms.common.validation.OnCreate;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FarmRequestDto {

    @NotBlank(message = "farm name should not be empty",groups = {OnCreate.class})
    private String name;

    private String description;
}
