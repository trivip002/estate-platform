package com.estate.repository;

import com.estate.entity.CustomerServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerServiceRepository extends JpaRepository<CustomerServiceEntity, Long> {
}
