package com.javaweb.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.builder.LaptopSearchBuilder;
import com.javaweb.converter.LaptopSearchBuilderConverter;
import com.javaweb.model.LaptopModelDTO;
import com.javaweb.model.ReviewDTO;
import com.javaweb.repository.LaptopRepository;
import com.javaweb.repository.entity.LaptopConfigurationEntity;
import com.javaweb.repository.entity.LaptopModelEntity;
import com.javaweb.repository.entity.ReviewsEntity;
import com.javaweb.service.LaptopService;

@Service
public class LaptopServiceImpl implements LaptopService {

    @Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private LaptopSearchBuilderConverter laptopSearchBuilderConverter;

    @Override
    public List<LaptopModelDTO> findAll(Map<String, Object> params, List<Integer> ramList,
                                   List<String> gpuList, List<Integer> storageCapacity) {
        // Build search criteria
        LaptopSearchBuilder builder = laptopSearchBuilderConverter
                .toLaptopSearchBuilder(params, ramList, gpuList, storageCapacity);

        // Fetch filtered configurations
        List<LaptopConfigurationEntity> configs = laptopRepository.findAll(builder);

        // Map each configuration entity to a single flat DTO
        List<LaptopModelDTO> result = new ArrayList<>();
        for (LaptopConfigurationEntity config : configs) {
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
     
            List<ReviewDTO> reviewDTOList = new ArrayList<>();

            for (ReviewsEntity review : config.getReviews()) {
                ReviewDTO rv = new ReviewDTO();
                rv.setAuthor(review.getUser());
                rv.setComment(review.getContent());
                reviewDTOList.add(rv);
            }

            dto.setReviews(reviewDTOList);

            result.add(dto);
        }
        return result;
    }
}
