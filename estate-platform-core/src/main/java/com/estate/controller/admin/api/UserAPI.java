package com.estate.controller.admin.api;

import com.estate.dto.UserDTO;
import com.estate.entity.UserEntity;
import com.estate.exception.MyFoundException;
import com.estate.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "apiUserOfAdmin")
@RequestMapping(value = "/api/admin/user")
public class UserAPI
{
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseEntity<UserDTO> createNew(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.insert(userDTO));
    }
    @GetMapping("/usages")
    public ResponseEntity<String> checkValue(@RequestBody UserDTO userDTO){
        try {
           return ResponseEntity.ok(userService.existsUserNameOrEmail(userDTO.getError()));
        }catch (MyFoundException ex){
            ex.printStackTrace();
            userDTO.setError(ex.getMessage()) ;
        }
        return ResponseEntity.ok(userDTO.getError());
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateNew(@RequestBody UserDTO userDTO, @PathVariable("id") long id) {
        return ResponseEntity.ok(userService.update(userDTO,id));
    }
    @PutMapping
    public ResponseEntity<String> changlePassUser(@RequestBody long id) {
        userService.changePass(id);
        return ResponseEntity.ok("success");
    }
    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestBody long[] ids) {
        if (ids.length > 0) {
            userService.delete(ids);
        }
        return ResponseEntity.ok("success");
    }
}
