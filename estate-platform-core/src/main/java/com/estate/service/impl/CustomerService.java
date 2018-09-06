package com.estate.service.impl;

import com.estate.converter.CareDetailConverter;
import com.estate.converter.CustomerConverter;
import com.estate.dto.CareDetailDTO;
import com.estate.dto.CustomerDTO;
import com.estate.entity.CareDetailEntity;
import com.estate.entity.CustomerEntity;
import com.estate.entity.RoleEntity;
import com.estate.entity.UserEntity;
import com.estate.repository.CustomerRepository;
import com.estate.repository.UserRepository;
import com.estate.service.ICustomerService;
import com.estate.service.IRoleService;
import com.estate.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private CareDetailConverter careDetailConverter;

    @Autowired
    private IRoleService roleService;


    private Long userId;
    private boolean isManager;


    @Override
    public List<CustomerDTO> getCustomer(String searchValue, Pageable pageable) {
        List<CustomerDTO> result = new ArrayList<>();
        getUserAndRole();
        Page<CustomerEntity> customerPage = null;
        if (searchValue != null) {
            //usersPage = userRepository.findByUserNameOrFullNameOrPhoneOrEmailContainingIgnoreCase(searchValue, searchValue, searchValue, searchValue, pageable);
        } else {
            if (isManager) { // manager
                customerPage = customerRepository.findAll(pageable);

            } else {// user
                customerPage = customerRepository.findByUsers_id(pageable, userId);
            }
        }
        for (CustomerEntity item : customerPage.getContent()) {
            CustomerDTO customerDTO = customerConverter.convertToDto(item);
            result.add(customerDTO);
        }
        return result;
    }

    @Override
    public CustomerDTO findOneById(long id) {
        CustomerEntity customerEntity = customerRepository.findOne(id);
        return customerConverter.convertToDto(customerEntity);
    }

    @Override
    public CustomerDTO findCustomerById(long id) {
        CustomerEntity entity = customerRepository.findOne(id);
        CustomerDTO dto = customerConverter.convertToDto(entity);
        return dto;
    }

    @Override
    public CustomerDTO insert(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = customerConverter.convertToEntity(customerDTO);
        customerRepository.save(customerEntity);
        return customerDTO;
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO, long id) {
        CustomerEntity oldCustomer = customerRepository.findOne(id);
        oldCustomer.setName(customerDTO.getName());
        oldCustomer.setDateMail(customerDTO.getDateMail());
        oldCustomer.setEmail(customerDTO.getEmail());
        oldCustomer.setNeed(customerDTO.getNeed());
        oldCustomer.setNote(customerDTO.getNote());
        oldCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        oldCustomer.setProcess(customerDTO.getProcess());
        oldCustomer.setStatus(customerDTO.getStatus());
        oldCustomer = customerRepository.save(oldCustomer);
        return customerConverter.convertToDto(oldCustomer);
    }

    @Override
    public int getTotalItem(String searchValue) {
        int totalItem = 0;
        getUserAndRole();
        if (searchValue != null) {
            //usersPage = userRepository.findByUserNameOrFullNameOrPhoneOrEmailContainingIgnoreCase(searchValue, searchValue, searchValue, searchValue, pageable);
        } else {
            if (isManager) { // manager
                totalItem = (int) customerRepository.count();

            } else {// user
                totalItem = (int) customerRepository.countByUsers_id(userId);
            }
        }
        return totalItem;
    }

    void getUserAndRole() {
        userId = SecurityUtils.getPrincipal().getId();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        isManager = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("MANAGER"));
    }


    @Override
    public void deleteCustomer(long[] ids) {
        for (long item : ids) {
            customerRepository.delete(item);
        }
    }

    @Override
    public CustomerDTO insertCustomerUser(String users, long id) {
        CustomerEntity entity = customerRepository.findOne(id);
        users = users.substring(1, users.length() - 1);
        String[] arrayUser = users.split(",");
        List<UserEntity> userEntities = new ArrayList<>();
        for (String s : arrayUser) {
            UserEntity userEntity = userRepository.findOneByUserName(s);
            userEntities.add(userEntity);
        }
        entity.setUsers(userEntities);
        customerRepository.save(entity);
        return customerConverter.convertToDto(entity);
    }


}

   