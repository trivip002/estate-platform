package com.estate.service;

import com.estate.dto.RoleDTO;
import com.estate.dto.UserDTO;

import javax.management.relation.Role;
import java.util.List;
import java.util.Map;

public interface IRoleService {
    Map<String , String> getAllName();
}
