package com.estate.controller.admin.api;

import com.estate.dto.CustomerDTO;
import com.estate.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "apiCustomerOfAdmin")
@RequestMapping(value = "/api/admin/customer")
public class CustomerAPI {
    @Autowired
    private CustomerService customerService;
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.insert(customerDTO));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable("id") long id){
        return ResponseEntity.ok(customerService.update(customerDTO,id));
    }
    @PutMapping
    public ResponseEntity<String> updateStatus(@RequestBody long id){
        customerService.updateStatus(id);
        return ResponseEntity.ok("success");
    }
}
