package com.estate.repository;

import com.estate.entity.BuildingEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<BuildingEntity, Long> {
    Page<BuildingEntity> findByPrioritize(Pageable pageable,int prioritize);
    Page<BuildingEntity> findByStaffs_id(Pageable pageable,long userId);
}

