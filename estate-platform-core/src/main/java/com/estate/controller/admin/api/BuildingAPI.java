package com.estate.controller.admin.api;

import com.estate.dto.BuildingDTO;
import com.estate.service.IBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/admin/building")
public class BuildingAPI {

    @Autowired
    private IBuildingService buildingService;


    @PostMapping
    public ResponseEntity<BuildingDTO> createBuilding(@RequestBody BuildingDTO buildingDTO) {
        return ResponseEntity.ok(buildingService.insert(buildingDTO));

    }

    @PutMapping("/{id}")
    public ResponseEntity<BuildingDTO> updateBuilding(@RequestBody BuildingDTO buildingDTO, @PathVariable("id") long id) {
        return ResponseEntity.ok(buildingService.update(buildingDTO, id));

    }

    @DeleteMapping
    public ResponseEntity<String> deleteBuilding(@RequestBody long[] ids) {
        if (ids.length > 0) {
            buildingService.deleteBuilding(ids);
        }
        return ResponseEntity.ok("success");
    }


    @PutMapping("/user/{id}")
    public ResponseEntity<BuildingDTO> updateBuildingStaffs(@RequestBody String users, @PathVariable("id") long id) {
        return ResponseEntity.ok(buildingService.insertStaffBuilding(users, id));

    }

    @PutMapping("/prioritize/{id}")
    public ResponseEntity<BuildingDTO> updateBuildingStaffsPrioritize(@RequestBody long userId, @PathVariable("id") long id) {
        return ResponseEntity.ok(buildingService.updateStaffBuildingPrioritize(userId, id,true));

    }

    @PutMapping("/delete/prioritize/{id}")
    public ResponseEntity<BuildingDTO> deleteBuildingStaffsPrioritize(@RequestBody long userId, @PathVariable("id") long id) {
        return ResponseEntity.ok(buildingService.updateStaffBuildingPrioritize(userId, id,false));

    }
}
