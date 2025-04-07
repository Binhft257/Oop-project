package com.javaweb.repository.entity;

public class TotalEntity {
	private BuildingEntity buildingEntity;
	private DistrictEntity districtEntity;
	private RentTypeEntity rentTypeEntity;
	private UserEntity userEntity;
	private AssignmentBuildingEntity assignmentBuildingEntity;
	private BuildingRentTypeEntity buildingRentTypeEntity;
	
	public BuildingEntity getBuildingEntity() {
		return buildingEntity;
	}
	public void setBuildingEntity(BuildingEntity buildingEntity) {
		this.buildingEntity = buildingEntity;
	}
	public DistrictEntity getDistrictEntity() {
		return districtEntity;
	}
	public void setDistrictEntity(DistrictEntity districtEntity) {
		this.districtEntity = districtEntity;
	}
	public RentTypeEntity getRentTypeEntity() {
		return rentTypeEntity;
	}
	public void setRentTypeEntity(RentTypeEntity rentTypeEntity) {
		this.rentTypeEntity = rentTypeEntity;
	}
	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	public AssignmentBuildingEntity getAssignmentBuildingEntity() {
		return assignmentBuildingEntity;
	}
	public void setAssignmentBuildingEntity(AssignmentBuildingEntity assignmentBuildingEntity) {
		this.assignmentBuildingEntity = assignmentBuildingEntity;
	}
	public BuildingRentTypeEntity getBuildingRentTypeEntity() {
		return buildingRentTypeEntity;
	}
	public void setBuildingRentTypeEntity(BuildingRentTypeEntity buildingRentTypeEntity) {
		this.buildingRentTypeEntity = buildingRentTypeEntity;
	}
	
}
