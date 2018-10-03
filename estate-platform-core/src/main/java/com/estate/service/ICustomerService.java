package com.estate.service;

import com.estate.dto.CustomerDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICustomerService {

    List<CustomerDTO> searchCustomersAssignment(CustomerDTO model);

    CustomerDTO findOneById(long id);

    CustomerDTO findCustomerById(long id);

    CustomerDTO insert(CustomerDTO customerDTO);

    CustomerDTO update(CustomerDTO customerDTO, long id);

    CustomerDTO updateStatus(CustomerDTO customerDTO, long id);


    int getTotalItemsAssignment(CustomerDTO model);

    void deleteCustomer(long[] ids);

    void assignCustomerToStaff(Long[] users, long id);

}
   