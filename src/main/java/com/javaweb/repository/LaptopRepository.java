package com.javaweb.repository;


import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaweb.builder.LaptopSearchBuilder;


import com.javaweb.repository.custom.LaptopRepositoryCustom;
import com.javaweb.repository.entity.LaptopConfigurationEntity;


public interface LaptopRepository extends JpaRepository<LaptopConfigurationEntity,Integer>,LaptopRepositoryCustom{
}
