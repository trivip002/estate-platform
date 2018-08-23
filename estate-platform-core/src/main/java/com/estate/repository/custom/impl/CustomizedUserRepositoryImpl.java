package com.estate.repository.custom.impl;

import com.estate.repository.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomizedUserRepositoryImpl implements UserRepositoryCustom {
    @PersistenceContext
    private EntityManager em;
}
