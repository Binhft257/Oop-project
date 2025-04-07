	package com.javaweb.service;

import java.util.List;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingSearchRequest;

public interface BuildingService {
	List<BuildingDTO> findAll(BuildingSearchRequest request);
	
}
