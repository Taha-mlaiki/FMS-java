package com.farm_management.fms.modules.farms;

import com.farm_management.fms.common.exceptions.ApiException;
import com.farm_management.fms.modules.farms.dtos.FarmRequestDto;
import com.farm_management.fms.modules.farms.dtos.FarmResponseDto;
import com.farm_management.fms.modules.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FarmService {
    final private FarmRepository farmRepo;

    public FarmResponseDto createFarm(Long userId , FarmRequestDto farmDto){
        if(farmRepo.existsByName(farmDto.getName())){
            throw new ApiException(HttpStatus.CONFLICT,"This farm name already used");
        };
        Farm farm = new Farm();
        User user = new User();
        user.setId(userId);
        farm.setName(farmDto.getName());
        farm.setDescription(farmDto.getDescription());
        farm.setUser(user);
        farmRepo.save(farm);
        return new FarmResponseDto(
                farm.getId(),
                farm.getName(),
                farm.getDescription(),
                farm.getCreatedAt(),
                farm.getUpdatedAt()
        );
    }

}
