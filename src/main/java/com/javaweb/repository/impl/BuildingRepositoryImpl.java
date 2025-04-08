package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;


import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;

import com.javaweb.util.ConnectionJDBCUtil;
import com.javaweb.util.NumberUtil;
import com.javaweb.util.StringUtil;
@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	static final String DB_URL = "jdbc:mysql://localhost:3306/building_rental?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
	static final String USER = "root";
	static final String PASS = "Binh25072005";

	
    public static void join(Map<String,Object> params, List<String> buildingRentType, StringBuilder sql) {
    	String userId=(String)params.get("userId");
    	if(StringUtil.checkString(userId)) {
    		sql.append(" left join assignmentbuilding on b.id=assignmentbuilding.building_id ");
    	}
    	if (buildingRentType!=null && buildingRentType.size()!=0) {
    		sql.append(" left join buildingrenttype on b.id= buildingrenttype.building_id ");
    		sql.append(" left join renttype on renttype.id=buildingrenttype.renttype_id ");
    	}
    	String floorAreaTo= (String)params.get("floorAreaTo");
    	String floorAreaFrom=(String)params.get("floorAreaFrom");
    	if(StringUtil.checkString(floorAreaFrom)==true|| StringUtil.checkString(floorAreaTo)==true) {
    		sql.append(" left join rentarea on b.id=rentarea.building_id ");
    	}
    }
    
    public static void queryNormal(Map<String,Object> params, StringBuilder where) {
    	for(Map.Entry<String, Object> it : params.entrySet()) {
    		if(!it.getKey().equals("userId") && !it.getKey().equals("buildingRentType")&&!it.getKey().startsWith("floorArea") 
    				&&	!it.getKey().startsWith("rentPrice") ) {
    			String value=(String)it.getValue();
    			if (StringUtil.checkString(value)) {
    				if(NumberUtil.isNumber(value)) {
    					where.append(" and b."+it.getKey()+"=" + value);
    				}
    				else {
    					where.append(" and b."+it.getKey()+" like '%" + value+"%' ");
    				}
    			}
    		}
    	}
    }

    public static void querySpecial(Map<String, Object> params, List<String> buildingRentType, StringBuilder where) {
        
        String userId = (String) params.get("userId");
        if (StringUtil.checkString(userId)) {
            where.append(" AND assignmentbuilding.user_id = " + userId);
        }

        // Xử lý khoảng diện tích thuê (areaFrom - areaTo)
        String rentAreaFrom = (String) params.get("floorAreaFrom");
        String rentAreaTo = (String) params.get("floorAreaTo");
        if (StringUtil.checkString(rentAreaFrom)) {
            where.append(" AND b.floorarea >= " + rentAreaFrom);
        }
        if (StringUtil.checkString(rentAreaTo)) {
            where.append(" AND b.floorarea <= " + rentAreaTo);
        }

        // Xử lý khoảng giá thuê (rentPriceFrom - rentPriceTo)
        String rentPriceFrom = (String) params.get("rentPriceFrom");
        String rentPriceTo = (String) params.get("rentPriceTo");
        if (StringUtil.checkString(rentPriceFrom)) {
            where.append(" AND b.rentprice >= " + rentPriceFrom);
        }
        if (StringUtil.checkString(rentPriceTo)) {
            where.append(" AND b.rentprice <= " + rentPriceTo);
        }

        // Xử lý typeCode (dạng List<String>)
        if (buildingRentType != null && !buildingRentType.isEmpty()) {
        	where.append(" And(");
            String x= buildingRentType.stream().map(it -> "renttype.name like" +" '%"+it+"%' ").collect(Collectors.joining(" OR "));
            where.append(x);
        	where.append(" ) ");
        }
    }

	@Override
	public List<BuildingEntity> findAll(Map<String,Object> params, List<String> buildingRentType) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT b.district_id, b.id,b.name, b.ward, b.street, b.direction,  ");
		sql.append("b.numberofbasement, b.managername, b.managerphonenumber, b.floorarea, b.rentprice, ");
		sql.append("b.servicefee, b.brokeragefee ");
		sql.append("FROM building b ");
		join(params,buildingRentType,sql);
		StringBuilder where = new StringBuilder(" where 1=1 ");
		queryNormal(params,where);
		querySpecial(params,buildingRentType,where);
		where.append(" group by b.id ");
		sql.append(where);
	    List<BuildingEntity> result = new ArrayList<>();
	        
//	        try(Connection conn= ConnectionJDBCUtil.getConnection();
//	            Statement stmt = conn.createStatement();
//	            ResultSet rs = stmt.executeQuery(sql.toString());){
	    try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql.toString()	);
	            
	            
	            while(rs.next()) {
                BuildingEntity buildingEntity = new BuildingEntity();
                buildingEntity.setId(rs.getInt("id"));
                buildingEntity.setName(rs.getString("name"));
                buildingEntity.setWard(rs.getString("ward"));
                buildingEntity.setNumberOfBasement(rs.getInt("numberofbasement"));
                buildingEntity.setDistrictId(rs.getInt("district_id"));
                buildingEntity.setStreet(rs.getString("street"));
                buildingEntity.setFloorArea(rs.getInt("floorarea"));
                buildingEntity.setRentPrice(rs.getInt("rentprice"));
                buildingEntity.setServiceFee(rs.getInt("servicefee"));
                buildingEntity.setBrokerageFee(rs.getInt("brokeragefee"));
                buildingEntity.setManagerName(rs.getString("managername"));
                buildingEntity.setManagerPhoneNumber(rs.getInt("managerphonenumber"));
                result.add(buildingEntity);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.out.println("Connected database failed...");
	        }
		return result;
	}
}	    
	
		    
    

