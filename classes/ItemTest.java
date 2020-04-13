package classes;

import static org.junit.Assert.*;
import org.junit.Test;

/** 
* Testing class for Item.java
* */ 

public class ItemTest {
  //test object parameters
  String itemName = "bread";
  String category = "food";
  String location = "kitchen";
  int qty1 = 2;
  int qty2 = -1;
  int qty3 = 0;
  double price1 = 2.0;
  double price2 = -1.0;
  double price3 = 0.0;
  Retailer retailer; // = new Retailer(   );
  
  //make object with each constructor
  Item item1 = new Item(itemName, qty1, price1, category, retailer);
  Item item2 = new Item(itemName, qty2, price1, category, location, retailer);
  Item item3 = new Item(itemName, qty3, price2, category, location);
  Item item4 = new Item(itemName, qty1, price3, category);

  //test getName method
  @Test
  public void test_Name() {
    String output1 = item1.getName();
    String output2 = item2.getName();
    String output3 = item3.getName();
    String output4 = item4.getName();
    
    assertEquals(itemName, output1);
    assertEquals(itemName, output1);
    assertEquals(itemName, output1);
    assertEquals(itemName, output1);    
  }
  
  //test getPrice method
  @Test
  public void test_Price() {
    double output1 = item1.getPrice();
    double output2 = item2.getPrice();
    double output3 = item3.getPrice();
    double output4 = item4.getPrice();
    
    assertEquals(price1, output1);
    assertEquals(price1, output2);
    assertEquals(price2, output3);
    assertEquals(price3, output4);    
  }
  
  //test getCategory method
  @Test
  public void test_Category() {
    String output1 = item1.getCategory();
    String output2 = item2.getCategory();
    String output3 = item3.getCategory();
    String output4 = item4.getCategory();
    
    assertEquals(category, output1);
    assertEquals(category, output2);
    assertEquals(category, output3);
    assertEquals(category, output4);    
  }
  
  //test getQuantity method
  @Test
  public void test_Quantity() {
    double output1 = item1.getQuantity();
    double output2 = item2.getQuantity();
    double output3 = item3.getQuantity();
    double output4 = item4.getQuantity();
    
    assertEquals(qty1, output1);
    assertEquals(qty2, output2);
    assertEquals(qty3, output3);
    assertEquals(qty1, output4);    
  }
  
  //test getLocation method
  @Test
  public void test_Location() {
    String output1 = item1.getLocation();
    String output2 = item2.getLocation();
    String output3 = item3.getLocation();
    String output4 = item4.getLocation();
    
    assertEquals(location, output1);
    assertEquals(location, output2);
    assertEquals(location, output3);
    assertEquals(location, output4);    
  }
  
  //test getRetailer method
  @Test
  public void test_Location() {
    Retailer output1 = item1.getRetailer();
    Retailer output2 = item2.getRetailer();
    Retailer output3 = item3.getRetailer();
    Retailer output4 = item4.getRetailer();
    
    assertEquals(retailer, output1);
    assertEquals(retailer, output2);
    assertEquals(retailer, output3);
    assertEquals(retailer, output4);    
  }


}
