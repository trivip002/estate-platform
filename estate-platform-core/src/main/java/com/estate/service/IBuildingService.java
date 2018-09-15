package com.estate.service;

import com.estate.dto.BuildingDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IBuildingService  {
    List<BuildingDTO> getBuildingsByPrioritizeAndUser(Pageable pageable,int prioritize);
    List<BuildingDTO> searchBuildingsByPrioritizeAndUser(BuildingDTO model,int prioritize);
    int getTotalItems(int prioritize);
    int getTotalItemsSearch(BuildingDTO model,int prioritize);
    BuildingDTO insert(BuildingDTO buildingDTO);
    BuildingDTO update(BuildingDTO updateBuilding, long id);
    BuildingDTO findBuildingById(long id);
    void deleteBuilding(long[] ids);
    Map<String,String> getBuildingTypes();
    void assignBuildingToStaff(String[] users,long id);
    void updatePriority(String action,long id);
}
