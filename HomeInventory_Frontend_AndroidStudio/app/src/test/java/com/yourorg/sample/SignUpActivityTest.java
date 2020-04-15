package com.yourorg.sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class SignUpActivityTest {

    SignUpActivity signUpInst = new SignUpActivity();

    @Test
    public void testOnCreate() {
    }

    @Test
    public void testSignUpSuccess() {
    }

    // Tests false for a name, email, password and confirm password
    // and true for an empty field
    // Test Successful, McKenzie Hawkins 4/15/2020
    @Test
    public void missingSignUpErrorTest() {

        // Asserts a false value is returned upon full fields
        assertFalse(signUpInst.missingSignUpError("John", "john@gmail.com", "JohnsPass", "JohnsPass"));

        // Asserts a true value for an empty userName
        assertTrue(signUpInst.missingSignUpError("", "john@gmail.com", "JohnsPass", "JohnsPass"));

        // Asserts a true value for an empty userEmail
        assertTrue(signUpInst.missingSignUpError("John", "", "JohnsPass", "JohnsPass"));

        // Asserts a true value for an empty userPass
        assertTrue(signUpInst.missingSignUpError("John", "john@gmail.com", "", "JohnsPass"));

        // Asserts a true value for an empty userConfPass
        assertTrue(signUpInst.missingSignUpError("John", "john@gmail.com", "JohnsPass", ""));

        // Asserts a true value for an all empty fields
        assertTrue(signUpInst.missingSignUpError("", "", "", ""));
    }

    @Test
    public void invalidSignUpErrorTest() {
        assertFalse(signUpInst.invalidSignUpError("john@gmail.com"));
        assertTrue(signUpInst.invalidSignUpError("john"));
    }
}