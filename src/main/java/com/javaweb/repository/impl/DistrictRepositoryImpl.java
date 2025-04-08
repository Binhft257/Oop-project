package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.util.ConnectionJDBCUtil;
@Repository
public class DistrictRepositoryImpl implements DistrictRepository {

	@Override
	public DistrictEntity findNameById(Integer x) {
		 DistrictEntity districtEntity = new DistrictEntity();
		StringBuilder sql= new StringBuilder("select district.name from district where district.id = " + x);
	     try(Connection conn= ConnectionJDBCUtil.getConnection(); 
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(sql.toString()	)){
	            
	            
	            while(rs.next()) {
            
             districtEntity.setDistrictName(rs.getString("district.name"));
            
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Connected database failed...");
	        }
		return districtEntity;
	}
	}
	

