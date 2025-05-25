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
@Table(name="laptopconfiguration")

public class LaptopConfigurationEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	
	@Column(name="specifications")
	private String specifications;
	
	@Column(name="material")
	private String material;
	
	
	@Column(name="os")
	private String os;
	
	@Column(name="warranty")
	private Integer warranty;
	
	@Column(name="color")
	private String color;
	
	@Column(name="image_url")
	private String image_url;
	
	@Column(name="Price")
	private Integer price;
	@ManyToOne
	@JoinColumn(name= "model_id")
	private LaptopModelEntity laptopModel;
	
	@ManyToOne
	@JoinColumn(name= "cpu_id")
	private CpuEntity cpu;
	
	@ManyToOne
	@JoinColumn(name= "display_id")
	private DisplayEntity display;
	
	@ManyToOne
	@JoinColumn(name= "power_id")
	private PowerEntity power;
	
	@ManyToOne
	@JoinColumn(name= "connectivity_id")
	private ConnectivityEntity connectivity;
	
	@ManyToOne
	@JoinColumn(name= "rating_id")
	private RatingEntity rating;
	
	@ManyToOne
	@JoinColumn(name= "gpu_id")
	private GpuEntity gpu;
	
	@ManyToOne
	@JoinColumn(name= "ram_id")
	private RamEntity ram;
	
	@ManyToOne
	@JoinColumn(name= "storage_id")
	private StorageEntity storage;
	
	
	@OneToMany(mappedBy= "config", fetch=FetchType.LAZY)
	private List<ReviewsEntity> reviews=new ArrayList<>();
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public Integer getWarranty() {
		return warranty;
	}

	public void setWarranty(Integer warranty) {
		this.warranty = warranty;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public LaptopModelEntity getLaptopModel() {
		return laptopModel;
	}

	public void setLaptopModel(LaptopModelEntity laptopModel) {
		this.laptopModel = laptopModel;
	}

	public CpuEntity getCpu() {
		return cpu;
	}

	public void setCpu(CpuEntity cpu) {
		this.cpu = cpu;
	}

	public DisplayEntity getDisplay() {
		return display;
	}

	public void setDisplay(DisplayEntity display) {
		this.display = display;
	}

	public PowerEntity getPower() {
		return power;
	}

	public void setPower(PowerEntity power) {
		this.power = power;
	}

	public ConnectivityEntity getConnectivity() {
		return connectivity;
	}

	public void setConnectivity(ConnectivityEntity connectivity) {
		this.connectivity = connectivity;
	}

	public RatingEntity getRating() {
		return rating;
	}

	public void setRating(RatingEntity rating) {
		this.rating = rating;
	}


	public List<ReviewsEntity> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewsEntity> reviews) {
		this.reviews = reviews;
	}

	public GpuEntity getGpu() {
		return gpu;
	}

	public void setGpu(GpuEntity gpu) {
		this.gpu = gpu;
	}

	public RamEntity getRam() {
		return ram;
	}

	public void setRam(RamEntity ram) {
		this.ram = ram;
	}

	public StorageEntity getStorage() {
		return storage;
	}

	public void setStorage(StorageEntity storage) {
		this.storage = storage;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	
	
	
	
	
	
	

	
}