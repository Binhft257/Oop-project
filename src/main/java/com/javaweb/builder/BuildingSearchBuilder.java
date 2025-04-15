 package com.javaweb.builder;

import java.util.List;

public class BuildingSearchBuilder {
	private String name;
	private Integer floorArea;
	private Integer districtId;
	private String ward;
	private String street;
	private Integer numberOfBasement;
	private Integer floorAreaFrom;
	private Integer floorAreaTo;
	private Integer rentPriceFrom;
	private Integer rentPriceTo;
	private String managerName;
	private Integer managerPhoneNumber;
	private Integer userId;
	private List<String> buildingRentType;
	public String getName() {
		return name;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public String getWard() {
		return ward;
	}
	public String getStreet() {
		return street;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public Integer getFloorAreaFrom() {
		return floorAreaFrom;
	}
	public Integer getFloorAreaTo() {
		return floorAreaTo;
	}
	public Integer getRentPriceFrom() {
		return rentPriceFrom;
	}
	public Integer getRentPriceTo() {
		return rentPriceTo;
	}
	public String getManagerName() {
		return managerName;
	}
	public Integer getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public Integer getUserId() {
		return userId;
	}
	public List<String> getBuildingRentType() {
		return buildingRentType;
	}
	
	
	


	public BuildingSearchBuilder(Builder builder) {
		super();
		this.name = builder.name;
		this.floorArea = builder.floorArea;
		this.districtId = builder.districtId;
		this.ward = builder.ward;
		this.street = builder.street;
		this.numberOfBasement = builder.numberOfBasement;
		this.floorAreaFrom = builder.floorAreaFrom;
		this.floorAreaTo = builder.floorAreaTo;
		this.rentPriceFrom = builder.rentPriceFrom;
		this.rentPriceTo = builder.rentPriceTo;
		this.managerName = builder.managerName;
		this.managerPhoneNumber = builder.managerPhoneNumber;
		this.userId = builder.userId;
		this.buildingRentType = builder.buildingRentType;
	}





	public static class Builder{
		private String name;
		private Integer floorArea;
		private Integer districtId;
		private String ward;
		private String street;
		private Integer numberOfBasement;
		private Integer floorAreaFrom;
		private Integer floorAreaTo;
		private Integer rentPriceFrom;
		private Integer rentPriceTo;
		private String managerName;
		private Integer managerPhoneNumber;
		private Integer userId;
		private List<String> buildingRentType;
		public Builder setName(String name) {
		    this.name = name;
		    return this;
		}
		public Builder setFloorArea(Integer floorArea) {
		    this.floorArea = floorArea;
		    return this;
		}
		public Builder setDistrictId(Integer districtId) {
		    this.districtId = districtId;
		    return this;
		}
		public Builder setWard(String ward) {
		    this.ward = ward;
		    return this;
		}
		public Builder setStreet(String street) {
		    this.street = street;
		    return this;
		}
		public Builder setNumberOfBasement(Integer numberOfBasement) {
		    this.numberOfBasement = numberOfBasement;
		    return this;
		}
		public Builder setFloorAreaFrom(Integer floorAreaFrom) {
		    this.floorAreaFrom = floorAreaFrom;
		    return this;
		}
		public Builder setFloorAreaTo(Integer floorAreaTo) {
		    this.floorAreaTo = floorAreaTo;
		    return this;
		}
		public Builder setRentPriceFrom(Integer rentPriceFrom) {
		    this.rentPriceFrom = rentPriceFrom;
		    return this;
		}
		public Builder setRentPriceTo(Integer rentPriceTo) {
		    this.rentPriceTo = rentPriceTo;
		    return this;
		}
		public Builder setManagerName(String managerName) {
		    this.managerName = managerName;
		    return this;
		}
		public Builder setManagerPhoneNumber(Integer managerPhoneNumber) {
		    this.managerPhoneNumber = managerPhoneNumber;
		    return this;
		}
		public Builder setUserId(Integer userId) {
		    this.userId = userId;
		    return this;
		}
		public Builder setBuildingRentType(List<String> buildingRentType) {
		    this.buildingRentType = buildingRentType;
		    return this;
		}

		public BuildingSearchBuilder build(){
			return new BuildingSearchBuilder(this);
		}
	}
	
}
