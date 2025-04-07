package com.javaweb.repository.entity;

public class RentTypeEntity {
	private Integer rentTypeId;
	private String rentTypeCode;
	private String rentTypeName;
	public Integer getRentTypeId() {
		return rentTypeId;
	}
	public void setRentTypeId(Integer rentTypeId) {
		this.rentTypeId = rentTypeId;
	}
	public String getRentTypeCode() {
		return rentTypeCode;
	}
	public void setRentTypeCode(String rentTypeCode) {
		this.rentTypeCode = rentTypeCode;
	}
	public String getRentTypeName() {
		return rentTypeName;
	}
	public void setRentTypeName(String rentTypeName) {
		this.rentTypeName = rentTypeName;
	}
	
}
