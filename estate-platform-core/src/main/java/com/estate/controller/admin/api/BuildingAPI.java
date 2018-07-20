package com.estate.controller.admin.api;


import com.estate.dto.BuildingDTO;
import com.estate.service.impl.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "apiBuildingOfAdmin")
@RequestMapping(value = "/api/admin/building")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;
    @PostMapping
    public ResponseEntity<BuildingDTO> createBuilding(@RequestBody BuildingDTO buildingDTO){
        return ResponseEntity.ok(buildingService.insert(buildingDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BuildingDTO> updateBuilding(@RequestBody BuildingDTO buildingDTO, @PathVariable("id") long id){
        return ResponseEntity.ok(buildingService.update(buildingDTO,id));
    }
    @PutMapping
    public ResponseEntity<String> changePriority(@RequestBody long id ){
        buildingService.changePriority(id);
        return ResponseEntity.ok("success");
    }
    @DeleteMapping
    public ResponseEntity<String> deleteBuilding(@RequestBody long[] ids){
        if(ids.length > 0){
            buildingService.delete(ids);
        }
        return ResponseEntity.ok("success");
    }
}
