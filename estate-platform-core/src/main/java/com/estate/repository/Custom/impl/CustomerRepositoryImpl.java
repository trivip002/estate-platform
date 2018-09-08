package com.estate.repository.Custom.impl;

import com.estate.Builder.CustomerBuilder;
import com.estate.entity.CustomerEntity;
import com.estate.paging.Pageable;
import com.estate.repository.Custom.CustomerRepositoryCustom;
import com.estate.service.impl.CustomUserDetailsService;
import com.estate.utils.SecurityUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class CustomerRepositoryImpl implements CustomerRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;
    private final Logger log = Logger.getLogger(CustomUserDetailsService.class);

    @Override
    public List<?> findAllByUser(CustomerBuilder builder, Pageable pageable) {
        StringBuilder sql = new StringBuilder("FROM CustomerEntity AS ce JOIN ce.users u");
        sql.append(" WHERE 1=1 AND u.id = "+ SecurityUtils.getPrincipal().getId() + " ");
        sql = buildQuery(sql, builder);
        Query query = entityManager.createQuery(sql.toString());
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getLimit());
        return query.getResultList();
    }

    @Override
    public Integer getTotalItem(CustomerBuilder builder) {
        StringBuilder sql = new StringBuilder("FROM CustomerEntity AS ce JOIN ce.users u");
        sql.append(" WHERE 1=1 AND u.id = '"+ SecurityUtils.getPrincipal().getId() + "' ");
        sql = buildQuery(sql, builder);
        Query query = entityManager.createQuery(sql.toString());
        return query.getResultList().size();
    }

    private StringBuilder buildQuery(StringBuilder sql, CustomerBuilder builder) {
        Field[] fields = CustomerBuilder.class.getDeclaredFields();
        for (Field field: fields){
            String fieldType = field.getType().getName();
            if (fieldType.equals("java.lang.String")) {
                String value = getValueField(field, builder, String.class);
                if (StringUtils.isNotBlank(value)) {
                    sql.append("AND LOWER(ce."+field.getName()+") LIKE '%"+value.toLowerCase()+"%'");
                }
            }else if(fieldType.equals("java.lang.Integer")){
                Integer value = getValueField(field, builder, Integer.class);
                if (value != null) {
                    sql.append(" AND ce."+field.getName()+") = "+value+"");
                }
            }
        }
        return sql;
    }

    private <T> T getValueField(Field field, CustomerBuilder builder, Class<T> type) {
        Object result = null;
        Method getter = getGetter(field,builder);
        if (getter != null) {
            try {
                result = getter.invoke(builder);
            }catch (IllegalAccessException | InvocationTargetException e) {
                log.error(e.getMessage(), e);
            }
        }
        return type.cast(result);
    }

    private Method getGetter(Field field, CustomerBuilder builder) {
        String getterMethod = "get" + StringUtils.capitalize(field.getName());
        try {
            return builder.getClass().getMethod(getterMethod);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
