package com.javaweb.repository.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
@Entity
@Table(name="gpu")
public class GpuEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="chip")
	private String chip;
	
	@Column(name="bus")
	private Integer bus;

	@Column(name="memory")
	private Integer memory;
		
	@Column(name="clockrate")
	private Integer clockRate;
	
	@OneToMany(mappedBy= "gpu", fetch=FetchType.LAZY)
	private List<LaptopConfigurationEntity> op=new ArrayList<>();

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

	public String getChip() {
		return chip;
	}

	public void setChip(String chip) {
		this.chip = chip;
	}

	public Integer getBus() {
		return bus;
	}

	public void setBus(Integer bus) {
		this.bus = bus;
	}

	public Integer getMemory() {
		return memory;
	}

	public void setMemory(Integer memory) {
		this.memory = memory;
	}

	public Integer getClockRate() {
		return clockRate;
	}

	public void setClockRate(Integer clockRate) {
		this.clockRate = clockRate;
	}

	public List<LaptopConfigurationEntity> getOp() {
		return op;
	}

	public void setOp(List<LaptopConfigurationEntity> op) {
		this.op = op;
	}

	
	
}
