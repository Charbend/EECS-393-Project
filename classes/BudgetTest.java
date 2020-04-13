package classes;

import static org.junit.Assert.*;
import org.junit.Test;

/** 
* Testing class for BudgetTest.java
* */ 

public class BudgetTest {
	//budget object for method testing	
  String testString = "test";
  Budget budget = new Budget(testString);
  
  //test setAmount and getAmount methods
  @Test
  public void test_Amount() {
    //positive and negative Double values
    Double case1 = 1.1;
    Double case2  = -1.1;
    
    budget.setAmount(case1); 
    Double output = budget.getAmount();  
    assertEquals(case1,output);
    
    //temp test until clarification on negative number handling
    budget.setAmount(case2);
    Double output = budget.getAmount();
    assertEquals(case2,output);
  }
  
  //test setSpent and getSpent methods
  @Test
  public void test_Spent() {
    //positive and negative Double values
    Double case1 = 1.1;
    Double case2  = -1.1;
    
    budget.increaseSpent(case1);    
    Double output = budget.getSpent();    
    assertEquals(case1,output);
    
    //temp test until clarification on negative number handling
    budget.increaseSpent(case2);    
    Double output = budget.getSpent();    
    assertEquals(case2,output);
  }
  
  //test getCategory method
  @Test
  public void test_Category() {
    String output = budget.getCategory();
    assertEquals(testString, output);
  }

}
