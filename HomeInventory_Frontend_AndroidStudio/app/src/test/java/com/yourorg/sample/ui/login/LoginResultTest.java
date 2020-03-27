package com.yourorg.sample.ui.login;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoginResultTest {

    // Successful Unit Test
    // Returns LoggedInUserView if log in was a success
    @Test
    public void getSuccessTest() {
        LoggedInUserView userViewTester = new LoggedInUserView("Tim");
        LoginResult resultTester = new LoginResult(userViewTester);
        assertEquals(userViewTester, resultTester.getSuccess());
        //userViewTester = null;
        //resultTester = null;
    }

    // Successful Unit Test
    // Returns integer error
    @Test
    public void getErrorTest() {
        Integer errorTest = 1;
        LoginResult tester = new LoginResult(errorTest);
        assertEquals("1", tester.getError().toString());
        //errorTest = null;
        //tester = null;
    }
}