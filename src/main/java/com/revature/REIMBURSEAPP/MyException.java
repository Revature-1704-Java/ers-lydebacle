package com.revature.REIMBURSEAPP;

public class MyException extends Exception {
	public void invalidUserPassword() {
		System.out.println("Invalid username and password combo");
	}
}
