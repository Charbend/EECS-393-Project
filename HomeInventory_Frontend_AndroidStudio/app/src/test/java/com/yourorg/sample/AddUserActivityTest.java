package com.yourorg.sample;

import android.app.Activity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.service.autofill.Validators.not;
import static java.util.regex.Pattern.matches;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AddUserActivityTest {

    private AddUserActivity addUserInst = new AddUserActivity();

    @Test
    public void onCreate() {
    }

    @Test
    public void setAddUser() {
    }

    @Test
    public void sendEmailTest() {

    }

    @Test
    public void testSendEmail() {

    }

    // Tests false for a addedEmail and addedName, true for an empty field
    // Test Successful, McKenzie Hawkins 4/15/2020
    @Test
    public void noSecondaryUserErrorTest() {

        // Asserts a false value is returned upon filled fields
        assertFalse(addUserInst.noSecondaryUserError("john@gmail.com", "john"));

        // Asserts a false value is returned upon empty addedEmail
        assertTrue(addUserInst.noSecondaryUserError("", "john"));

        // Asserts a false value is returned upon empty addedName
        assertTrue(addUserInst.noSecondaryUserError("john@gmail.com", ""));

        // Asserts a false value is returned upon all empty fields
        assertTrue(addUserInst.noSecondaryUserError("", ""));
    }

    @Test
    public void invalidSecondaryUserErrorTest() {


    }

}