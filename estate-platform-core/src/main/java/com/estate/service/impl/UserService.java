package com.estate.service.impl;

import com.estate.dto.UserDTO;
import com.estate.entity.UserEntity;
import com.estate.repository.UserRepository;
import com.estate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository ;

	@Override
	public List<UserDTO> getAll() {
		List<UserDTO> userDTOS = new ArrayList<>();
		List<UserEntity> userEntities = userRepository.findAll();
		for (UserEntity item: userEntities) {
			UserDTO userDTO = new UserDTO();
			userDTO.setUserName(item.getUserName());
			userDTO.setPassword(item.getPassword());
			userDTOS.add(userDTO);
		}
		return userDTOS;
	}
}
