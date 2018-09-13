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
import java.util.List;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {

    private Logger logger = Logger.getLogger(BuildingRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingBuilder buildingBuilder, Pageable pageable) {
        StringBuilder sql = new StringBuilder("FROM BuildingEntity AS be");
        sql.append(" WHERE 1=1 ");
        sql = buildQuery(sql, buildingBuilder);
        Query query = entityManager.createQuery(sql.toString());
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getLimit());
        return query.getResultList();
    }

    @Override
    public Integer getTotalItems(BuildingBuilder buildingBuilder) {
        StringBuilder sql = new StringBuilder("FROM BuildingEntity AS be");
        sql.append(" WHERE 1=1 ");
        sql = buildQuery(sql, buildingBuilder);
        Query query = entityManager.createQuery(sql.toString());
        return query.getResultList().size();
    }

    private StringBuilder buildQuery(StringBuilder sql, BuildingBuilder buildingBuilder) {
        Field[] fields = BuildingBuilder.class.getDeclaredFields();
        for (Field field: fields) {
            if (!field.getName().equals("staffName") && !field.getName().startsWith("area") && !field.getName().startsWith("price")) {
                String fieldType = field.getType().getName();
                if (fieldType.equals("java.lang.String")) {
                    String value = getValueField(field, buildingBuilder, String.class);
                    if (StringUtils.isNotBlank(value)) {
                        sql.append("AND LOWER(be."+field.getName()+") LIKE '%"+value.toLowerCase()+"%'");
                    }
                }
            }
        }
        return null;
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
