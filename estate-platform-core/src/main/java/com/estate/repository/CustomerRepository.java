package com.estate.repository;

import com.estate.entity.CustomerEntity;
import com.estate.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Page<CustomerEntity> findByUsers(UserEntity userEntity, Pageable pageable);
    long countByUsers(UserEntity userEntity);
}
