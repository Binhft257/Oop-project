package com.javaweb.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.javaweb.model.LaptopModelDTO;
import com.javaweb.service.LaptopService;


@RestController
@RequestMapping("/api/building")
public class LaptopAPI {

    @Autowired
    private LaptopService laptopService;

    @GetMapping
    public List<LaptopModelDTO> getBuilding(
            @RequestParam Map<String, Object> params,
            @RequestParam(name = "ram", required = false) List<Integer> ram,
            @RequestParam(name = "gpu", required = false) List<String> gpu,
            @RequestParam(name = "storageCapacity", required = false) List<Integer> storageCapacity,
            @RequestParam(name = "page", required = false) Integer page,
            @RequestParam(name = "pageSize", required = false) Integer pageSize) {


        return laptopService.findAll(params, ram, gpu, storageCapacity, page, pageSize);
    }

    @GetMapping("/count")
    public int getTotalCount(
            @RequestParam Map<String, Object> params,
            @RequestParam(name = "ram", required = false) List<Integer> ram,
            @RequestParam(name = "gpu", required = false) List<String> gpu,
            @RequestParam(name = "storageCapacity", required = false) List<Integer> storageCapacity) {

        return laptopService.countAll(params, ram, gpu, storageCapacity);
    }
}
