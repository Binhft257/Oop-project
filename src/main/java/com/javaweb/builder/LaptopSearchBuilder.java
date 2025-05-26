package com.javaweb.builder;

import java.util.List;

public class LaptopSearchBuilder {
	private String brand;
	private String model;
	private Integer priceFrom;
	private Integer priceTo;
	private String cpu;
	private List<String> gpu;
	private List<Integer> ram;
	private Integer displaySizeFrom;
	private Integer displaySizeTo;
	private String color;
	private List<Integer> storageCapacity;
	private String Os;
	
	

	public String getModel() {
		return model;
	}

	public List<Integer> getStorageCapacity() {
		return storageCapacity;
	}

	public String getOs() {
		return Os;
	}

	public String getBrand() {
		return brand;
	}

	public Integer getPriceFrom() {
		return priceFrom;
	}

	public Integer getPriceTo() {
		return priceTo;
	}

	public String getCpu() {
		return cpu;
	}

	public List<String> getGpu() {
		return gpu;
	}

	public List<Integer> getRam() {
		return ram;
	}

	public Integer getDisplaySizeFrom() {
		return displaySizeFrom;
	}

	public Integer getDisplaySizeTo() {
		return displaySizeTo;
	}


	public String getColor() {
		return color;
	}

	public LaptopSearchBuilder(Builder builder) {
		super();
		this.brand = builder.brand;
		this.model = builder.model;
		this.priceFrom = builder.priceFrom;
		this.priceTo = builder.priceTo;
		this.cpu = builder.cpu;
		this.gpu = builder.gpu;
		this.ram = builder.ram;
		this.displaySizeFrom = builder.displaySizeFrom;
		this.displaySizeTo = builder.displaySizeTo;
		this.color = builder.color;
		this.Os=builder.Os;
		this.storageCapacity=builder.storageCapacity;
	}


	public static class Builder {
		private String brand;
		private String model;
		private Integer priceFrom;
		private Integer priceTo;
		private String cpu;
		private List<String> gpu;
		private List<Integer> ram;
		private Integer displaySizeFrom;
		private Integer displaySizeTo;
		private String color;
		private List<Integer> storageCapacity;
		private String Os;
		
		

		public Builder setStorageCapacity(List<Integer> storageCapacity) {
			this.storageCapacity = storageCapacity;
			return this;
		}

		public Builder setOs(String os) {
			this.Os = os;
			return this;
		}

		public Builder setBrand(String brand) {
			this.brand = brand;
			return this;
		}
		public Builder setModel(String model) {
			this.model = model;
			return this;
		}


		public Builder setPriceFrom(Integer priceFrom) {
			this.priceFrom = priceFrom;
			return this;
		}

		public Builder setPriceTo(Integer priceTo) {
			this.priceTo = priceTo;
			return this;
		}

		public Builder setCpu(String cpu) {
			this.cpu = cpu;
			return this;
		}

		public Builder setGpu(List<String> gpu) {
			this.gpu = gpu;
			return this;
		}

		

		public Builder setRam(List<Integer> ram) {
			this.ram = ram;
			return this;
		}

		public Builder setDisplaySizeFrom(Integer displaySizeFrom) {
			this.displaySizeFrom = displaySizeFrom;
			return this;
		}

		public Builder setDisplaySizeTo(Integer displaySizeTo) {
			this.displaySizeTo = displaySizeTo;
			return this;
		}

		public Builder setColor(String color) {
			this.color = color;
			return this;
		}


		public LaptopSearchBuilder build() {
			return new LaptopSearchBuilder(this);
		}
	}

}
