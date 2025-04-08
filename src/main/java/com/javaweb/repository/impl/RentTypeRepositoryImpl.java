package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.RentTypeRepository;
import com.javaweb.repository.entity.RentTypeEntity;
import com.javaweb.util.ConnectionJDBCUtil;
@Repository
public class RentTypeRepositoryImpl implements RentTypeRepository {

    @Override
    public RentTypeEntity findRentTypeById(Integer id) {
        RentTypeEntity rent = null;
        String sql = "SELECT name FROM renttype WHERE id = " + id;
        
        try(Connection conn = ConnectionJDBCUtil.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            
            if (rs.next()){
                // Tạo đối tượng mới khi có dữ liệu trả về
                rent = new RentTypeEntity();
                rent.setRentTypeName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connected database failed...");
        }
        return rent;
    }
}



