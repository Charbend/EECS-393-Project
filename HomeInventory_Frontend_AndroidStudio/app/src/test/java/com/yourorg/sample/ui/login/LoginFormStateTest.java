package com.yourorg.sample.ui.login;

import androidx.annotation.Nullable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoginFormStateTest {


    // Successful Unit Test
    // Checks if username is only an integer, throwing an error
    @Test
    public void getUsernameErrorTest() {
        LoginFormState tester = new LoginFormState(1, 2);
        assertEquals("1", tester.getUsernameError().toString());
        //tester = null;
    }

    // Successful Unit Test
    // Checks if password is only an integer, throwing an error
    @Test
    public void getPasswordErrorTest() {
        LoginFormState tester = new LoginFormState(1, 2);
        assertEquals("2", tester.getPasswordError().toString());

        //tester = null;
    }

    // Successful Unit Test
    // Checks if login criteria is true and false
    @Test
    public void isDataValidTest() {
        LoginFormState tester = new LoginFormState(true);
        assertTrue(tester.isDataValid());

        tester = new LoginFormState(false);
        assertFalse(tester.isDataValid());
        //tester = null;
    }

}