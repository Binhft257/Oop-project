package com.javaweb.repository;

import java.util.List;

import com.javaweb.repository.entity.RentTypeEntity;

public interface RentTypeRepository {
	RentTypeEntity findRentTypeById(Integer x);
}
