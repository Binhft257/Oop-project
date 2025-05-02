package com.javaweb.repository.custom.impl;

import java.lang.reflect.Field;
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

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import com.javaweb.repository.entity.BuildingEntity;

import com.javaweb.util.ConnectionJDBCUtil;
import com.javaweb.util.NumberUtil;
import com.javaweb.util.StringUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom  {
	

	
    public static void join(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {
    	Integer userId= buildingSearchBuilder.getUserId();
    	if(userId!=null) {
    		sql.append(" left join assignmentbuilding on b.id=assignmentbuilding.building_id ");
    	}
    	if ( buildingSearchBuilder.getBuildingRentType()!=null &&  buildingSearchBuilder.getBuildingRentType().size()!=0) {
    		sql.append(" left join buildingrenttype on b.id= buildingrenttype.building_id ");
    		sql.append(" left join renttype on renttype.id=buildingrenttype.renttype_id ");
    	}
    	Integer floorAreaTo=  buildingSearchBuilder.getFloorAreaTo();
    	Integer floorAreaFrom= buildingSearchBuilder.getFloorAreaFrom();
    	if(floorAreaTo!=null|| floorAreaFrom!=null) {
    		sql.append(" left join rentarea on b.id=rentarea.building_id ");
    	}
    }
    
    public static void queryNomal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("userId") && !fieldName.equals("buildingRentType") && !fieldName.startsWith("floorArea")
                        && !fieldName.startsWith("rentPrice")) {
                    Object value = item.get(buildingSearchBuilder);
                    if (value != null) {
                    	if(!item.getName().equals("districtId")) {
                        if (item.getType().getName().equals("java.lang.Long") || item.getType().getName().equals("java.lang.Integer")) {
                            where.append(" AND b." + fieldName + " = " + value);
                        } else if (item.getType().getName().equals("java.lang.String")) {
                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
                        } } else {
                        	where.append(" AND b.district_id = " + value);
                        }
                        }
                        
                    }
     
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        
    	Integer userId= buildingSearchBuilder.getUserId();
        if (userId!=null) {
            where.append(" AND assignmentbuilding.user_id = " + userId);
        }

        // Xử lý khoảng diện tích thuê (areaFrom - areaTo)
        Integer rentAreaFrom =buildingSearchBuilder.getFloorAreaFrom() ;
        Integer rentAreaTo = buildingSearchBuilder.getFloorAreaTo();
        if (rentAreaFrom!=null) {
            where.append(" AND b.floorarea >= " + rentAreaFrom);
        }
        if (rentAreaTo!=null) {
            where.append(" AND b.floorarea <= " + rentAreaTo);
        }

        // Xử lý khoảng giá thuê (rentPriceFrom - rentPriceTo)
        Integer rentPriceFrom =buildingSearchBuilder.getRentPriceFrom() ;
        Integer rentPriceTo = buildingSearchBuilder.getRentPriceTo();
        if (rentPriceFrom!=null) {
            where.append(" AND b.rentprice >= " + rentPriceFrom);
        }
        if (rentPriceTo!=null) {
            where.append(" AND b.rentprice <= " + rentPriceTo);
        }

        // Xử lý typeCode (dạng List<String>)
        if (buildingSearchBuilder.getBuildingRentType() != null && !buildingSearchBuilder.getBuildingRentType().isEmpty()) {
        	where.append(" And(");
            String x= buildingSearchBuilder.getBuildingRentType().stream().map(it -> "renttype.name like" +" '%"+it+"%' ").collect(Collectors.joining(" OR "));
            where.append(x);
        	where.append(" ) ");
        }
    }
    @PersistenceContext
    private EntityManager entityManager;
    @Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT b.district_id, b.id,b.name, b.ward, b.street, b.direction,  ");
		sql.append("b.numberofbasement, b.managername, b.managerphonenumber, b.floorarea, b.rentprice, ");
		sql.append("b.servicefee, b.brokeragefee ");
		sql.append("FROM building b ");
		join(buildingSearchBuilder,sql);
		StringBuilder where = new StringBuilder(" where 1=1 ");
		queryNomal(buildingSearchBuilder,where);
		querySpecial(buildingSearchBuilder,where);
		where.append(" group by b.id ");
		sql.append(where);
		Query query= entityManager.createNativeQuery(sql.toString(),BuildingEntity.class);
		return query.getResultList();
		
}
}
	
		    
    

