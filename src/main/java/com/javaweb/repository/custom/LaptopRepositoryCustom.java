package com.javaweb.repository.custom;

import java.util.List;

import com.javaweb.builder.LaptopSearchBuilder;
import com.javaweb.model.ReviewDTO;
import com.javaweb.repository.entity.LaptopConfigurationEntity;

public interface LaptopRepositoryCustom {
	List<LaptopConfigurationEntity> findAll(LaptopSearchBuilder builder);

}
