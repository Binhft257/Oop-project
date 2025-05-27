// src/main/java/com/javaweb/repository/custom/LaptopRepositoryCustom.java
package com.javaweb.repository.custom;

import java.util.List;

import com.javaweb.builder.LaptopSearchBuilder;
import com.javaweb.repository.entity.LaptopConfigurationEntity;

public interface LaptopRepositoryCustom {
    /**
     * Tìm tất cả cấu hình theo builder, phân trang bằng offset + limit
     */
    List<LaptopConfigurationEntity> findAll(LaptopSearchBuilder builder, int offset, int limit);
    int countAll(LaptopSearchBuilder builder);
}
