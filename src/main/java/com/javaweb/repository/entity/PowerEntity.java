package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="power")
public class PowerEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="type")
	private String type;
	
	@Column(name="battery")
	private Integer battery;

	@Column(name="powersupply")
	private Integer powerSupply;

	
	@OneToMany(mappedBy= "power", fetch=FetchType.LAZY)
	private List<LaptopConfigurationEntity> laptopConfiguration=new ArrayList<>();


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Integer getBattery() {
		return battery;
	}


	public void setBattery(Integer battery) {
		this.battery = battery;
	}


	public Integer getPowerSupply() {
		return powerSupply;
	}


	public void setPowerSupply(Integer powerSupply) {
		this.powerSupply = powerSupply;
	}


	public List<LaptopConfigurationEntity> getLaptopConfiguration() {
		return laptopConfiguration;
	}


	public void setLaptopConfiguration(List<LaptopConfigurationEntity> laptopConfiguration) {
		this.laptopConfiguration = laptopConfiguration;
	}
	
	
}