package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingSearchRequest;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.TotalEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService{
	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	
	public List<BuildingDTO> findAll(BuildingSearchRequest request) {
		List<TotalEntity> list = buildingRepository.findAll(request);
		List<BuildingDTO> buildings= new ArrayList<>();
		for(TotalEntity x : list) {
			BuildingDTO building= new BuildingDTO() ;
			building.setName(x.getBuildingEntity().getName());
			building.setAddress(x.getBuildingEntity().getStreet()+","+x.getBuildingEntity().getWard()+","+ x.getDistrictEntity().getDistrictName());
			building.setNumberOfBasement(x.getBuildingEntity().getNumberOfBasement());
			building.setManagerName(x.getBuildingEntity().getManagerName());
			building.setManagerPhoneNumber(x.getBuildingEntity().getManagerPhoneNumber());
			building.setFloorArea(x.getBuildingEntity().getFloorArea());
			building.setRentPrice(x.getBuildingEntity().getRentPrice());
			building.setServicePrice(x.getBuildingEntity().getServiceFee());
			building.setBrokageFee(x.getBuildingEntity().getBrokerageFee());
			building.setBuildingRentType(x.getRentTypeEntity().getRentTypeName());
			buildings.add(building);
		}
		return buildings;
	}
	
}
