package com.estate.service;

import com.estate.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IUserService {
	List<UserDTO> getAll();
	List<UserDTO> getUsers(String searchValue, Pageable pageable);
	int getTotalItems(String searchValue);
	UserDTO insert(UserDTO userDTO);
	UserDTO update(UserDTO updateUser, long id);
	UserDTO findUserById(long id);
	void deleteUser(long[] ids);
	Map<String, String> getMessageResponse(String message);
}
