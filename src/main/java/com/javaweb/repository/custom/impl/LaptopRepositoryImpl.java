package com.javaweb.repository.custom.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.javaweb.builder.LaptopSearchBuilder;
import com.javaweb.repository.custom.LaptopRepositoryCustom;
import com.javaweb.repository.entity.LaptopConfigurationEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Repository
public class LaptopRepositoryImpl implements LaptopRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    private void join(LaptopSearchBuilder builder, StringBuilder sql) {

        if (builder.getRam() != null && !builder.getRam().isEmpty()) {
            sql.append(" JOIN ram r ON lc.ram_id = r.id ");
        }
        if (builder.getGpu() != null && !builder.getGpu().isEmpty()) {
            sql.append(" JOIN gpu g ON lc.gpu_id = g.id ");
        }
        if (builder.getStorageCapacity() != null && !builder.getStorageCapacity().isEmpty()) {
            sql.append(" JOIN storage s ON lc.storage_id = s.id ");
        }
        if (builder.getCpu() != null) {
            sql.append(" JOIN cpu c ON lc.cpu_id = c.id ");
        }
        if (builder.getBrand() != null || builder.getModel() != null) {
            sql.append(" JOIN laptopmodel lm ON lc.model_id = lm.id ");
        }

        if (builder.getBrand() != null) {
            sql.append(" JOIN brand b ON lm.brand_id = b.id ");
        }
        if (builder.getDisplaySizeFrom() != null || builder.getDisplaySizeTo() != null) {
            sql.append(" JOIN display d ON lc.display_id = d.id ");
        }
    }

    private void queryNormal(LaptopSearchBuilder builder, StringBuilder where) {
        if (builder.getColor() != null) {
            where.append(" AND lc.color LIKE '%").append(builder.getColor()).append("%' ");
        }
        if (builder.getOs() != null) {
            where.append(" AND lc.os LIKE '%").append(builder.getOs()).append("%' ");
        }
        if (builder.getPriceFrom() != null) {
            where.append(" AND lc.price >= ").append(builder.getPriceFrom());
        }
        if (builder.getPriceTo() != null) {
            where.append(" AND lc.price <= ").append(builder.getPriceTo());
    }
    }

    private void querySpecial(LaptopSearchBuilder builder, StringBuilder where) {
        if (builder.getRam() != null && !builder.getRam().isEmpty()) {
            String ramCondition = builder.getRam().stream()
                .map(cap -> "r.capacity = " + cap)
                .collect(Collectors.joining(" OR "));
            where.append(" AND (").append(ramCondition).append(") ");
        }
        if (builder.getCpu() != null) {
            where.append(" AND c.model LIKE '%").append(builder.getCpu()).append("%' ");
        }
        if (builder.getGpu() != null && !builder.getGpu().isEmpty()) {
            String gpuCondition = builder.getGpu().stream()
                .map(gpu -> "LOWER(g.name) LIKE '%" + gpu.toLowerCase() + "%'")
                .collect(Collectors.joining(" OR "));
            where.append(" AND (").append(gpuCondition).append(") ");
        }
        if (builder.getDisplaySizeFrom() != null) {
            where.append(" AND d.size >= ").append(builder.getDisplaySizeFrom());
        }
        if (builder.getDisplaySizeTo() != null) {
            where.append(" AND d.size <= ").append(builder.getDisplaySizeTo());
        }
        if (builder.getBrand() != null) {
            where.append(" AND b.name LIKE '%").append(builder.getBrand()).append("%' ");
        }
        if (builder.getModel() != null) {
            where.append(" AND lm.model_name LIKE '%").append(builder.getModel()).append("%' ");
        }
        if (builder.getStorageCapacity() != null && !builder.getStorageCapacity().isEmpty()) {
            String storageCondition = builder.getStorageCapacity().stream()
                .map(cap -> "s.capacity = " + cap)
                .collect(Collectors.joining(" OR "));
            where.append(" AND (").append(storageCondition).append(") ");
        }
    }

    @Override
    public List<LaptopConfigurationEntity> findAll(LaptopSearchBuilder builder) {
        StringBuilder sql = new StringBuilder("SELECT lc.* FROM laptopconfiguration lc ");
        join(builder, sql);

        StringBuilder where = new StringBuilder(" WHERE 1=1 ");
        queryNormal(builder, where);
        querySpecial(builder, where);

        sql.append(where);
        sql.append(" GROUP BY lc.id ");

        Query query = entityManager.createNativeQuery(sql.toString(), LaptopConfigurationEntity.class);
        return query.getResultList();
    }
}