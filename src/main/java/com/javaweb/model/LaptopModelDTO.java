package com.javaweb.model;

import java.util.List;

public class LaptopModelDTO {
    private String brand;
    private String model;
    private String os;
    private String cpu;
    private String material;
    private String specifications;
    private Float displaySize;
    private String displayResolution;
    private Integer refreshRate;
    private String color;
    private Integer ram;
    private String gpu;
    private String storage;
    private Integer price;
    private String image_url;
    private List<ReviewDTO> reviews;
    private Float rate;
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getSpecifications() {
		return specifications;
	}
	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}
	public Float getDisplaySize() {
		return displaySize;
	}
	public void setDisplaySize(Float displaySize) {
		this.displaySize = displaySize;
	}
	public String getDisplayResolution() {
		return displayResolution;
	}
	public void setDisplayResolution(String displayResolution) {
		this.displayResolution = displayResolution;
	}
	public Integer getRefreshRate() {
		return refreshRate;
	}
	public void setRefreshRate(Integer refreshRate) {
		this.refreshRate = refreshRate;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getRam() {
		return ram;
	}
	public void setRam(Integer ram) {
		this.ram = ram;
	}
	public String getGpu() {
		return gpu;
	}
	public void setGpu(String gpu) {
		this.gpu = gpu;
	}
	public String getStorage() {
		return storage;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public List<ReviewDTO> getReviews() {
		return reviews;
	}
	public void setReviews(List<ReviewDTO> reviews) {
		this.reviews = reviews;
	}
	public Float getRate() {
		return rate;
	}
	public void setRate(Float rate) {
		this.rate = rate;
	}

    
}
