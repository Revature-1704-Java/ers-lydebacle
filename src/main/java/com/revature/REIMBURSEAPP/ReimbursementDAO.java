package com.revature.REIMBURSEAPP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.revature.REIMBURSEAPP.dbConnect;

public class ReimbursementDAO{

    public ReimbursementDAO(){};

    public void insertReimbursement(String username, String value, String reason){
        PreparedStatement ps = null;
        String eID;
        ResultSet rs = null;
        try(Connection conn = dbConnect.getConnection()){
        	String sql = "SELECT userID FROM USERS WHERE username = '" + username + "'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            eID = Integer.toString(rs.getInt(1));
            System.out.println(eID);
            sql = "INSERT INTO Reimbursements(EmployeeID,value, reason) VALUES ('"+eID+"','"+value+"','"+reason+"')";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
            ps.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
     public void viewReimbursement(String username) {
    	 PreparedStatement ps = null;
         String eID="";
         ResultSet rs = null;
         String sql;
         HashMap<String,ArrayList<String>> allReimbursements = new HashMap<String, ArrayList<String>>();
    	 try(Connection conn = dbConnect.getConnection()){
    		 if(!username.equals("Manager")) {
	    		 sql = "SELECT userID FROM USERS WHERE username = '" + username + "'";
	             ps = conn.prepareStatement(sql);
	             rs = ps.executeQuery();
	             rs.next();
	             eID = Integer.toString(rs.getInt(1));
    		 }
    		 System.out.println(username);
    		 System.out.println(seeIfManager(username));
             if(seeIfManager(username)) {
            	 sql = "SELECT * FROM REIMBURSEMENTS";
	    	 }
             else {
            	 sql = "SELECT * FROM REIMBURSEMENTS WHERE employeeID = '"+ eID + "'";
             }
             ps = conn.prepareStatement(sql);
    		 rs = ps.executeQuery();
    		 while(rs.next()) {
    			 allReimbursements.put(rs.getString(1), new ArrayList<String>(Arrays.asList(rs.getString(3), rs.getString(4))));
    		 }
    		 for(Map.Entry<String, ArrayList<String>> m: allReimbursements.entrySet()) {
    			 System.out.println(m.getKey() + " value: " + m.getValue().get(0) + "reason: " + m.getValue().get(1));
    		 }
    	 }catch(Exception ex) {
    		 ex.printStackTrace();
    	 }
     }
     
     public boolean seeIfManager(String username) {
    	 PreparedStatement ps = null;
         String eID;
         ResultSet rs = null;
         
         try(Connection conn = dbConnect.getConnection()){
        	 String sql = "SELECT username FROM MANAGERS WHERE username= '"+ username+"'";
        	 ps = conn.prepareStatement(sql);
        	 rs = ps.executeQuery();
        	 if(!rs.next()) {
        		 return false;
        	 };
         }catch(Exception ex) {
        	 ex.printStackTrace();
        	 return false;
         }
         return true;
         
     }

    
}