package com.estate.service.impl;

import com.estate.converter.RoleConverter;
import com.estate.dto.RoleDTO;
import com.estate.dto.UserDTO;
import com.estate.entity.RoleEntity;
import com.estate.repository.RoleRepository;
import com.estate.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleConverter roleConverter;


    @Override
    public Map<String, String> getAllName() {
        List<RoleEntity> roleEntities = roleRepository.findAll();
        Map<String, String> listRole = new HashMap<>();
        for(RoleEntity item:roleEntities)
        {
            listRole.put(item.getCode(), item.getName());
        }
        return listRole;
    }
}
