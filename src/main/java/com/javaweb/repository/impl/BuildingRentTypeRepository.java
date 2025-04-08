package com.javaweb.repository.impl;

import java.util.List;

import com.javaweb.repository.entity.BuildingRentTypeEntity;

public interface BuildingRentTypeRepository {
	List<BuildingRentTypeEntity> findId(Integer x);

}
