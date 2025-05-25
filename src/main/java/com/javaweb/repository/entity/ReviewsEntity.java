package com.javaweb.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="reviews")

public class ReviewsEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="user")
	private String user;
	
	@Column(name="content")
	private String content;
	
	@Column(name="date")
	private String date;
	
	@ManyToOne
	@JoinColumn(name= "configuration_id")
	private LaptopConfigurationEntity config;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public LaptopConfigurationEntity getConfig() {
		return config;
	}

	public void setConfig(LaptopConfigurationEntity config) {
		this.config = config;
	}

	
	
}