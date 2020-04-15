package com.yourorg.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewListsActivityTest {

    ViewListsActivity viewListsInst = new ViewListsActivity();

    @Test
    public void onCreate() {
    }

    @Test
    public void onBtnClick() {
    }

    @Test
    public void addUser() {
    }

    // Tests false for a list name, true for an empty field
    // Test Successful, McKenzie Hawkins 4/15/2020
    @Test
    public void noListErrorTest() {
        // Asserts a false value is returned upon a string
        assertFalse(viewListsInst.noListError("test"));

        // Asserts a true value is returned upon an empty field
        assertTrue(viewListsInst.noListError(""));
    }
}