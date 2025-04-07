package com.javaweb.repository;


import java.util.List;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingSearchRequest;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.TotalEntity;

public interface BuildingRepository {
	List<TotalEntity> findAll(BuildingSearchRequest request);
	
}
