package com.estate.repository.Custom;

import com.estate.Builder.BuildingBuilder;
import com.estate.entity.BuildingEntity;
import com.estate.paging.Pageable;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<?> findAll(BuildingBuilder builder, Pageable pageable);
    Integer getTotalItems(BuildingBuilder buildingBuilder );
    List<BuildingEntity> findByStaffs_id(BuildingBuilder buildingBuilder, Pageable pageable,long userId);
    Integer getTotalItemsByStaffs_id(BuildingBuilder buildingBuilder, long userId);
}
