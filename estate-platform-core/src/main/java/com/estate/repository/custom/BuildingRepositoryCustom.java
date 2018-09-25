package com.estate.repository.custom;

import com.estate.builder.BuildingBuilder;
import com.estate.entity.BuildingEntity;
import com.estate.paging.Pageable;

import java.util.List;

public interface BuildingRepositoryCustom {
    List<?> findAll(BuildingBuilder buildingBuilder, Pageable pageable,boolean priority);
    Long getTotalItems(BuildingBuilder buildingBuilder,boolean priority);
}
