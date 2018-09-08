package com.estate.service;

import com.estate.dto.CustomerDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> getCustomer(Pageable pageable);
    CustomerDTO findOneById(long id);
    CustomerDTO insert(CustomerDTO customerDTO);
    CustomerDTO update(CustomerDTO customerDTO, long id);
    void updateStatus(long id);
    int getTotalItem();
}
