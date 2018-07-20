package com.estate.service;

import com.estate.dto.BuildingDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IBuildingService {
    List<BuildingDTO> getBuilding(Pageable pageable);
    List<BuildingDTO> getfavoriteBuilding(Pageable pageable);
    BuildingDTO insert(BuildingDTO buildingDTO);
    BuildingDTO update(BuildingDTO buildingDTO, long id);
    void delete(long[] ids);
    void changePriority(long id);
    int getTotalItems();
    int getTotalFavoriteItems();
    BuildingDTO getOneById(long id);
    Map<String, String> getMaptype();
}
