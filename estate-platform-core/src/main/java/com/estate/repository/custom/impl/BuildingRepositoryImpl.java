package com.estate.repository.custom.impl;

import com.estate.builder.BuildingBuilder;
import com.estate.entity.BuildingEntity;
import com.estate.paging.Pageable;
import com.estate.repository.custom.BuildingRepositoryCustom;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    private Logger logger = Logger.getLogger(BuildingRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<?> findAll(BuildingBuilder buildingBuilder, Pageable pageable,boolean priority) {
        StringBuilder sql = new StringBuilder("FROM BuildingEntity AS be");
        if(priority){
            sql.append(" JOIN be.staffsPrioritize sp ");
        }
        if (StringUtils.isNotBlank(buildingBuilder.getStaffName())) {
            sql.append(" JOIN be.staffs s ");
        }
        sql.append(" WHERE 1=1 ");
        sql = buildQuery(sql, buildingBuilder,priority);
        Query query = entityManager.createQuery(sql.toString());
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getLimit());
        return query.getResultList();
    }

    @Override
    public Long getTotalItems(BuildingBuilder buildingBuilder, boolean priority) {
        StringBuilder sql = new StringBuilder("SELECT COUNT(*) FROM BuildingEntity AS be");
        if(priority){
            sql.append(" JOIN be.staffsPrioritize sp ");
        }
        if (StringUtils.isNotBlank(buildingBuilder.getStaffName())) {
            sql.append(" JOIN be.staffs s ");
        }
        sql.append(" WHERE 1=1 ");
        sql = buildQuery(sql, buildingBuilder,priority);
        Query query = entityManager.createQuery(sql.toString());
        return (Long) query.getSingleResult();
    }



    private StringBuilder buildQuery(StringBuilder sql, BuildingBuilder buildingBuilder,boolean priority) {
        Field[] fields = BuildingBuilder.class.getDeclaredFields();
        for (Field field: fields) {
            if (!field.getName().equals("staffName") && !field.getName().startsWith("area") && !field.getName().startsWith("price")) {
                String fieldType = field.getType().getName();
                if (fieldType.equals("java.lang.String")) {
                    String value = getValueField(field, buildingBuilder, String.class);
                    if (StringUtils.isNotBlank(value)) {
                        sql.append("AND LOWER(be."+field.getName()+") LIKE '%"+value.toLowerCase()+"%'");
                    }
                } else if (fieldType.equals("java.lang.Integer")) {
                    Integer value = getValueField(field, buildingBuilder, Integer.class);
                    if (value != null) {
                        sql.append(" AND be."+field.getName()+") = "+value+"");
                    }
                }
            }
        }
        Optional.ofNullable(buildingBuilder.getAreaFrom()).map(item -> sql.append(" AND be.rentArea >= "+buildingBuilder.getAreaFrom()+"")).orElse(sql.append(""));
        Optional.ofNullable(buildingBuilder.getAreaTo()).map(item -> sql.append(" AND be.rentArea <= "+buildingBuilder.getAreaTo()+"")).orElse(sql.append(""));
        Optional.ofNullable(buildingBuilder.getPriceFrom()).map(item -> sql.append(" AND be.price >= "+buildingBuilder.getPriceFrom()+"")).orElse(sql.append(""));
        Optional.ofNullable(buildingBuilder.getPriceTo()).map(item -> sql.append(" AND be.price <= "+buildingBuilder.getPriceTo()+"")).orElse(sql.append(""));
        if (buildingBuilder.getTypeArrays().length > 0) {
            sql.append(" AND (").append("be.types LIKE '%"+buildingBuilder.getTypeArrays()[0]+"%'");
            Arrays.stream(buildingBuilder.getTypeArrays()).filter(item -> !item.equals(buildingBuilder.getTypeArrays()[0]))
                    .forEach(item -> sql.append(" OR be.types LIKE '%"+item+"%' "));
            sql.append(")");
        }
        if (StringUtils.isNotBlank(buildingBuilder.getStaffName())) {
            sql.append(" AND s.userName = '"+buildingBuilder.getStaffName()+"'");
        }
        if(priority){
            sql.append(" AND sp.id = '"+buildingBuilder.getUserId()+"'");
        }
        return sql;
    }

    private <T> T getValueField(Field field, BuildingBuilder buildingBuilder, Class<T> type) {
        Object result = null;
        Method getter = getGetter(field, buildingBuilder);
        if (getter != null) {
            try {
                result = getter.invoke(buildingBuilder);
            } catch (IllegalAccessException | InvocationTargetException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return type.cast(result);
    }

    private Method getGetter(Field field, BuildingBuilder buildingBuilder) {
        String getterMethod = "get" + StringUtils.capitalize(field.getName());
        try {
            return buildingBuilder.getClass().getMethod(getterMethod);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
