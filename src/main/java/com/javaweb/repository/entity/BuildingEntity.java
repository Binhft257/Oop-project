package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="building")

public class BuildingEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="street")
	private String street;
	
	@Column(name="ward")
	private String ward;
	
	
	@Column(name="numberofbasement")
	private Integer numberOfBasement;
	
	@Column(name="managername")
	private String managerName;
	
	@Column(name="managerphonenumber")
	private Integer managerPhoneNumber;
	
	@Column(name="floorarea")
	private Integer floorArea;

	@Column(name="rentprice")
	private Integer rentPrice;
	
	@Column(name="servicefee")
	private Integer serviceFee;
	
	@Column(name="brokeragefee")
	private Integer brokerageFee;
	
	@ManyToOne
	@JoinColumn(name= "district_id")
	private DistrictEntity district;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="buildingrenttype",
			   joinColumns = @JoinColumn(name="building_id", nullable=false),
			   inverseJoinColumns = @JoinColumn(name="renttype_id", nullable=false))
		private List<RentTypeEntity> rents= new ArrayList<>();
	
	
	public DistrictEntity getDistrict() {
		return district;
	}
	public void setDistrict(DistrictEntity district) {
		this.district = district;
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

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getNumberOfBasement() {
		return numberOfBasement;
	}
	public void setNumberOfBasement(Integer numberOfBasement) {
		this.numberOfBasement = numberOfBasement;
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
	public Integer getFloorArea() {
		return floorArea;
	}
	public void setFloorArea(Integer floorArea) {
		this.floorArea = floorArea;
	}
	public Integer getRentPrice() {
		return rentPrice;
	}
	public void setRentPrice(Integer rentPrice) {
		this.rentPrice = rentPrice;
	}
	public Integer getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(Integer serviceFee) {
		this.serviceFee = serviceFee;
	}
	public Integer getBrokerageFee() {
		return brokerageFee;
	}
	public void setBrokerageFee(Integer brokerageFee) {
		this.brokerageFee = brokerageFee;
	}
	public List<RentTypeEntity> getRents() {
		return rents;
	}
	public void setRents(List<RentTypeEntity> rents) {
		this.rents = rents;
	}
	
	

	
}