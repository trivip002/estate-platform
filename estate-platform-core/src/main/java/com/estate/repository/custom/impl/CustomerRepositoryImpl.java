package com.estate.repository.custom.impl;

import com.estate.dto.CustomerDTO;
import com.estate.paging.Pageable;
import com.estate.repository.custom.CustomerRepositoryCustom;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    private Logger logger = Logger.getLogger(CustomerRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<?> findAll(CustomerDTO customerDTO, Pageable pageable) {
        StringBuilder sql = new StringBuilder("FROM CustomerEntity AS ce");
        if (StringUtils.isNotBlank(customerDTO.getStaffName())) {
            sql.append(" JOIN ce.users u ");
        }
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(customerDTO.getName())) {
            sql.append("AND LOWER(ce.name) LIKE '%" + customerDTO.getName() + "%'");
        }
        if (StringUtils.isNotBlank(customerDTO.getPhoneNumber())) {
            sql.append("AND LOWER(ce.phoneNumber) LIKE '%" + customerDTO.getPhoneNumber() + "%'");
        }
        if (StringUtils.isNotBlank(customerDTO.getEmail())) {
            sql.append("AND LOWER(ce.email) LIKE '%" + customerDTO.getEmail() + "%'");
        }
        if (StringUtils.isNotBlank(customerDTO.getStaffName())) {
            sql.append(" AND u.userName = '"+customerDTO.getStaffName()+"'");
        }
        Query query = entityManager.createQuery(sql.toString());
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getLimit());
        return query.getResultList();
    }



    @Override
    public Long getTotalItems(CustomerDTO customerDTO) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM CustomerEntity AS ce");
        if (StringUtils.isNotBlank(customerDTO.getStaffName())) {
            sql.append(" JOIN ce.users u ");
        }
        sql.append(" WHERE 1=1 ");
        if (StringUtils.isNotBlank(customerDTO.getName())) {
            sql.append("AND LOWER(ce.name) LIKE '%" + customerDTO.getName() + "%'");
        }
        if (StringUtils.isNotBlank(customerDTO.getPhoneNumber())) {
            sql.append("AND LOWER(ce.phoneNumber) LIKE '%" + customerDTO.getPhoneNumber() + "%'");
        }
        if (StringUtils.isNotBlank(customerDTO.getEmail())) {
            sql.append("AND LOWER(ce.email) LIKE '%" + customerDTO.getEmail() + "%'");
        }
        if (StringUtils.isNotBlank(customerDTO.getStaffName())) {
            sql.append(" AND u.userName = '"+customerDTO.getStaffName()+"'");
        }

        Query query = entityManager.createQuery(sql.toString());
        return (Long) query.getSingleResult();
    }
}
