package com.revature.REIMBURSEAPP;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    
    public void testUserDAO1checkifUserValid()
    {
    	UserDAO ud = new UserDAO();
    	//ud.insertUser("awd", "awdad") alrdy inserted - insert works;
    	assertEquals(true, ud.checkifUserValid("awd", "awdad"));
        
    }
    
    public void testUserDAO2returnUserID()
    {
    	UserDAO ud = new UserDAO();
    	assertEquals(ud.returnUserID("Lyinky"), "1");
    }
    
    public void testReimbursementDAOIfManager()
    {
    	//testing if username is a manager
    	ReimbursementDAO rd = new ReimbursementDAO();
    	assertTrue(rd.seeIfManager("Manager"));
    	assertFalse(rd.seeIfManager("adwaawdwadawdawd"));
    }
    
    
    
    
}
