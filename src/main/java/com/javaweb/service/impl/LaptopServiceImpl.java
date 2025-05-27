package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.builder.LaptopSearchBuilder;
import com.javaweb.converter.LaptopModelMapper;
import com.javaweb.converter.LaptopSearchBuilderConverter;
import com.javaweb.model.LaptopModelDTO;
import com.javaweb.model.ReviewDTO;
import com.javaweb.repository.custom.LaptopRepositoryCustom;
import com.javaweb.repository.entity.LaptopConfigurationEntity;
import com.javaweb.repository.entity.ReviewsEntity;
import com.javaweb.service.LaptopService;

@Service
public class LaptopServiceImpl implements LaptopService {

	@Autowired
	private LaptopSearchBuilderConverter laptopSearchBuilderConverter;

	@Autowired
	private LaptopRepositoryCustom laptopRepository;

	@Autowired
	private LaptopModelMapper laptopModelMapper;

	@Override
	public List<LaptopModelDTO> findAll(Map<String, Object> params, List<Integer> ramList, List<String> gpuList,
			List<Integer> storageCapacity, Integer page, Integer pageSize) {
		LaptopSearchBuilder builder = laptopSearchBuilderConverter.toLaptopSearchBuilder(params, ramList, gpuList,
				storageCapacity);

		if (page == null || page < 1)
			page = 1;
		if (pageSize == null || pageSize < 1)
			pageSize = 30;

		int offset = (page - 1) * pageSize;
		List<LaptopConfigurationEntity> configs = laptopRepository.findAll(builder, offset, pageSize);

		List<LaptopModelDTO> result = new ArrayList<>();
		for (LaptopConfigurationEntity config : configs) {
			result.add(laptopModelMapper.toDto(config));
		}
		return result;
	}

	@Override
	public int countAll(Map<String, Object> params, List<Integer> ramList, List<String> gpuList,
			List<Integer> storageCapacity) {
		LaptopSearchBuilder builder = laptopSearchBuilderConverter.toLaptopSearchBuilder(params, ramList, gpuList,
				storageCapacity);

		return laptopRepository.countAll(builder);
	}
}