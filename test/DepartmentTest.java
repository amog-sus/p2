/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
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
public class DepartmentTest {
    
    public DepartmentTest() {
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
     * Test of addTeacher method, of class Department.
     */
    @Test
    public void testAddTeacher() {
        System.out.println("Test: Add teacher to a department.");
        Teacher teacher = new FullTimeTeacher("Evan", 1, 2021, "Male", 20212, "Computer Science", "PhD");
        Department instance = new Department("Computer Science", 0, 0, 
                new ArrayList<Teacher>(), new ArrayList<Staff>(), "csDept");
        instance.addTeacher(teacher);
    }

    /**
     * Test of addStaff method, of class Department.
     */
    @Test
    public void testAddStaff() {
        System.out.println("Test: Add staff member to a department.");
        Staff staffMember = new Staff("Also Evan", 2, 2021, "Male", 10093, "H", 21);
        Department instance = new Department("Computer Science", 0, 0,
                new ArrayList<Teacher>(), new ArrayList<Staff>(), "csDept");
        instance.addStaff(staffMember);
    }

    /**
     * Test of removeTeacher method, of class Department.
     */
    @Test
    public void testRemoveTeacher() {
        System.out.println("Test: Remove teacher from a department.");
        Teacher teacher = new FullTimeTeacher("Evan", 1, 2021, "Male", 20212, "Computer Science", "PhD");
        Department instance = new Department("Computer Science", 0, 0, 
                new ArrayList<Teacher>(), new ArrayList<Staff>(), "csDept");
        instance.removeTeacher(teacher);
    }

    /**
     * Test of removeStaff method, of class Department.
     */
    @Test
    public void testRemoveStaff() {
        System.out.println("Test: Remove staff member from a department.");
        Staff staffMember = new Staff("Also Evan", 2, 2021, "Male", 10093, "H", 21);
        Department instance = new Department("Computer Science", 0, 0,
                new ArrayList<Teacher>(), new ArrayList<Staff>(), "csDept");
        instance.removeStaff(staffMember);
    }

    /**
     * Test of loadDept method, of class Department.
     */
    @Test
    public void testLoadDept() {
        System.out.println("Test: Load a department.");
        String name = "mathDept";
        Department instance = new Department("Math", 0, 0,
                new ArrayList<Teacher>(), new ArrayList<Staff>(), name);
        instance.loadDept(name);
    }

    /**
     * Test of saveDept method, of class Department.
     */
    @Test
    public void testSaveDept() {
        System.out.println("Test: Save a department.");
        String name = "csDept";
        Department instance = new Department("Test", 0, 0,
                new ArrayList<Teacher>(), new ArrayList<Staff>(), name);
        instance.saveDept("testDept");
    }

    /**
     * Test of verifyDean method, of class Department.
     */
    @Test
    public void testVerifyDean() {
        System.out.println("Test: Verify whether there is a valid "
                + "dean in a department");
        Department instance = new Department("Computer Science", 10001, 1001,
                new ArrayList<Teacher>(), new ArrayList<Staff>(), "csDept");
        boolean expResult = true;
        boolean result = instance.verifyDean();
        assertEquals(expResult, result);
    }
    
}
