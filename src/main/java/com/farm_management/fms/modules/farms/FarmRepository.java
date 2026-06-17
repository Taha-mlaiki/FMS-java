package com.farm_management.fms.modules.farms;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FarmRepository extends JpaRepository<Farm,Long> {
     boolean existsByName(String name);
}
