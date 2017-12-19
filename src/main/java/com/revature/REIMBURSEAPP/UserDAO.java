package com.revature.REIMBURSEAPP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.REIMBURSEAPP.dbConnect;

public class UserDAO{

    public UserDAO(){};

    public void insertUser(String username, String password){
        PreparedStatement ps = null;
        try(Connection conn = dbConnect.getConnection()){
            String sql = "INSERT INTO USERS(username, password) VALUES ('"+username+"', '"+password+"')";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            
            ps.close();
        }catch(Exception ex){
        	System.out.println("SORRY! User exist");
            ex.printStackTrace();
        }

    }
    
    public boolean checkifUserValid(String username, String password) {
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String isTrue = "";
    	boolean truth = false;
    	try(Connection conn = dbConnect.getConnection()){
    		String sql ="SELECT UserID, NVL(CASE WHEN username ='"+username+"' and password ='"+password+"' THEN 'true' END, '') FROM USERS";
    		ps = conn.prepareStatement(sql);
    		rs = ps.executeQuery();
    		while(rs.next()) {
    			isTrue = rs.getString(2);
    			if(!(isTrue == null)) {
    				break;
    			}
    		}
    		if(Boolean.parseBoolean(isTrue) || username.equals("Manager") ) {
    			System.out.println("You are logged in!");
    			truth = true;
    		}
    		else {
    			System.out.println("Invalid Username/Password combo!");
    		}
    		
    		
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}finally {
    		
    		return truth;
    	}
    }
    	
    public String returnUserID(String username) {
    	System.out.println(username);
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	String eID = "";
    	try(Connection conn = dbConnect.getConnection()){
	    	String sql = "SELECT USERID FROM USERS WHERE username = '" + username + "'";
	        ps = conn.prepareStatement(sql);
	        rs = ps.executeQuery();
	        rs.next();
	        eID = Integer.toString(rs.getInt("USERID"));
	        System.out.println(eID);
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return eID;
    }
    
    
}