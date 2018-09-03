package com.estate.service.impl;

import com.estate.constant.SystemConstant;
import com.estate.controller.utils.RandomGenerator;
import com.estate.controller.utils.Md5Utils;
import com.estate.converter.RoleConverter;
import com.estate.converter.UserConverter;
import com.estate.dto.UserDTO;
import com.estate.entity.UserEntity;
import com.estate.repository.RoleRepository;
import com.estate.repository.UserRepository;
import com.estate.service.IRoleService;
import com.estate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.estate.exception.MyException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleConverter roleConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getAll() {
        List<UserDTO> userDTOS = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        for (UserEntity item : userEntities) {
            UserDTO userDTO = userConverter.convertToDto(item);
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public List<UserDTO> getUsers(String searchValue, Pageable pageable) {
        List<UserDTO> result = new ArrayList<>();
        Page<UserEntity> usersPage = null;
        if (searchValue != null) {
            usersPage = userRepository.findByUserNameOrFullNameOrPhoneOrEmailContainingIgnoreCase(searchValue, searchValue, searchValue, searchValue, pageable);
        } else {
            usersPage = userRepository.findAll(pageable);
        }
        for (UserEntity item : usersPage.getContent()) {
            if (item.getStatus() != 0) {
                UserDTO userDTO = userConverter.convertToDto(item);
                result.add(userDTO);
            }
        }
        return result;
    }

    @Override
    public int getTotalItems(String searchValue) {
        int totalItem = 0;
        int totalItemDelete = (int) userRepository.countByStatus(0);
        if (searchValue != null) {
            totalItem = (int) userRepository.countByUserNameOrFullNameOrPhoneOrEmailContainingIgnoreCase(searchValue, searchValue, searchValue, searchValue) - totalItemDelete;
        } else {
            totalItem = (int) userRepository.count() - totalItemDelete;
        }
        if (totalItem < 0) {
            return 0;
        }
        return totalItem;
    }

    @Override
    public UserDTO insert(UserDTO userDTO) {
        UserEntity userEntity = userConverter.convertToEntity(userDTO);
        userEntity.setRoleList(roleRepository.findOneByCode(userDTO.getRoleCode()));
        String pass = RandomGenerator.generateRandom(8);
        //userEntity.setPassword(Md5Utils.convertToMd5(pass));
        userEntity.setPassword(passwordEncoder.encode(pass));
        userEntity.setStatus(1);
        userEntity = userRepository.save(userEntity);
        return userConverter.convertToDto(userEntity);
    }

    @Override
    public UserDTO update(UserDTO updateUser, long id) {
        UserEntity oldUser = userRepository.findOne(id);
        oldUser.setUserName(updateUser.getUserName());
        oldUser.setFullName(updateUser.getFullName());
        oldUser.setEmail(updateUser.getEmail());
        oldUser.setPhone(updateUser.getPhone());
        oldUser.setRoleList(roleRepository.findOneByCode(updateUser.getRoleCode()));
        oldUser = userRepository.save(oldUser);
        return userConverter.convertToDto(oldUser);
    }

    @Override
    public UserDTO findUserById(long id) {
        UserEntity entity = userRepository.findOne(id);
        UserDTO dto = userConverter.convertToDto(entity);
        dto.setRoleCode(entity.getRoleList().get(0).getCode());
        dto.setRoles(roleService.getRoles());
        return dto;
    }

    @Override
    public void deleteUser(long[] ids) {
        for (long item : ids) {
            UserEntity entity = userRepository.findOne(item);
            entity.setStatus(0);
            userRepository.save(entity);
        }
    }

    @Override
    public void checkUserNameOrEmailExist(String userName, String email, Long id) throws MyException {
        if (id == 0 && userRepository.existsByUserNameOrEmail(userName, email)) {
            throw new MyException("Username hoặc email đã tồn tại");
        } else {
            //custom method check using JPA
        }
    }

    @Override
    public Map<String, String> getUsers() {
        Map<String, String> users = new HashMap<>();
        List<UserEntity> entities = userRepository.findAll();
        entities.forEach(item -> {
            users.put(item.getId().toString(), item.getUserName());
        });
        return users;
    }

}
