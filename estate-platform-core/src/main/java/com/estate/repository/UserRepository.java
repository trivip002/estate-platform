package com.estate.repository;

import com.estate.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long>
{
    UserEntity findOneByUserName(String userName);
    Page<UserEntity> findByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseAndStatus(String UserName, String fullName , String email,Pageable Pageable,int status);
    Page<UserEntity> findByStatus(Pageable pageable,int status);
    long countByUserNameContainingIgnoreCaseOrFullNameContainingIgnoreCaseOrEmailContainingIgnoreCaseAndStatus(String userName,String fullName, String email,int status);
    long countByStatus(int status);
    boolean existsByEmailAndStatus(String email,int status);
    boolean existsByUserNameAndStatus(String userName , int status);
    List<UserEntity> findById(long Id);
    List<UserEntity> findByRoles_code(String code);
}
