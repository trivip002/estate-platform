package com.estate.service;

import com.estate.dto.CareDetailDTO;
import com.estate.dto.CustomerDTO;

public interface ICareDetailService {
    CustomerDTO insert(CareDetailDTO careDetailDTO, long customerId);
}
