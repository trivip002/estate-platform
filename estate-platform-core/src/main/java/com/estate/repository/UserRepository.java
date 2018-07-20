package com.estate.repository;

import com.estate.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<UserEntity, Long>
{
    UserEntity findOneByUserName(String userName);
}
