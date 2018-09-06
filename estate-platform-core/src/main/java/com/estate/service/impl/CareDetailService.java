package com.estate.service.impl;

import com.estate.converter.CareDetailConverter;
import com.estate.converter.CustomerConverter;
import com.estate.dto.CareDetailDTO;
import com.estate.dto.CustomerDTO;
import com.estate.entity.CareDetailEntity;
import com.estate.entity.CustomerEntity;
import com.estate.repository.CareDetailRepository;
import com.estate.repository.CustomerRepository;
import com.estate.service.ICareDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CareDetailService implements ICareDetailService {

    @Autowired
    private CareDetailConverter careDetailConverter;

    @Autowired
    private CustomerConverter customerConverter;


    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CareDetailRepository careDetailRepository;

    @Override
    public CustomerDTO insert(CareDetailDTO careDetailDTO, long customerId) {
        CustomerEntity customerEntity = customerRepository.findOne(customerId);
        CareDetailEntity careDetailEntity = careDetailConverter.convertToEntity(careDetailDTO);
        careDetailEntity.setCustomer(customerEntity);
        careDetailRepository.save(careDetailEntity);
        return customerConverter.convertToDto(customerEntity);
    }
}
