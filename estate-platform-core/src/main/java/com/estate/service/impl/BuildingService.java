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
import com.estate.utils.UploadFileUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.Query;
import java.io.File;
import java.util.*;
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
    private UploadFileUtils fileUtils;

    @Value("${dir.default}")
    private String dirDefault;


    @Override
    public List<BuildingDTO> getBuildingsByPrioritize(String searchValue, Pageable pageable,int prioritize) {
        List<BuildingDTO> result = new ArrayList<>();
        Page<BuildingEntity> buildingsPage = null;
            if (searchValue != null) {
                //usersPage = userRepository.findByUserNameOrFullNameOrPhoneOrEmailContainingIgnoreCase(searchValue, searchValue, searchValue, searchValue, pageable);
            } else {
                if(prioritize == 1){
                    buildingsPage = buildingRepository.findByPrioritize(pageable,prioritize);
                }else {
                    buildingsPage = buildingRepository.findAll(pageable);
                }
            }
            for (BuildingEntity item : buildingsPage.getContent()) {
                BuildingDTO buildingDTO = buildingConverter.convertToDto(item);
                buildingDTO.setAddress(buildingDTO.getStreet()+","+buildingDTO.getWard());
                result.add(buildingDTO);
            }

        return result;
    }

//    @Override
//    public List<BuildingDTO> getBuildingsByUser(String searchValue, Pageable pageable, long userId) {
//        List<BuildingDTO> result = new ArrayList<>();
//        Page<BuildingEntity> buildingsPage = null;
//        if (searchValue != null) {
//            //usersPage = userRepository.findByUserNameOrFullNameOrPhoneOrEmailContainingIgnoreCase(searchValue, searchValue, searchValue, searchValue, pageable);
//        } else {
//
//                buildingsPage = buildingRepository.findByStaffs_id(pageable,userId);
//        }
//        for (BuildingEntity item : buildingsPage.getContent()) {
//            BuildingDTO buildingDTO = buildingConverter.convertToDto(item);
//            buildingDTO.setAddress(buildingDTO.getStreet()+","+buildingDTO.getWard());
//            result.add(buildingDTO);
//        }
//
//        return result;
//    }

    @Override
    public int getTotalItems(String searchValue) {
        int totalItem = 0;
        if (searchValue != null) {
            //totalItem = (int) userRepository.countByUserNameOrFullNameOrPhoneOrEmailContainingIgnoreCase(searchValue, searchValue, searchValue, searchValue) - totalItemDelete;
        } else {
            totalItem = (int) buildingRepository.count();
        }
        return totalItem;
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
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        buildingEntity.setDistrict(districtRepository.findOneByCode(buildingDTO.getDistrictCode()));
        setTypeArray(buildingDTO);
        //cắt dấu phẩy ở cuối
        buildingEntity.setTypes(buildingDTO.getTypes().substring(0,buildingDTO.getTypes().length()-1));
        buildingEntity = buildingRepository.save(buildingEntity);
        return buildingConverter.convertToDto(buildingEntity);
    }

    @Override
    @Transactional
    public BuildingDTO  update(BuildingDTO updateBuilding, long id) {
        BuildingEntity oldBuilding = buildingRepository.findOne(id);
        oldBuilding.setAgencyCharge(updateBuilding.getAgencyCharge());
        oldBuilding.setCarParkingCharge(updateBuilding.getCarParkingCharge());
        oldBuilding.setDistrict(districtRepository.findOneByCode(updateBuilding.getDistrictCode()));
        oldBuilding.setPrioritize(updateBuilding.getPrioritize());
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
        setTypeArray(updateBuilding);
        oldBuilding.setTypes(updateBuilding.getTypes().substring(0,updateBuilding.getTypes().length()-1));
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
    public BuildingDTO insertStaffBuilding(String users, long id) {
        BuildingEntity entity = buildingRepository.findOne(id);
        users = users.substring(1,users.length()-1);
        String [] arrayUser = users.split(",");
        List<UserEntity> userEntities = new ArrayList<>();
        for (String s : arrayUser) {
            UserEntity userEntity = userRepository.findOneByUserName(s);
            userEntities.add(userEntity);
        }
        entity.setStaffs(userEntities);
        buildingRepository.save(entity);
        return buildingConverter.convertToDto(entity);
    }


    @Override
    public BuildingDTO findBuildingById(long id) {
        BuildingEntity entity = buildingRepository.findOne(id);
        BuildingDTO dto = buildingConverter.convertToDto(entity);
        dto.setDistrictCode(entity.getDistrict().getCode());
        dto.setDistricts(districtService.getDistricts());
        return dto;
    }

    @Override
    public void deleteBuilding(long[] ids) {
        for (long item : ids) {
            buildingRepository.delete(item);
        }
    }

    void setTypeArray(BuildingDTO buildingDTO){
        String [] typeArray = buildingDTO.getTypeArrays();
        String temp = "";
        for (String s : typeArray) {
            temp += (s + ",");
        }
        buildingDTO.setTypes(temp);
    }

}
