package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.entity.BuildingRentTypeEntity;
import com.javaweb.util.ConnectionJDBCUtil;
@Repository
public class BuildingRentTypeRepositotyImpl implements BuildingRentTypeRepository {

    @Override
    public List<BuildingRentTypeEntity> findId(Integer buildingId) {
        List<BuildingRentTypeEntity> result = new ArrayList<>();
        String sql = "SELECT renttype_id FROM buildingrenttype WHERE building_id = " + buildingId;
        
        try(Connection conn = ConnectionJDBCUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            
            while(rs.next()){
                // Tạo mới đối tượng cho mỗi dòng dữ liệu
                BuildingRentTypeEntity buildingRent = new BuildingRentTypeEntity();
                buildingRent.setBuildingRentTypeRentTypeId(rs.getInt("renttype_id"));
                result.add(buildingRent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connected database failed...");
        }
        return result;
    }
}

	


