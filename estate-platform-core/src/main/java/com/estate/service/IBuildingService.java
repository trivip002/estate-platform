package com.estate.service;

import com.estate.dto.BuildingDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IBuildingService  {
    List<BuildingDTO> getBuildingsByPrioritizeAndUser(String searchValue, Pageable pageable,int prioritize,Long userId,boolean isManager);
    int getTotalItems(String searchValue);
    BuildingDTO insert(BuildingDTO buildingDTO);
    BuildingDTO update(BuildingDTO updateBuilding, long id);
    BuildingDTO findBuildingById(long id);
    void deleteBuilding(long[] ids);
    Map<String,String> getBuildingTypes();
    BuildingDTO insertStaffBuilding(String users,long id);
    BuildingDTO updateStaffBuildingPrioritize(long userId,long id,boolean update);

}
