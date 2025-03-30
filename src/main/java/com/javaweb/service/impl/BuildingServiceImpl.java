package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;
	@Override
	public List<BuildingDTO> findAll(String name, Integer numberofbasement) {
		List<BuildingEntity> list = buildingRepository.findAll(name,numberofbasement);
		List<BuildingDTO> buildings= new ArrayList<>();
		for(BuildingEntity x : list) {
			BuildingDTO building= new BuildingDTO();
			building.setName(x.getName());
			building.setAddress(x.getStreet()+" "+x.getWard());
			building.setNumberOfBasement(x.getNumberOfBasement());
			buildings.add(building);
		}
		return buildings;
	}
	
}
