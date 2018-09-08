package com.estate.service;

import com.estate.dto.CareDetailDTO;
import com.estate.dto.CustomerDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {
    List<CustomerDTO> getCustomer(String searchValue, Pageable pageable);

    CustomerDTO findOneById(long id);

    CustomerDTO findCustomerById(long id);

    CustomerDTO insert(CustomerDTO customerDTO);

    CustomerDTO update(CustomerDTO customerDTO, long id);

    CustomerDTO updateStatus(CustomerDTO customerDTO, long id);

    int getTotalItem(String searchValue);

    void deleteCustomer(long[] ids);

    void assignCustomerToStaff(String[] users, long id);

}
   