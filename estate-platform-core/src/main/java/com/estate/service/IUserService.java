package com.estate.service;

import com.estate.dto.UserDTO;
import com.estate.exception.MyException;
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
	void checkUserNameOrEmailExist(String userName,String email,Long id) throws MyException;
	Map<String, String> getUsers();
}
