package com.estate.service.impl;

import com.estate.builder.BuildingBuilder;
import com.estate.converter.BuildingConverter;
import com.estate.dto.BuildingDTO;
import com.estate.entity.BuildingEntity;
import com.estate.entity.UserEntity;
import com.estate.enums.ETypes;
import com.estate.paging.PageRequest;
import com.estate.repository.BuildingRepository;
import com.estate.repository.DistrictRepository;
import com.estate.repository.UserRepository;
import com.estate.service.IBuildingService;
import com.estate.service.IDistrictService;
import com.estate.service.IUserService;
import com.estate.utils.SecurityUtils;
import com.estate.utils.UploadFileUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.security.Timestamp;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BuildingService implements IBuildingService {

    private Logger logger = Logger.getLogger(BuildingService.class);


    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private IDistrictService districtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private UploadFileUtils fileUtils;

    @Value("${dir.default}")
    private String dirDefault;

    private Long userId;
    private boolean isManager;


    @Override
    public List<BuildingDTO> searchBuildingsAssignment(BuildingDTO modelSearch, boolean isAssignment) {
        getUserAndRole();
        com.estate.paging.Pageable pageableCustom = new PageRequest(modelSearch.getPage(), modelSearch.getMaxPageItems());
        List<?> buildingEntities = null;
        List<BuildingDTO> result = new ArrayList<>();
        if(!isManager && isAssignment) {
            modelSearch.setStaffName(userRepository.findOne(userId).getUserName());
        }
        //ko tính ưu tiên
        buildingEntities = buildingRepository.findAll(getBuildingBuilder(modelSearch), pageableCustom,false);
        //
        for (Object item : buildingEntities) {
            BuildingEntity buildingEntity = new BuildingEntity();
            try {
                buildingEntity = (BuildingEntity)item;
            }catch (Exception e){
                buildingEntity = (BuildingEntity)((Object[])item)[0];
            }
            BuildingDTO buildingDTO = buildingConverter.convertToDto(buildingEntity);
            if (userRepository.existsByIdAndBuildingsPrioritize_Id(SecurityUtils.getPrincipal().getId(), buildingEntity.getId())) {
                buildingDTO.setPrioritize(1);
            }
            buildingDTO.setAddress(buildingDTO.getStreet()+","+buildingDTO.getWard()+","+buildingDTO.getDistrict());
            result.add(buildingDTO);
        }

        return result;
    }

    @Override
    public List<BuildingDTO> searchBuildingsByPriority(BuildingDTO modelSearch) {
        getUserAndRole();
        com.estate.paging.Pageable pageableCustom = new PageRequest(modelSearch.getPage(), modelSearch.getMaxPageItems());
        List<?> buildingEntities = null;
        List<BuildingDTO> result = new ArrayList<>();
        modelSearch.setUserId(userId);
        //tính ưu tiên
        buildingEntities = buildingRepository.findAll(getBuildingBuilder(modelSearch), pageableCustom,true);
        //
        for (Object item : buildingEntities) {
            BuildingEntity buildingEntity = new BuildingEntity();
            try {
                buildingEntity = (BuildingEntity)item;
            }catch (Exception e){
                buildingEntity = (BuildingEntity)((Object[])item)[0];
            }
            BuildingDTO buildingDTO = buildingConverter.convertToDto(buildingEntity);
            buildingDTO.setAddress(buildingDTO.getStreet()+","+buildingDTO.getWard()+","+buildingDTO.getDistrict());
            result.add(buildingDTO);
        }
        return result;
    }

    private BuildingBuilder getBuildingBuilder(BuildingDTO modelSearch) {
        return new BuildingBuilder.Builder()
                .setName(modelSearch.getName())
                .setDistrict(modelSearch.getDistrict())
                .setWard(modelSearch.getWard())
                .setStreet(modelSearch.getStreet())
                .setFloorArea(modelSearch.getFloorArea())
                .setDirection(modelSearch.getDirection())
                .setType(modelSearch.getType())
                .setAreaFrom(modelSearch.getAreaFrom())
                .setAreaTo(modelSearch.getAreaTo())
                .setPriceFrom(modelSearch.getPriceFrom())
                .setPriceTo(modelSearch.getPriceTo())
                .setManagerName(modelSearch.getManagerName())
                .setManagerPhone(modelSearch.getManagerPhone())
                .setTypeArrays(modelSearch.getTypeArrays())
                .setStaffName(modelSearch.getStaffName())
                .setUserId(modelSearch.getUserId())
                .build();
    }



    @Override
    public int getTotalItemsAssignment(BuildingDTO modelSearch,boolean isAssignment) {
        int totalItem = 0;
        getUserAndRole();
        if(!isManager && isAssignment){
            modelSearch.setStaffName(userRepository.findOne(userId).getUserName());
        }
        totalItem = buildingRepository.getTotalItems(getBuildingBuilder(modelSearch),false).intValue();
        return totalItem;
    }

    @Override
    public int getTotalItemsPriority(BuildingDTO modelSearch) {
        int totalItem = 0;
        getUserAndRole();
        modelSearch.setUserId(userId);
        totalItem = buildingRepository.getTotalItems(getBuildingBuilder(modelSearch),true).intValue();
        return totalItem;
    }


    void getUserAndRole(){
        userId = SecurityUtils.getPrincipal().getId();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        isManager = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("MANAGER"));
    }


    private void saveImage(BuildingDTO buildingDTO){
        if(buildingDTO.getAvatarBase64() != null){
            byte[] decodeBase64 = Base64.decodeBase64(buildingDTO.getAvatarBase64());
            String path = "building" + File.separator + buildingDTO.getImageName();
            fileUtils.writeOrUpdate(path,decodeBase64);
            buildingDTO.setAvatar(dirDefault + File.separator + path);
        }
    }



    @Override
    @Transactional
    public BuildingDTO insert(BuildingDTO buildingDTO) {
        saveImage(buildingDTO);
        buildingDTO.setPrioritize(0);
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        buildingEntity.setTypes(StringUtils.join(buildingDTO.getTypeArrays(), ","));
        buildingEntity = buildingRepository.save(buildingEntity);
        return buildingConverter.convertToDto(buildingEntity);
    }

    @Override
    @Transactional
    public BuildingDTO  update(BuildingDTO updateBuilding, long id) {
        BuildingEntity oldBuilding = buildingRepository.findOne(id);
        updateBuilding.setId(id);
        updateBuilding.setCreatedBy(oldBuilding.getCreatedBy());
        updateBuilding.setCreatedDate((java.sql.Timestamp) oldBuilding.getCreatedDate());
        updateBuilding.setTypes(StringUtils.join(updateBuilding.getTypeArrays(),","));
        updateBuilding.setPrioritize(0);
        saveImage(updateBuilding);
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(updateBuilding);
        buildingEntity.setStaffs(oldBuilding.getStaffs());
        buildingEntity.setStaffsPrioritize(oldBuilding.getStaffsPrioritize());
        buildingRepository.save(buildingEntity);
        return buildingConverter.convertToDto(buildingEntity);

    }


    @Override
    public Map<String, String> getBuildingTypes() {
        Map<String,String> results = new HashMap<>();
        Stream.of(ETypes.values()).forEach(type -> {
            results.put(type.name(), type.getValue());
        });
        return results;
    }

    @Override
    public void assignBuildingToStaff(String[] users, long id) {
        BuildingEntity building = buildingRepository.findOne(id);
        List<UserEntity> userEntities = new ArrayList<>();
        for (String item: users) {
            userEntities.add(userRepository.findOneByUserName(item));
        }
        building.setStaffs(userEntities);
        buildingRepository.save(building);
    }

    @Override
    public void updatePriority(String action,long id) {
        BuildingEntity building = buildingRepository.findOne(id);
        UserEntity user = userRepository.findOne(SecurityUtils.getPrincipal().getId());
        if (action.equals("add")) {
            building.setStaffsPrioritize(Stream.of(user).collect(Collectors.toList()));
            user.setBuildingsPrioritize(Stream.of(building).collect(Collectors.toList()));

        } else if (action.equals("remove")) {
            List<UserEntity> listUsers = building.getStaffsPrioritize();
            List<BuildingEntity> listBuildings = user.getBuildingsPrioritize();
            for (UserEntity item: listUsers) {
                if(item.getId() == user.getId()){
                    listUsers.remove(item);
                    break;
                }
            }
            for (BuildingEntity item: listBuildings) {
                if(item.getId() == id){
                    listBuildings.remove(item);
                    break;
                }
            }
            building.setStaffsPrioritize(listUsers);
            user.setBuildingsPrioritize(listBuildings);
        }
        userRepository.save(user);
        buildingRepository.save(building);
    }



    @Override
    public BuildingDTO findBuildingById(long id) {
        BuildingEntity entity = buildingRepository.findOne(id);
        BuildingDTO dto = buildingConverter.convertToDto(entity);
        dto.setDistrict(entity.getDistrict());
        dto.setDistricts(districtService.getDistricts());
        return dto;
    }

    @Override
    public void deleteBuilding(long[] ids) {
        for (long item : ids) {
            buildingRepository.delete(item);
        }
    }



}
