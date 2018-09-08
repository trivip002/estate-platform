package com.estate.service.impl;

import com.estate.converter.BuildingConverter;
import com.estate.dto.BuildingDTO;
import com.estate.dto.UserDTO;
import com.estate.entity.BuildingEntity;
import com.estate.entity.UserEntity;
import com.estate.enums.ETypes;
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
import javax.persistence.Query;
import java.io.File;
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
    public List<BuildingDTO> getBuildingsByPrioritizeAndUser(String searchValue, Pageable pageable,int prioritize) {
        List<BuildingDTO> result = new ArrayList<>();
        Page<BuildingEntity> buildingsPage = null;
        getUserAndRole();
            if (searchValue != null) {
                //usersPage = userRepository.findByUserNameOrFullNameOrPhoneOrEmailContainingIgnoreCase(searchValue, searchValue, searchValue, searchValue, pageable);
            } else {
                if(prioritize == 1){
                    buildingsPage = buildingRepository.findByStaffsPrioritize_id(pageable,userId);
                }else{
                    if(isManager){ // manager
                        buildingsPage = buildingRepository.findAll(pageable);
                    }else {// user
                        buildingsPage = buildingRepository.findByStaffs_id(pageable,userId);
                    }
                }
            }
            for (BuildingEntity item : buildingsPage.getContent()) {
                BuildingDTO buildingDTO = buildingConverter.convertToDto(item);
                if(prioritize != 1){
                    if (userRepository.existsByIdAndBuildingsPrioritize_Id(SecurityUtils.getPrincipal().getId(), item.getId())) {
                        buildingDTO.setPrioritize(1);
                    }
                }
                buildingDTO.setDistrictName(districtRepository.findOneByCode(item.getDistrict()).getName());
                buildingDTO.setAddress(buildingDTO.getStreet()+","+buildingDTO.getWard()+","+buildingDTO.getDistrictName());
                result.add(buildingDTO);
            }
        return result;
    }


    @Override
    public int getTotalItems(String searchValue,int prioritize) {
        int totalItem = 0;
        getUserAndRole();
        if (searchValue != null) {
            //totalItem = (int) userRepository.countByUserNameOrFullNameOrPhoneOrEmailContainingIgnoreCase(searchValue, searchValue, searchValue, searchValue) - totalItemDelete;
        } else {
            if(prioritize == 1){
                totalItem =(int) buildingRepository.countByStaffsPrioritize_id(userId);
            }
            else{
                if(isManager){ // manager
                    totalItem = (int) buildingRepository.count();

                }else {// user
                    totalItem =(int) buildingRepository.countByStaffs_id(userId);
                }
            }
        }
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
        oldBuilding.setAgencyCharge(updateBuilding.getAgencyCharge());
        oldBuilding.setCarParkingCharge(updateBuilding.getCarParkingCharge());
        oldBuilding.setDistrict(updateBuilding.getDistrict());
        oldBuilding.setAgencyCharge(updateBuilding.getAgencyCharge());
        oldBuilding.setDeposit(updateBuilding.getDeposit());
        oldBuilding.setDescription(updateBuilding.getDescription());
        oldBuilding.setDirection(updateBuilding.getDirection());
        oldBuilding.setElectricCharge(updateBuilding.getElectricCharge());
        oldBuilding.setFloorArea(updateBuilding.getFloorArea());
        oldBuilding.setExtraCharge(updateBuilding.getExtraCharge());
        oldBuilding.setLink(updateBuilding.getLink());
        oldBuilding.setManagerName(updateBuilding.getManagerName());
        oldBuilding.setManagerPhone(updateBuilding.getManagerPhone());
        oldBuilding.setServiceFee(updateBuilding.getServiceFee());
        oldBuilding.setMap(updateBuilding.getMap());
        oldBuilding.setMotorParkingCharge(updateBuilding.getMotorParkingCharge());
        oldBuilding.setCarParkingCharge(updateBuilding.getCarParkingCharge());
        oldBuilding.setWard(updateBuilding.getWard());
        oldBuilding.setStreet(updateBuilding.getStreet());
        oldBuilding.setStructure(updateBuilding.getStructure());
        oldBuilding.setTimeForDecorate(updateBuilding.getTimeForDecorate());
        oldBuilding.setType(updateBuilding.getType());
        oldBuilding.setTimeForRent(updateBuilding.getTimeForRent());
        oldBuilding.setName(updateBuilding.getName());
        oldBuilding.setPayment(updateBuilding.getPayment());
        oldBuilding.setRentArea(updateBuilding.getRentArea());
        oldBuilding.setPrice(updateBuilding.getPrice());
        oldBuilding.setTypes(StringUtils.join(updateBuilding.getTypeArrays(), ","));
        if (StringUtils.isNotEmpty(updateBuilding.getImageName())) { // co thay doi hinh
            saveImage(updateBuilding);
            oldBuilding.setAvatar(updateBuilding.getAvatar());
        }
        oldBuilding = buildingRepository.save(oldBuilding);
        return buildingConverter.convertToDto(oldBuilding);
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
        dto.setDistrictName(districtRepository.findOneByCode(entity.getDistrict()).getName());
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
