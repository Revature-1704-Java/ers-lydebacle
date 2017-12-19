package com.revature.REIMBURSEAPP;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.io.*; 

import com.revature.REIMBURSEAPP.UserDAO;
import com.revature.REIMBURSEAPP.ReimbursementDAO;
import com.revature.REIMBURSEAPP.EmployeeDAO;

public class ReimbursementApp {
    private Scanner sc = new Scanner(System.in);
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String ssn;
    private String reason;
    private String value;
    private String input;
    
    private boolean quit = true;
    private boolean validu = true;
    private User user = new User();
    
    private UserDAO ud = new UserDAO();
    private EmployeeDAO ed = new EmployeeDAO();
    private ReimbursementDAO rd = new ReimbursementDAO();
    
    public ReimbursementApp(){};

    public static void main(String[] args){
        ReimbursementApp rA = new ReimbursementApp();
        rA.Intro();
        
    }

    public void Intro(){
       
        System.out.println("Welcome to Revature Reimbursement LLC");
        System.out.println("What would you like to do? Please Register if you are a new user.");
        System.out.println("a: LOGIN b: REGISTER");
        username = sc.nextLine();
        switch(username.charAt(0)){
            case 'a':
                login();
                break;
            case 'b':
                register();
                break;
        }
        
    }

    public void login(){
    	while(validu) {
	        System.out.println("Please enter your username to login:");
	        username = sc.nextLine();
	        System.out.println("Please enter your password to login:");
	        password = sc.nextLine();
	        
	        if(ud.checkifUserValid(username, password)) {
	        	break;
	        }
    	}
        user.setUsername(username);
        while (quit) {
        	LoopForNow();
        }
    }
    public void setReimbursement() {
        
        System.out.println("How much is your Reimbursement? Enter a numeric value");
        value = sc.nextLine();
        System.out.println("What is this Reimbursement for?");
        reason = sc.nextLine();
        rd.insertReimbursement(user.getUsername(), value, reason);
    }

    public void register(){
        System.out.println("Please enter your username to register:");
        username = sc.nextLine();
        
        
        System.out.println("Please enter your password to register:");
        password = sc.nextLine();
        
        ud.insertUser(username, password);
        user.setUsername(username);
        
        System.out.println("Please enter your first name:");
        firstName = sc.nextLine();
        System.out.println("Please enter your last name:");
        lastName = sc.nextLine();
        System.out.println("Please enter your ssn:");
        ssn = sc.nextLine();
        
        ed.insertEmployee(ud.returnUserID(username), firstName, lastName, ssn);
        login();
    }
    
    public void LoopForNow() {
    	System.out.println("What would you like to do next?");
        System.out.println("a: issue Reimbursement b: view your Reimbursements c:quit");
        input = sc.nextLine();
        switch(input.charAt(0)) {
        	case 'a':
        		setReimbursement(); break;
        	case 'b':
        		rd.viewReimbursement(user.getUsername()); break;
        	case 'c':
        		System.out.println("Thank you for using Revature Reimbursement LLC!");
        		quit = false;
        }	
    }

}