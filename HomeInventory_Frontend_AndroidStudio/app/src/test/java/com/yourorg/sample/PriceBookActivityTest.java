package com.yourorg.sample;

import org.junit.Test;

import static org.junit.Assert.*;

public class PriceBookActivityTest {

    PriceBookActivity priceBookInst = new PriceBookActivity();


    @Test
    public void onCreate() {
    }

    @Test
    public void onBtnClick() {
    }

    // Tests false for an itemName, itemQuantity, itemPrice, locationPurch, and category
    // and true for an empty field
    // Test Successful, McKenzie Hawkins 4/15/2020
    @Test
    public void noPriceBookItemErrorTest() {

        // Asserts a false value is returned upon full fields
        assertFalse(priceBookInst.noPriceBookItemError("cereal", "3", "9.99", "JohnsPass", "food"));

        // Asserts a true value is returned upon a missing itemName field
        assertTrue(priceBookInst.noPriceBookItemError("", "3", "9.99", "JohnsPass", "food"));

        // Asserts a true value is returned upon a missing itemQuantity field
        assertTrue(priceBookInst.noPriceBookItemError("cereal", "", "9.99", "JohnsPass", "food"));

        // Asserts a true value is returned upon a missing itemPrice field
        assertTrue(priceBookInst.noPriceBookItemError("cereal", "3", "", "JohnsPass", "food"));

        // Asserts a true value is returned upon a missing locationPurch field
        assertTrue(priceBookInst.noPriceBookItemError("cereal", "3", "9.99", "", "food"));

        // Asserts a true value is returned upon a missing category field
        assertTrue(priceBookInst.noPriceBookItemError("cereal", "3", "9.99", "JohnsPass", ""));

        // Asserts a true value is returned upon a all fields
        assertTrue(priceBookInst.noPriceBookItemError("", "", "", "", ""));

    }
}