/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author 2136262
 */
public class StaffTest {
    
    public StaffTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of computePayRoll method, of class Staff.
     */
    @Test
    public void testComputePayRoll() {
        System.out.println("Test: Calculate payroll for a staff member.");
        Staff instance = new Staff
        ("Yet Another Evan", 17, 2004, "Male", 1024, "mogus", 25);
        double expResult = 1200;
        double result = instance.computePayRoll();
        assertEquals(expResult, result, 0.0001);
    }
    
    /**
     * Test of computePayRoll method, of class Staff.
     */
    @Test
    public void testComputePayRollOvertime() {
        System.out.println("Test: Calculate payroll for a staff member "
                + "working overtime.");
        Staff instance = new Staff
        ("Yet Another Evan", 17, 2004, "Male", 1024, "mogus", 45);
        double expResult = 1920;
        double result = instance.computePayRoll();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of determineBonus method, of class Staff.
     */
    @Test
    public void testDetermineBonus() {
        System.out.println("Test: Calculate bonus for a staff member.");
        Staff instance = new Staff
        ("Yet Another Evan", 17, 2015, "Male", 1024, "mogus", 32);
        double expResult = 103;
        double result = instance.determineBonus();
        assertEquals(expResult, result, 0.01);
    }
    
}
