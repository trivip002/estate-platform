package com.estate.controller.admin.api;

import com.estate.dto.UserDTO;
import com.estate.exception.MyException;
import com.estate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin/checkexist")
public class CheckExistAPI {

    @Autowired
    private IUserService userService;

    @PostMapping
    public ResponseEntity<UserDTO> checkExist(@RequestBody UserDTO userDTO) {
            try {
                boolean check = userService.checkUserNameOrEmailExist(userDTO.getUserName(),userDTO.getEmail(),userDTO.getId());
                if(check){ // ko trùng
                    return ResponseEntity.ok(userDTO);
                }
            } catch (MyException e) {
                userDTO.setMessageException(e.getMessage());
                e.printStackTrace();
            }


        return ResponseEntity.ok(userDTO);
    }
}
