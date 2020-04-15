package com.yourorg.sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class LogInActivity2Test {

    LogInActivity2 logInInst = new LogInActivity2();

    @Test
    public void onCreate() {
    }

    @Test
    public void logInSuccess() {
    }

    @Test
    public void missingLogInErrorTest() {
        // Asserts a false value is returned upon full fields
        assertFalse(logInInst.missingLogInError("john@gmail.com", "johnpassword"));

        // Asserts a true value for an empty enteredEmail
        assertTrue(logInInst.missingLogInError("", "johnpassword"));

        // Asserts a true value for an empty enteredPassword
        assertTrue(logInInst.missingLogInError("john@gmail.com", ""));
    }

    @Test
    public void invalidLogInError() {
    }
}