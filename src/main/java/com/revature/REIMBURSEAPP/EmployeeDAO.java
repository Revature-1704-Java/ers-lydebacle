package com.revature.REIMBURSEAPP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.REIMBURSEAPP.dbConnect;

public class EmployeeDAO{

    public EmployeeDAO(){};

    public void insertEmployee(String userID, String firstName, String lastName, String ssn){
        PreparedStatement ps = null;
        try(Connection conn = dbConnect.getConnection()){
        	
            String sql = "INSERT INTO Employees(userid, firstname, lastname, ssn, birthdate) VALUES ('"+userID+"','"+firstName+"','"+lastName+"','"+ssn+"', LOCALTIMESTAMP)";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }

    }


}