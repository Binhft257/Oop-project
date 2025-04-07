package com.javaweb.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingSearchRequest;
import com.javaweb.model.ErrorResonseDTO;
import com.javaweb.service.BuildingService;

import customexception.FieldRequiredException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class BuildingAPI {
	@Autowired
	private BuildingService buildingService;
	@GetMapping(value="/api/building/")
	public List<BuildingDTO> getBuilding(BuildingSearchRequest request){
		List<BuildingDTO> result = buildingService.findAll(request);
		return result;
	}
	
	
	
//	@RequestMapping(value = "/api/building/", method = RequestMethod.GET)
//	public void building(@RequestParam(value = "name") String name, @RequestParam(value = "age") String age) {
//		System.out.print(name + " " + age);
//	}
//
//	static final String DB_URL = "jdbc:mysql://localhost:3306/building_rental";
//    static final String USER = "root";
//    static final String PASS = "Binh25072005";
//
//    @PostMapping(value="/api/building/")
//    public List<BuildingDTO> getBuilding(@RequestParam(name="name") String name) {
//        String sql = "SELECT * FROM building where name like '%"+ name+ "%'" ;
//        List<BuildingDTO> result = new ArrayList<>();
//        
//        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(sql);
//            
//            while(rs.next()) {
//                BuildingDTO building = new BuildingDTO();
//                building.setName(rs.getString("name"));
//                building.setStreet(rs.getString("street"));
//                building.setWard(rs.getString("ward"));
//                building.setNumberOfBasement(rs.getInt("numberofbasement"));
//                result.add(building);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.out.println("Connected database failed...");
//        }
//        
//        return result;
//    }

//	public void valiDate(BuildingDTO building) {
//		if (building.getName() == null || building.getName().equals("") || building.getNumberOfBasement() == null) {
//			throw new FieldRequiredException("name or numberofbasement is null");
//		}
//	}

	@DeleteMapping("/api/building/{id}/{name}")
	public void deleteBuilding(@PathVariable Integer id, @PathVariable String name) {
		System.out.println("da xoa toa nha" + id + name);
	}
}
