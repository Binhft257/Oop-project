package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="cpu")

public class CpuEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="model")
	private String model;
	
	@Column(name="basespeed")
	private Float baseSpeed;
	
	@Column(name="maxspeed")
	private Float maxSpeed;

	@Column(name="cores")
	private Integer cores;
	
	@Column(name="threads")
	private Integer threads;
	
	@ManyToOne
	@JoinColumn(name= "brand_id")
	private BrandEntity brands;
	
	@OneToMany(mappedBy= "cpu", fetch=FetchType.LAZY)
	private List<LaptopConfigurationEntity> laptopConfiguration=new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Float getBaseSpeed() {
		return baseSpeed;
	}

	public void setBaseSpeed(Float baseSpeed) {
		this.baseSpeed = baseSpeed;
	}

	public Float getMaxSpeed() {
		return maxSpeed;
	}

	public void setMaxSpeed(Float maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public Integer getCores() {
		return cores;
	}

	public void setCores(Integer cores) {
		this.cores = cores;
	}

	public Integer getThreads() {
		return threads;
	}

	public void setThreads(Integer threads) {
		this.threads = threads;
	}

	public BrandEntity getBrands() {
		return brands;
	}

	public void setBrands(BrandEntity brands) {
		this.brands = brands;
	}

	public List<LaptopConfigurationEntity> getLaptopConfiguration() {
		return laptopConfiguration;
	}

	public void setLaptopConfiguration(List<LaptopConfigurationEntity> laptopConfiguration) {
		this.laptopConfiguration = laptopConfiguration;
	}
	
	


	
 


}
