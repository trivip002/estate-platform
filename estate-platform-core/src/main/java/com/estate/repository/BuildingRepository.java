package com.estate.repository;

import com.estate.entity.BuildingEntity;
import com.estate.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {
    Page<BuildingEntity> findByPriority(Pageable pageable, int priority);
    long countByPriority(int priority);
    Page<BuildingEntity> findByUsers(Pageable pageable,UserEntity userEntity);
}
