package com.estate.repository;

import com.estate.entity.CustomerEntity;
import com.estate.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    Page<CustomerEntity> findAll(Pageable pageable);
    // lấy tất cả khách hàng theo user
    Page<CustomerEntity> findByUsers_id(Pageable pageable,long userId);
    long countByUsers_id(long userId);
}
