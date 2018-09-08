package com.estate.service.impl;

import com.estate.constant.SystemConstant;
import com.estate.converter.UserConverter;
import com.estate.dto.MyUserDetail;
import com.estate.dto.RoleDTO;
import com.estate.dto.UserDTO;
import com.estate.entity.RoleEntity;
import com.estate.entity.UserEntity;
import com.estate.exception.MyFoundException;
import com.estate.repository.RoleRepository;
import com.estate.repository.UserRepository;
import com.estate.service.IUserService;
import com.estate.utils.StringGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository ;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<UserDTO> getAll() {
		List<UserDTO> userDTOS = new ArrayList<>();
		List<UserEntity> userEntities = userRepository.findAll();
		for(UserEntity item:userEntities){
			UserDTO userDTO = userConverter.convertToDto(item);
			userDTOS.add(userDTO);
		}
		return userDTOS;
	}

	@Override
	public List<UserDTO> getUsers(String searchValue, Pageable pageable)
	{
		List<UserDTO> userDTOS = new ArrayList<>();
		List<UserEntity> userEntities = new ArrayList<>();
		Page<UserEntity> newPage = null;
		if(searchValue != null) {
			newPage = userRepository.findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseAndStatus(searchValue, searchValue ,searchValue,pageable,1);
		}
		else {
			newPage = userRepository.findByStatus(pageable,1);
		}
		userEntities = newPage.getContent();
		for(UserEntity item : userEntities) {
			UserDTO userDTO = userConverter.convertToDto(item);
			userDTOS.add(userDTO);
		}
		return userDTOS;
	}

	@Override
	public UserDTO insert(UserDTO userDTO) {
		userDTO.setPassword(StringGenerate.generateValue(8));
		UserEntity userEntity = userConverter.convertToEntity(userDTO);
		List<RoleEntity> roleEntities = new ArrayList<>();
		roleEntities.add(roleRepository.findByCode(userDTO.getRole()));
		userEntity.setRoles(roleEntities);
		userEntity.setStatus(1);
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userEntity = userRepository.save(userEntity);
		return userDTO;
	}

	@Override
	public UserDTO update(UserDTO userDTO, long id) {
		userDTO.setId(id);
		UserEntity userEntity = userRepository.findOne(userDTO.getId());
		userEntity.setEmail(userDTO.getEmail());
		userEntity.setFullName(userDTO.getFullName());
		userEntity.setUserName(userDTO.getUserName());
		userEntity.setPhoneNumber(userDTO.getPhoneNumber());
		List<RoleEntity> roleEntities = new ArrayList<>();
		roleEntities.add(roleRepository.findByCode(userDTO.getRole()));
		userEntity.setRoles(roleEntities);
		userEntity.setStatus(1);
		userEntity = userRepository.save(userEntity);
		return userDTO;
	}

	@Override
	public void changePass(long id) {
		UserEntity userEntity = userRepository.findOne(id);
		userEntity.setPassword(StringGenerate.generateValue(8));
		userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
		userEntity = userRepository.save(userEntity);
	}

	@Override
	public void delete(long[] ids) {
		for (long item : ids) {
			UserEntity userEntity = userRepository.findOne(item);
			userEntity.setStatus(0);
			userEntity = userRepository.save(userEntity);
		}
	}

	@Override
	public UserDTO getById(long id) {
		UserEntity userEntity = userRepository.findOne(id);
		UserDTO userDTO =  userConverter.convertToDto(userEntity);
		for(RoleEntity item : userEntity.getRoles()){
			userDTO.setRole(item.getCode());
		}
		userDTO.setRolesName(roleService.getAllName());
		return userDTO;
	}


	@Override
	public String existsUserNameOrEmail(String valueName) {
		if(userRepository.existsByEmailAndStatus(valueName,1)){
			throw new MyFoundException("emailExists");
		}else if(userRepository.existsByUserNameAndStatus(valueName,1)) {
			throw new MyFoundException("userNameExists");
		}else if(!userRepository.existsByUserNameAndStatus(valueName,1) && !userRepository.existsByEmailAndStatus(valueName,1)){
			return "success";
		}else{
			if(userRepository.existsByUserNameAndStatus(valueName,1) && userRepository.existsByEmailAndStatus(valueName,1)){
				return "errorExists";
			}
			else if(!userRepository.existsByUserNameAndStatus(valueName,1)){
				return "userNameSuccess";
			}else{
				return "emailSuccess";
			}
		}
	}

    @Override
    public List<String> getAllUserName() {
		List<UserEntity> userEntities = userRepository.findAll();
		List<String> result = userEntities.stream().map(item->item.getUserName()).collect(Collectors.toList());
        return result;
    }

    @Override
	public int getTotalItem(String searchValue) {
		int totalItem = 0 ;
		if(searchValue != null) {
			totalItem = (int) userRepository.countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseAndStatus(searchValue, searchValue, searchValue,1);
		}
		else {
			totalItem = (int) userRepository.countByStatus(1);
		}

		return totalItem;
	}


    @Override
    public Map<String, String> getMessageResponse(String message) {
		Map<String, String> results = new HashMap<>();
		if (message != null) {
			if (message.equals(SystemConstant.INSERT_SUCCESS)) {
				results.put(SystemConstant.ALERT, "success");
				results.put(SystemConstant.MESSAGE_RESPONSE, "Thêm thành công");
			} else if (message.equals(SystemConstant.UPDATE_SUCCESS)) {
				results.put(SystemConstant.ALERT, "success");
				results.put(SystemConstant.MESSAGE_RESPONSE, "Cập nhật thành công");
			} else if (message.equals(SystemConstant.DELETE_SUCCESS)) {
				results.put(SystemConstant.ALERT, "success");
				results.put(SystemConstant.MESSAGE_RESPONSE, "Xóa thành công");
			}else if(message.equals(SystemConstant.ERROR_EXISTS)){
				results.put(SystemConstant.ALERT, "danger");
				results.put(SystemConstant.MESSAGE_RESPONSE, "tài khoản hoặc mật khẩu đã tồn tại!");
			} else if (message.equals(SystemConstant.ERROR_SYSTEM)) {
				results.put(SystemConstant.ALERT, "danger");
				results.put(SystemConstant.MESSAGE_RESPONSE, "Có lỗi xảy ra!");
			}
		}
		return results;
    }
}
