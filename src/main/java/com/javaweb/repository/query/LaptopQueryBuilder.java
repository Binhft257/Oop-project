package com.javaweb.repository.query;

import com.javaweb.builder.LaptopSearchBuilder;
import java.util.*;

public class LaptopQueryBuilder {

    public static Map<String, Object> buildConditions(LaptopSearchBuilder builder, StringBuilder jpql) {
        Map<String, Object> params = new HashMap<>();

        if (builder.getBrand() != null) {
            jpql.append(" AND LOWER(br.name) LIKE :brand");
            params.put("brand", "%" + builder.getBrand().toLowerCase() + "%");
        }
        if (builder.getModel() != null) {
            jpql.append(" AND LOWER(m.modelName) LIKE :model");
            params.put("model", "%" + builder.getModel().toLowerCase() + "%");
        }
        if (builder.getOs() != null) {
            jpql.append(" AND LOWER(lc.os) LIKE :os");
            params.put("os", "%" + builder.getOs().toLowerCase() + "%");
        }
        if (builder.getColor() != null) {
            jpql.append(" AND LOWER(lc.color) LIKE :color");
            params.put("color", "%" + builder.getColor().toLowerCase() + "%");
        }
        if (builder.getPriceFrom() != null) {
            jpql.append(" AND lc.price >= :priceFrom");
            params.put("priceFrom", builder.getPriceFrom());
        }
        if (builder.getPriceTo() != null) {
            jpql.append(" AND lc.price <= :priceTo");
            params.put("priceTo", builder.getPriceTo());
        }
        if (builder.getCpu() != null) {
            jpql.append(" AND LOWER(c.model) LIKE :cpu");
            params.put("cpu", "%" + builder.getCpu().toLowerCase() + "%");
        }
        if (builder.getRam() != null && !builder.getRam().isEmpty()) {
            jpql.append(" AND r.capacity IN :rams");
            params.put("rams", builder.getRam());
        }
        if (builder.getGpu() != null && !builder.getGpu().isEmpty()) {
            jpql.append(" AND LOWER(g.name) IN :gpus");
            params.put("gpus", builder.getGpu().stream().map(String::toLowerCase).toList());
        }
        if (builder.getStorageCapacity() != null && !builder.getStorageCapacity().isEmpty()) {
            jpql.append(" AND s.capacity IN :storages");
            params.put("storages", builder.getStorageCapacity());
        }
        if (builder.getDisplaySizeFrom() != null) {
            jpql.append(" AND d.size >= :dsFrom");
            params.put("dsFrom", builder.getDisplaySizeFrom());
        }
        if (builder.getDisplaySizeTo() != null) {
            jpql.append(" AND d.size <= :dsTo");
            params.put("dsTo", builder.getDisplaySizeTo());
        }

        return params;
    }
}
