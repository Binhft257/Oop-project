package com.javaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.javaweb.model.LaptopModelDTO;
import com.javaweb.service.LaptopService;

import customexception.FieldRequiredException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class LaptopAPI {
	@Autowired
	private LaptopService laptopService;

	@GetMapping(value = "/api/building/")
	public List<LaptopModelDTO> getBuilding(@RequestParam Map<String, Object> params,
			@RequestParam(name = "ram", required = false) List<Integer> ram,
			@RequestParam(name = "gpu", required = false) List<String> gpu,
			@RequestParam(name = "storageCapacity", required = false) List<Integer> storageCapacity) {
		if (params.containsKey("brand")) {
            String brand = params.get("brand").toString().trim();

            // Check nếu brand chỉ toàn là số
            if (!brand.isEmpty() && brand.matches(".*\\d.*")) {
                throw new FieldRequiredException("Tên hãng không hợp lệ, không được là số.");
            }
        }
		List<LaptopModelDTO> result = laptopService.findAll(params, ram, gpu, storageCapacity);
		return result;
	}

}
