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
@Table(name="assignmentbuilding")
public class AssignmentBuildingEntity {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;

	
	@Column(name="user_id")
	private Integer userId;
	
	@ManyToOne
	@JoinColumn(name= "building_id")
	private BuildingEntity bd;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BuildingEntity getBd() {
		return bd;
	}

	public void setBd(BuildingEntity bd) {
		this.bd = bd;
	}
	
	
	
}
