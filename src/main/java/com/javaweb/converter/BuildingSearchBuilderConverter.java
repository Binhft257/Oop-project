package com.javaweb.converter;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.util.MapUtil;
@Component
public class BuildingSearchBuilderConverter {
	public BuildingSearchBuilder toBuildingSearchBuilder(Map<String,Object> params, List<String> buildingRentType) {
		 BuildingSearchBuilder  buildingSearchBuilder = new  BuildingSearchBuilder.Builder()    .setName(MapUtil.getObject(params, "name", String.class))
				    .setFloorArea(MapUtil.getObject(params, "floorArea", Integer.class))
				    .setWard(MapUtil.getObject(params, "ward", String.class))
				    .setStreet(MapUtil.getObject(params, "street", String.class))
				    .setDistrictId(MapUtil.getObject(params, "districtId", Integer.class))
				    .setNumberOfBasement(MapUtil.getObject(params, "numberOfBasement", Integer.class))
				    .setBuildingRentType(buildingRentType)
				    .setManagerName(MapUtil.getObject(params, "managerName", String.class))
				    .setManagerPhoneNumber(MapUtil.getObject(params, "managerPhoneNumber", Integer.class))
				    .setRentPriceTo(MapUtil.getObject(params, "rentPriceTo", Integer.class))
				    .setRentPriceFrom(MapUtil.getObject(params, "rentPriceFrom", Integer.class))
				    .setFloorAreaFrom(MapUtil.getObject(params, "floorAreafrom", Integer.class))
				    .setFloorAreaTo(MapUtil.getObject(params, "floorAreato", Integer.class))
				    .setUserId(MapUtil.getObject(params, "userId", Integer.class))
				    .build();
				 																			
				 												
		 return buildingSearchBuilder;
	}
}
