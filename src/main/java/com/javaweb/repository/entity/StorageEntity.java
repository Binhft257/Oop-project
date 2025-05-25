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
@Table(name="storage")
public class StorageEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="capacity")
	private Integer	 capacity;
	
	@Column(name="type")
	private String type;

	@OneToMany(mappedBy= "storage", fetch=FetchType.LAZY)
	private List<LaptopConfigurationEntity> op=new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getType() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}

	public List<LaptopConfigurationEntity> getOp() {
		return op;
	}

	public void setOp(List<LaptopConfigurationEntity> op) {
		this.op = op;
	}

	
	
	
}