package com.estate.repository.Custom;

import com.estate.Builder.CustomerBuilder;
import com.estate.entity.CustomerEntity;
import com.estate.paging.Pageable;

import java.util.List;

public interface CustomerRepositoryCustom {
    List<?> findAllByUser(CustomerBuilder builder, Pageable pageable);
    Integer getTotalItem(CustomerBuilder customerBuilder);
}
