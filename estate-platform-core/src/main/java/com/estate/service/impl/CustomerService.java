package com.estate.service.impl;

import com.estate.converter.CustomerConverter;
import com.estate.dto.CustomerDTO;
import com.estate.entity.CustomerEntity;
import com.estate.entity.RoleEntity;
import com.estate.entity.UserEntity;
import com.estate.repository.CustomerRepository;
import com.estate.repository.UserRepository;
import com.estate.service.ICustomerService;
import com.estate.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerConverter customerConverter;

    @Override
    public List<CustomerDTO> getCustomer(Pageable pageable) {
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getPrincipal().getId());
        Page<CustomerEntity> customerPage = customerRepository.findByUsers(userEntity,pageable);
        List<CustomerEntity> customerEntities = customerPage.getContent();
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for(CustomerEntity item:customerEntities){
            CustomerDTO customerDTO = customerConverter.convertToDto(item);
            customerDTOS.add(customerDTO);
        }
        return customerDTOS;
    }

    @Override
    public CustomerDTO findOneById(long id) {
        CustomerEntity customerEntity = customerRepository.findOne(id);
        return customerConverter.convertToDto(customerEntity);
    }

    @Override
    public CustomerDTO insert(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
        List<UserEntity> userEntities = userRepository.findById(1);
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getPrincipal().getId());
        if(!isAdmin(userEntity)){
            userEntities.add(userRepository.findOne(SecurityUtils.getPrincipal().getId()));
        }
        customerEntity.setUsers(userEntities);
        customerRepository.save(customerEntity);
        return customerDTO;
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO, long id) {
        customerDTO.setId(id);
        CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
        List<UserEntity> userEntities = userRepository.findById(1);
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getPrincipal().getId());
        if(!isAdmin(userEntity)){
            userEntities.add(userRepository.findOne(SecurityUtils.getPrincipal().getId()));
        }
        customerEntity.setUsers(userEntities);
        customerRepository.save(customerEntity);
        return customerDTO;
    }

    @Override
    public int getTotalItem() {
        UserEntity userEntity = userRepository.findOne(SecurityUtils.getPrincipal().getId());
        return (int) customerRepository.countByUsers(userEntity);
    }
    private boolean isAdmin(UserEntity userEntity){
        for(RoleEntity item:userEntity.getRoles()){
            if(item.getCode().equals("ADMIN")){
                return true;
            }
        }
        return false;
    }
}
