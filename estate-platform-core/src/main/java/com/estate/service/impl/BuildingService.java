package com.estate.service.impl;

import com.estate.converter.BuildingConverter;
import com.estate.converter.UserConverter;
import com.estate.dto.UserDTO;
import com.estate.entity.UserEntity;
import com.estate.enums.OptionEnum;
import com.estate.repository.UserRepository;
import com.estate.utils.SecurityUtils;
import org.apache.commons.codec.binary.Base64;
import com.estate.dto.BuildingDTO;
import com.estate.entity.BuildingEntity;
import com.estate.repository.BuildingRepository;
import com.estate.service.IBuildingService;
import com.estate.utils.UploadFileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BuildingService implements IBuildingService {

    @Autowired UploadFileUtils uploadFileUtils;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private BuildingConverter buildingConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Value("${dir.default}")
    private String dirDefault;

    @Override
    public List<BuildingDTO> getBuilding(Pageable pageable) {
        List<BuildingDTO> buildingDTOS = buildingRepository.findAll(pageable).getContent().stream().map(item -> buildingConverter.convertToDto(item)).collect(Collectors.toList());
        for(BuildingDTO item:buildingDTOS){
            for(UserDTO user : item.getUsers()){
                if(user.getId() == SecurityUtils.getPrincipal().getId()){
                    item.setPriority(1);
                    break;
                }
            }
        }
        return buildingDTOS;
    }

    @Override
    public List<BuildingDTO> getfavoriteBuilding(Pageable pageable) {
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getPrincipal().getId());
        Page<BuildingEntity> newPage = buildingRepository.findByUsers(pageable,userEntity);
        List<BuildingEntity> buildingEntities = newPage.getContent();
        List<BuildingDTO> buildingDTOS = new ArrayList<>();
        for(BuildingEntity item : buildingEntities){
            BuildingDTO buildingDTO = buildingConverter.convertToDto(item);
            buildingDTOS.add(buildingDTO);
        }
        return buildingDTOS;
    }

    @Override
    public BuildingDTO insert(BuildingDTO buildingDTO) {
        saveImange(buildingDTO);
        buildingDTO.setTypes(StringUtils.join(buildingDTO.getTypeArray(),","));
        buildingDTO.setPriority(0);
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        buildingRepository.save(buildingEntity);
        return buildingDTO;
    }

    @Override
    public BuildingDTO update(BuildingDTO buildingDTO, long id) {
        BuildingEntity owlBuilding = buildingRepository.findOne(id);
        buildingDTO.setId(id);
        buildingDTO.setCreatedBy(owlBuilding.getCreatedBy());
        buildingDTO.setCreatedDate((Timestamp) owlBuilding.getCreatedDate());
        buildingDTO.setTypes(StringUtils.join(buildingDTO.getTypeArray(),","));
        buildingDTO.setPriority(0);
        saveImange(buildingDTO);
        BuildingEntity buildingEntity = buildingConverter.convertToEntity(buildingDTO);
        List<UserEntity> userEntities = new ArrayList<>();
        userEntities.addAll(owlBuilding.getStaffs());
        buildingEntity.setStaffs(userEntities);
        buildingRepository.save(buildingEntity);
        return buildingDTO;
    }

    private void saveImange(BuildingDTO buildingDTO){
        if(buildingDTO.getThumbnailBase64() != null){
            byte[] decodeBase64 = Base64.decodeBase64(buildingDTO.getThumbnailBase64().getBytes());
            String path = "building" + File.separator + buildingDTO.getImageName();
            uploadFileUtils.writeOrUpdate(path,decodeBase64);
            buildingDTO.setThumnail(dirDefault + File.separator + path);
        }
    }

    @Override
    public void delete(long[] ids) {
        for (long item : ids){
            buildingRepository.delete(item);
        }
    }

    @Override
    public void changePriority(long id) {
        BuildingEntity buildingEntity = buildingRepository.findOne(id);
        /*for(UserEntity item:buildingEntity.getUsers()){
            if(item.getId() == SecurityUtils.getPrincipal().getId()){
                buildingEntity.getUsers().remove(item);
                buildingRepository.save(buildingEntity);
                return;
            }
        }*/

        buildingEntity.getUsers().add(userRepository.findOne(SecurityUtils.getPrincipal().getId()));
        buildingRepository.save(buildingEntity);
    }

    @Override
    public int getTotalItems() {
        return (int) buildingRepository.count();
    }

    @Override
    public int getTotalFavoriteItems() {
        return (int) buildingRepository.countByPriority(1);
    }

    @Override
    public BuildingDTO getOneById(long id) {
        BuildingEntity buildingEntity = buildingRepository.findOne(id);
        BuildingDTO buildingDTO = buildingConverter.convertToDto(buildingEntity);
        buildingDTO.setTypeArray(getTypeArray(buildingDTO.getTypes()));
        return buildingDTO;
    }
    private String[] getTypeArray(String title){
        if(title == null){
            return null;
        }
        String[] typeArray = title.split(",");
        return typeArray;
    }

    @Override
    public Map<String, String> getMaptype() {
        Map<String, String > result = new HashMap<>();
        Stream.of(OptionEnum.values()).forEach(type->{
            result.put(type.name(),type.getValue());
        });
        return result;
    }
}
