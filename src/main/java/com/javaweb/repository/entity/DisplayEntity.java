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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="display")
public class DisplayEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="size")
	private Float size;
	
	@Column(name="type")
	private String type;
	
	@Column(name="refreshrate")
	private Integer refreshRate;

	@Column(name="brightness")
	private Integer brightness;
	
	@Column(name="ratio")
	private String ratio;
	
	@Column(name="resolution")
	private String resolution;
	
	@OneToMany(mappedBy= "display", fetch=FetchType.LAZY)
	private List<LaptopConfigurationEntity> laptopConfiguration=new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getSize() {
		return size;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getRefreshRate() {
		return refreshRate;
	}

	public void setRefreshRate(Integer refreshRate) {
		this.refreshRate = refreshRate;
	}

	public Integer getBrightness() {
		return brightness;
	}

	public void setBrightness(Integer brightness) {
		this.brightness = brightness;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public List<LaptopConfigurationEntity> getLaptopConfiguration() {
		return laptopConfiguration;
	}

	public void setLaptopConfiguration(List<LaptopConfigurationEntity> laptopConfiguration) {
		this.laptopConfiguration = laptopConfiguration;
	}
	
	
}
