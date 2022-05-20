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
public class TeacherTest {
    
    public TeacherTest() {
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
     * Test of computePayRoll method, of class Teacher.
     */
    @Test
    public void testComputePayRoll() {
        System.out.println("Test: Calculate payroll for a "
                + "full-time teacher with bachelor's degree.");
        Teacher instance = new FullTimeTeacher
        ("Evan", 1, 2021, "Male", 1001, "Computer Science", "Bachelor");
        double expResult = 2284.80;
        double result = instance.computePayRoll();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of determineBonus method, of class Teacher.
     */
    @Test
    public void testDetermineBonus() {
        System.out.println("Test: Calculate bonus for a "
                + "full-time teacher with a PhD.");
        Teacher instance = new FullTimeTeacher
        ("Evan", 1, 2021, "Male", 1001, "Computer Science", "PhD");
        double expResult = 1350.0;
        double result = instance.determineBonus();
        assertEquals(expResult, result, 0.0);
    }
    
    /**
     * Test of computePayRoll method, of class Teacher.
     */
    @Test
    public void testComputePayRollPartTime() {
        System.out.println("Test: Calculate payroll for a "
                + "part-time teacher with a master's degree.");
        Teacher instance = new PartTimeTeacher
        ("Evan", 1, 2021, "Male", 1001, "Computer Science", "Master", 32);
        double expResult = 3988.48;
        double result = instance.computePayRoll();
        assertEquals(expResult, result, 0.0001);
    }
        /**
     * Test of determineBonus method, of class Teacher.
     */
    @Test
    public void testDetermineBonusPartTime() {
        System.out.println("Test: Calculate bonus for a "
                + "part-time teacher with a PhD, who is working overtime.");
        Teacher instance = new PartTimeTeacher
        ("Evan", 1, 2021, "Male", 1001, "Computer Science", "PhD", 50);
        double expResult = 750.0;
        double result = instance.determineBonus();
        assertEquals(expResult, result, 0.0);
    }
    
}
