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
@Table(name="brand")

public class BrandEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name="name")
	private String name;
	
	@OneToMany(mappedBy= "brand", fetch=FetchType.LAZY)
	private List<LaptopModelEntity> laptopModel=new ArrayList<>();
	
	@OneToMany(mappedBy= "brands", fetch=FetchType.LAZY)
	private List<CpuEntity> cpu=new ArrayList<>();
	
	

	public List<CpuEntity> getCpu() {
		return cpu;
	}

	public void setCpu(List<CpuEntity> cpu) {
		this.cpu = cpu;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LaptopModelEntity> getLaptopModel() {
		return laptopModel;
	}

	public void setLaptopModel(List<LaptopModelEntity> laptopModel) {
		this.laptopModel = laptopModel;
	}


}
	