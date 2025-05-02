package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingSearchRequest;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentTypeRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentTypeEntity;
import com.javaweb.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {
	@Autowired
	private BuildingRepository buildingRepository;
	@Autowired
	
	private ModelMapper modelMapper;
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchBuilderConverter;

	@Override

	public List<BuildingDTO> findAll(Map<String, Object> params, List<String> buildingRentType) {
		BuildingSearchBuilder buildingSearchBuilder = buildingSearchBuilderConverter.toBuildingSearchBuilder(params,
				buildingRentType);
		List<BuildingDTO> result = new ArrayList<>();
		List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchBuilder);
		for (BuildingEntity item : buildingEntities) {
			BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
			String rent = item.getRents().stream().map(it -> it.getName()).distinct()
					.collect(Collectors.joining(","));

			building.setBuildingRentType(rent);

			building.setAddress(item.getStreet() + "," + item.getWard() + "," + item.getDistrict().getName());

			result.add(building);
		}

		return result;
	}

}
