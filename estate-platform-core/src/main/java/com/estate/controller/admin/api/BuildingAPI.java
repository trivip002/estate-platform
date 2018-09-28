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

    @PostMapping("/{id}/users")
    public ResponseEntity<Void> assignBuildingToStaff(@RequestBody Long[] userId, @PathVariable("id") long id) {
        buildingService.assignBuildingToStaff(userId, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/priority")
    public ResponseEntity<Void> updatePriority(@RequestParam(value = "action", required = false) String action, @PathVariable("id") long id) {
        buildingService.updatePriority(action, id);
        return ResponseEntity.noContent().build();
    }
}
