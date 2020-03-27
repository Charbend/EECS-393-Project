package com.yourorg.sample.ui.login;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoggedInUserViewTest {

    String displayName;

    @Before
    public void setUp() throws Exception {
        displayName = "John";
    }

    // Successful Unit Test
    // Checks if getDisplayName() returns correct name
    @Test
    public void getDisplayNameTest() {

        LoggedInUserView tester = new LoggedInUserView(displayName);
        assertEquals("John", tester.getDisplayName());
        //tester = null;
    }

    @After
    public void tearDown() throws Exception {
        displayName = null;
    }
}