package com.estate.controller.admin.api;

import com.estate.dto.CareDetailDTO;
import com.estate.dto.CustomerDTO;
import com.estate.service.impl.CareDetailService;
import com.estate.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController(value = "apiCustomerOfAdmin")
@RequestMapping(value = "/api/admin/customer")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CareDetailService careDetailService;

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.insert(customerDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable("id") long id){
        return ResponseEntity.ok(customerService.update(customerDTO,id));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteCustomer(@RequestBody long[] ids) {
        if (ids.length > 0) {
            customerService.deleteCustomer(ids);
        }
        return ResponseEntity.ok("success");
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<CustomerDTO> updateCustomerUser(@RequestBody String users, @PathVariable("id") long id) {
        return ResponseEntity.ok(customerService.insertCustomerUser(users, id));
    }

    @PutMapping("/care_detail/{id}")
    public ResponseEntity<CustomerDTO> updateCareDetail(@RequestBody CareDetailDTO careDetailDTO, @PathVariable("id") long id) {
        return ResponseEntity.ok(careDetailService.insert(careDetailDTO, id));
    }
}
