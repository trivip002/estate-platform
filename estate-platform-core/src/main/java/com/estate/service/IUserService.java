package com.estate.service;

import com.estate.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IUserService
{
	List<UserDTO> getAll();
	List<UserDTO> getUsers(String userName, Pageable pageable);
	int getTotalItem(String searchValue);
	Map<String, String> getMessageResponse(String message);
	UserDTO insert(UserDTO userDTO);
	UserDTO update(UserDTO userDTO, long id);
	void changePass(long id);
	void delete(long[] ids);
	UserDTO getById(long id);
	String existsUserNameOrEmail(String valueName);
}
