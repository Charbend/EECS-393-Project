package com.yourorg.sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class ViewRooms1ActivityTest {

    ViewRooms1Activity viewRoomInst = new ViewRooms1Activity();

    @Test
    public void onCreate() {
    }

    @Test
    public void onBtnClick() {

    }

    // Tests false for a room name, true for an empty field
    // Test Successful, McKenzie Hawkins 4/15/2020
    @Test
    public void noRoomErrorTest() {

        // Asserts a false value is returned upon a string
        assertFalse(viewRoomInst.noRoomError("test"));

        // Asserts a true value is returned upon an empty field
        assertTrue(viewRoomInst.noRoomError(""));
    }
}