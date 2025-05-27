package com.javaweb.converter;

import com.javaweb.model.*;
import com.javaweb.repository.entity.*;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class LaptopModelMapper {

	public static LaptopModelDTO toDto(LaptopConfigurationEntity config) {
		LaptopModelDTO dto = new LaptopModelDTO();
		dto.setBrand(config.getLaptopModel().getBrand().getName());
		dto.setModel(config.getLaptopModel().getModelName());
		dto.setOs(config.getOs());
		dto.setCpu(config.getCpu().getModel());
		dto.setMaterial(config.getMaterial());
		dto.setSpecifications(config.getSpecifications());
		dto.setDisplaySize(config.getDisplay().getSize());
		dto.setColor(config.getColor());
		dto.setRefreshRate(config.getDisplay().getRefreshRate());
		dto.setDisplayResolution(config.getDisplay().getResolution());
		dto.setRam(config.getRam().getCapacity());
		dto.setGpu(config.getGpu().getName());
		dto.setStorage(config.getStorage().getType());
		dto.setPrice(config.getPrice());
		dto.setImage_url(config.getImage_url());
		dto.setRate(config.getRating().getAverage());

		List<ReviewDTO> reviewDTOs = config.getReviews().stream().map(r -> {
			ReviewDTO rv = new ReviewDTO();
			rv.setAuthor(r.getUser());
			rv.setComment(r.getContent());
			return rv;
		}).collect(Collectors.toList());

		dto.setReviews(reviewDTOs);
		return dto;
	}
}
