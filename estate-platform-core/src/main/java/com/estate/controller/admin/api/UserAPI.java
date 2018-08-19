package com.estate.controller.admin.api;

import com.estate.dto.UserDTO;
import com.estate.repository.UserRepository;
import com.estate.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin/user")
public class UserAPI{

        @Autowired
        private IUserService userService;

        @Autowired
        private UserRepository userRepository;

        @PostMapping
        public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
            return ResponseEntity.ok(userService.insert(userDTO));
        }

        @PutMapping("/{id}")
        public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable("id") long id) {
            return ResponseEntity.ok(userService.update(userDTO, id));
        }

        @DeleteMapping
        public ResponseEntity<String> updateUser(@RequestBody long[] ids) {
            if (ids.length > 0) {
                userService.deleteUser(ids);
            }
            return ResponseEntity.ok("success");
        }
}
