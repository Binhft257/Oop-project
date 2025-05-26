package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.LaptopSearchBuilder;
import com.javaweb.util.MapUtil;
@Component
public class LaptopSearchBuilderConverter {
	public LaptopSearchBuilder toLaptopSearchBuilder(Map<String, Object> params, List<Integer> ram,List<String> gpu, List<Integer> storageCapacity) {
		LaptopSearchBuilder laptopSearchBuilder = new LaptopSearchBuilder.Builder()
				.setBrand(MapUtil.getObject(params, "brand", String.class))
				.setModel(MapUtil.getObject(params, "model", String.class))
				.setPriceFrom(MapUtil.getObject(params, "priceFrom", Integer.class))
				.setPriceTo(MapUtil.getObject(params, "priceTo", Integer.class))
				.setCpu(MapUtil.getObject(params, "cpu", String.class))
				.setGpu(gpu)
				.setRam(ram)
				.setDisplaySizeFrom(MapUtil.getObject(params, "displaySizeFrom", Integer.class))
				.setDisplaySizeTo(MapUtil.getObject(params, "displaySizeTo", Integer.class))
				.setColor(MapUtil.getObject(params, "color", String.class))
				.setOs(MapUtil.getObject(params, "os", String.class))
				.setStorageCapacity(storageCapacity)
				.build();

		return laptopSearchBuilder;
	}
}

