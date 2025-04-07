package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.model.BuildingSearchRequest;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentTypeEntity;
import com.javaweb.repository.entity.TotalEntity;
@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	static final String DB_URL = "jdbc:mysql://localhost:3306/building_rental";
    static final String USER = "root";
    static final String PASS = "Binh25072005";

	@Override
	public List<TotalEntity> findAll(BuildingSearchRequest request) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT b.name, b.ward, b.street, d.name as district_name, d.id as districtid, b.direction, ");
		sql.append("GROUP_CONCAT(DISTINCT u.id SEPARATOR ', ') as userid, ");
		sql.append("b.numberofbasement, b.managername, b.managerphonenumber, b.floorarea, b.rentprice, ");
		sql.append("GROUP_CONCAT(DISTINCT rt.name SEPARATOR ', ') as renttype_name, ");
		sql.append("b.servicefee, b.brokeragefee ");
		sql.append("FROM building b ");
		sql.append("JOIN district d ON b.district_id = d.id ");
		sql.append("LEFT JOIN buildingrenttype brt ON b.id = brt.building_id ");
		sql.append("LEFT JOIN renttype rt ON brt.renttype_id = rt.id ");
		sql.append("LEFT JOIN assignmentbuilding ab ON b.id = ab.building_id ");
		sql.append("LEFT JOIN user u ON ab.user_id = u.id ");
		sql.append("WHERE 1=1 ");
		
		if(request.getName()!= null && !request.getName().equals("")) {
			sql.append("and b.name like '%"+request.getName()+"%' ");
		}
		
		if(request.getFloorArea()!=null) {
			sql.append("and b.floorarea = "+request.getFloorArea()+" ");
		}
		
		if(request.getDistrictId()!=null) {
			sql.append("and d.id= "+request.getDistrictId()+" ");
		}
		
		if(request.getWard()!= null && !request.getWard().equals("")) {
			sql.append("and b.ward like '%"+request.getWard()+"%' ");
		}
		
		if(request.getStreet()!= null && !request.getStreet().equals("")) {
			sql.append("and b.street like '%"+request.getStreet()+"%' ");
		}
		
		if(request.getNumberOfBasement()!=null) {
			sql.append("and b.numberofbasement = "+request.getNumberOfBasement()+" ");
		}
		 
		if(request.getFloorAreaFrom()!=null) {
			sql.append("and b.floorarea >= "+request.getFloorAreaFrom()+" ");
		}
		
		if(request.getFloorAreaTo()!=null) {
			sql.append("and b.floorarea <= "+request.getFloorAreaTo()+" ");
		}
		
		if(request.getRentPriceFrom()!=null) {
			sql.append("and b.rentprice >= "+request.getRentPriceFrom()+" ");
		}
		
		if(request.getRentPriceTo()!=null) {
			sql.append("and b.rentprice <= "+request.getRentPriceTo()+" ");
		}
		
		if(request.getManagerName()!= null && !request.getManagerName().equals("")) {
			sql.append("and b.managername like '%"+request.getManagerName()+"%' ");
		}
		
		if(request.getManagerPhoneNumber()!=null) {
			sql.append("and b.managerphonenumber = "+request.getManagerPhoneNumber()+" ");
		}
		
		if(request.getUserId()!=null) {
			sql.append("and u.id = "+request.getUserId()+" ");
		}
		
		int rentTypeCount = request.getBuildingRentType() != null ? request.getBuildingRentType().size() : 0;
        if (rentTypeCount > 0) {
            sql.append("AND rt.name IN (");
            for (int i = 0; i < rentTypeCount; i++) {
                sql.append("'").append(request.getBuildingRentType().get(i)).append("'");
                if (i < rentTypeCount - 1) {
                    sql.append(", ");
                }
            }
            sql.append(") ");
        }

        sql.append("GROUP BY b.name, b.ward, b.street, b.direction, d.name, b.numberofbasement, ");
        sql.append("b.managername, b.managerphonenumber, b.floorarea, b.rentprice, ");
        sql.append("b.servicefee, b.brokeragefee, d.id ");

        if (rentTypeCount > 0) {
            sql.append(" HAVING COUNT(DISTINCT rt.name) = ").append(rentTypeCount);
        }

	        List<TotalEntity> result = new ArrayList<>();
	        
	        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
	            Statement stmt = conn.createStatement();
	            ResultSet rs = stmt.executeQuery(sql.toString()	);
	            
	            
	            while(rs.next()) {
                BuildingEntity building = new BuildingEntity();
                DistrictEntity district= new DistrictEntity();
                RentTypeEntity rentType= new RentTypeEntity();
                TotalEntity total= new TotalEntity();
                building.setName(rs.getString("name"));
                building.setFloorArea(rs.getInt("floorarea"));
                building.setWard(rs.getString("ward"));
                building.setStreet(rs.getString("street"));
                building.setNumberOfBasement(rs.getInt("numberofbasement"));
                building.setDirection(rs.getString("direction"));
                building.setManagerName(rs.getString("managername"));
                building.setManagerPhoneNumber(rs.getInt("managerphonenumber"));
                district.setDistrictName(rs.getString("district_name"));
                rentType.setRentTypeName(rs.getString("renttype_name"));
                building.setBrokerageFee(rs.getInt("brokeragefee"));
                building.setServiceFee(rs.getInt("servicefee"));
                building.setRentPrice(rs.getInt("rentprice"));
                total.setBuildingEntity(building);
                total.setDistrictEntity(district);
                total.setRentTypeEntity(rentType);
                result.add(total);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Connected database failed...");
	        }
		return result;
	}
}	    
	
		    
    

