package com.javaweb.repository.custom.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.LaptopSearchBuilder;
import com.javaweb.repository.custom.LaptopRepositoryCustom;
import com.javaweb.repository.entity.LaptopConfigurationEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Repository
public class LaptopRepositoryImpl implements LaptopRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<LaptopConfigurationEntity> findAll(LaptopSearchBuilder b) {
        // 1. Build JPQL with join fetch all needed associations
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
            .append("WHERE 1=1 ");

        // 2. Dynamic WHERE clauses
        Map<String, Object> params = new HashMap<>();

        if (b.getBrand() != null) {
            jpql.append(" AND LOWER(br.name) LIKE :brand ");
            params.put("brand", "%" + b.getBrand().toLowerCase() + "%");
        }
        if (b.getModel() != null) {
            jpql.append(" AND LOWER(m.modelName) LIKE :model ");
            params.put("model", "%" + b.getModel().toLowerCase() + "%");
        }
        if (b.getOs() != null) {
            jpql.append(" AND LOWER(lc.os) LIKE :os ");
            params.put("os", "%" + b.getOs().toLowerCase() + "%");
        }
        if (b.getColor() != null) {
            jpql.append(" AND LOWER(lc.color) LIKE :color ");
            params.put("color", "%" + b.getColor().toLowerCase() + "%");
        }
        if (b.getPriceFrom() != null) {
            jpql.append(" AND lc.price >= :priceFrom ");
            params.put("priceFrom", b.getPriceFrom());
        }
        if (b.getPriceTo() != null) {
            jpql.append(" AND lc.price <= :priceTo ");
            params.put("priceTo", b.getPriceTo());
        }
        if (b.getCpu() != null) {
            jpql.append(" AND LOWER(c.model) LIKE :cpu ");
            params.put("cpu", "%" + b.getCpu().toLowerCase() + "%");
        }
        if (b.getRam() != null && !b.getRam().isEmpty()) {
            jpql.append(" AND r.capacity IN :rams ");
            params.put("rams", b.getRam());
        }
        if (b.getGpu() != null && !b.getGpu().isEmpty()) {
            jpql.append(" AND LOWER(g.name) IN :gpus ");
            params.put("gpus", b.getGpu().stream()
                                   .map(String::toLowerCase)
                                   .toList());
        }
        if (b.getStorageCapacity() != null && !b.getStorageCapacity().isEmpty()) {
            jpql.append(" AND s.capacity IN :storages ");
            params.put("storages", b.getStorageCapacity());
        }
        if (b.getDisplaySizeFrom() != null) {
            jpql.append(" AND d.size >= :dsFrom ");
            params.put("dsFrom", b.getDisplaySizeFrom());
        }
        if (b.getDisplaySizeTo() != null) {
            jpql.append(" AND d.size <= :dsTo ");
            params.put("dsTo", b.getDisplaySizeTo());
        }

        // 3. Create query & bind params
        TypedQuery<LaptopConfigurationEntity> query =
            em.createQuery(jpql.toString(), LaptopConfigurationEntity.class);
        params.forEach(query::setParameter);

        // 4. Execute
        return query.getResultList();
    }
}
