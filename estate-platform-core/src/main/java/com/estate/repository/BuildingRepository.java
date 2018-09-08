package com.estate.repository;

import com.estate.entity.BuildingEntity;
import com.estate.entity.UserEntity;
import com.estate.repository.Custom.BuildingRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BuildingRepository extends JpaRepository<BuildingEntity, Long>,BuildingRepositoryCustom {
    Page<BuildingEntity> findByStaffs_id(UserEntity userEntity, Pageable pageable);
    long countByPriority(int priority);
    Page<BuildingEntity> findByUsers(Pageable pageable,UserEntity userEntity);
}
