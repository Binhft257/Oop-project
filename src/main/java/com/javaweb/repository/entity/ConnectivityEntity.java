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
@Table(name="connectivity")
public class ConnectivityEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="count")
	private Integer count;

	@Column(name="port")
	private String port;

	
	@OneToMany(mappedBy= "connectivity", fetch=FetchType.LAZY)
	private List<LaptopConfigurationEntity> laptopConfiguration=new ArrayList<>();


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getCount() {
		return count;
	}


	public void setCount(Integer count) {
		this.count = count;
	}


	public String getPort() {
		return port;
	}


	public void setPort(String port) {
		this.port = port;
	}


	public List<LaptopConfigurationEntity> getLaptopConfiguration() {
		return laptopConfiguration;
	}


	public void setLaptopConfiguration(List<LaptopConfigurationEntity> laptopConfiguration) {
		this.laptopConfiguration = laptopConfiguration;
	}
	
	
}