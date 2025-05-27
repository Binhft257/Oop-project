package com.javaweb.repository.custom.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.LaptopSearchBuilder;
import com.javaweb.repository.custom.LaptopRepositoryCustom;
import com.javaweb.repository.entity.LaptopConfigurationEntity;
import com.javaweb.repository.query.LaptopQueryBuilder;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class LaptopRepositoryImpl implements LaptopRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<LaptopConfigurationEntity> findAll(LaptopSearchBuilder builder, int offset, int limit) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT DISTINCT lc ")
            .append("FROM LaptopConfigurationEntity lc ")
            .append("JOIN FETCH lc.laptopModel m ")
            .append("JOIN FETCH m.brand br ")
            .append("JOIN FETCH lc.cpu c ")
            .append("JOIN FETCH lc.gpu g ")
            .append("JOIN FETCH lc.ram r ")
            .append("JOIN FETCH lc.storage s ")
            .append("JOIN FETCH lc.display d ")
            .append("LEFT JOIN FETCH lc.reviews rv ")
            .append("LEFT JOIN FETCH lc.rating rt ")
            .append("WHERE 1 = 1 ");

        Map<String, Object> params = LaptopQueryBuilder.buildConditions(builder, jpql);
        jpql.append(" ORDER BY lc.id ASC");
        TypedQuery<LaptopConfigurationEntity> query =
            em.createQuery(jpql.toString(), LaptopConfigurationEntity.class);
        params.forEach(query::setParameter);

        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.getResultList();
    }

    @Override
    public int countAll(LaptopSearchBuilder builder) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT COUNT(DISTINCT lc) ")
            .append("FROM LaptopConfigurationEntity lc ")
            .append("JOIN lc.laptopModel m ")
            .append("JOIN m.brand br ")
            .append("JOIN lc.cpu c ")
            .append("JOIN lc.gpu g ")
            .append("JOIN lc.ram r ")
            .append("JOIN lc.storage s ")
            .append("JOIN lc.display d ")
            .append("WHERE 1 = 1 ");

        Map<String, Object> params = LaptopQueryBuilder.buildConditions(builder, jpql);
        jpql.append(" ORDER BY lc.id ASC");
        TypedQuery<Long> query = em.createQuery(jpql.toString(), Long.class);
        params.forEach(query::setParameter);

        return query.getSingleResult().intValue();
    }
}
