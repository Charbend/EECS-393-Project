package com.yourorg.sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class ViewItemsActivityTest {

    ViewItemsActivity viewItemsInst = new ViewItemsActivity();

    @Test
    public void onCreate() {
    }

    @Test
    public void onBtnClick() {
    }

    @Test
    public void addUser() {
    }

    // Tests false for a item name, true for an empty field
    // Test Successful, McKenzie Hawkins 4/15/2020
    @Test
    public void noItemErrorTest() {
        // Asserts a false value is returned upon a string
        assertFalse(viewItemsInst.noItemError("test"));

        // Asserts a true value is returned upon an empty field
        assertTrue(viewItemsInst.noItemError(""));
    }
}