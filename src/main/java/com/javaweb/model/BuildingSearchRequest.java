package com.javaweb.model;

import java.util.List;

public class BuildingSearchRequest { // nhận vào từ 16 field
	private String name;
	private Integer floorArea;
	private Integer districtId;
	private String ward;
	private String street;
	private String numberOfBasement;
	private String direction;
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
	public void setName(String name) {
		this.name = name;
	}
	public Integer getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}
	public Integer getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Integer districtId) {
		this.districtId = districtId;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(String numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Integer getFloorAreaFrom() {
		return floorAreaFrom;
	}
	public void setFloorAreaFrom(Integer floorAreaFrom) {
		this.floorAreaFrom = floorAreaFrom;
	}
	public Integer getFloorAreaTo() {
		return floorAreaTo;
	}
	public void setFloorAreaTo(Integer floorAreaTo) {
		this.floorAreaTo = floorAreaTo;
	}
	public Integer getRentPriceFrom() {
		return rentPriceFrom;
	}
	public void setRentPriceFrom(Integer rentPriceFrom) {
		this.rentPriceFrom = rentPriceFrom;
	}
	public Integer getRentPriceTo() {
		return rentPriceTo;
	}
	public void setRentPriceTo(Integer rentPriceTo) {
		this.rentPriceTo = rentPriceTo;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public Integer getManagerPhoneNumber() {
		return managerPhoneNumber;
	}
	public void setManagerPhoneNumber(Integer managerPhoneNumber) {
		this.managerPhoneNumber = managerPhoneNumber;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public List<String> getBuildingRentType() {
		return buildingRentType;
	}
	public void setBuildingRentType(List<String> buildingRentType) {
		this.buildingRentType = buildingRentType;
	}
	
	
}