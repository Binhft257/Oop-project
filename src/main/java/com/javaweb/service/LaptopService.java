package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.model.LaptopModelDTO;

public interface LaptopService {

    List<LaptopModelDTO> findAll(
        Map<String, Object> params,
        List<Integer> ram,
        List<String> gpu,
        List<Integer> storageCapacity,
        Integer page,
        Integer pageSize
    );
    int countAll(
        Map<String, Object> params,
        List<Integer> ram,
        List<String> gpu,
        List<Integer> storageCapacity
    );
}
