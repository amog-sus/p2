import java.util.ArrayList;
import java.util.List;

public class Driver {

    /**
     * Test functionality.
     */
    public static void main(String[] args) {
        FullTimeTeacher t1 = new FullTimeTeacher("Evan", 1, 2021, "Male", 1001, "Computer Science", "PhD");
        PartTimeTeacher t2 = new PartTimeTeacher("Another Evan", 14, 2017, "Male", 1043, "Math", "Bachelor", 32);
        Staff s1 = new Staff("Also Evan", 2, 2021, "Male", 1003, "H", 21);
        Staff s2 = new Staff("Yet Another Evan", 17, 2004, "Male", 1024, "mogus", 45);

        System.out.println(t1.computePayRoll());
        System.out.println(t2.computePayRoll());
        System.out.println(s2.computePayRoll()); // should cap at 40 hours
        System.out.println(t1.determineBonus()); // test abstract method

        List<Teacher> csTeachers = new ArrayList<>();
        csTeachers.add(t1);
        List<Teacher> mathTeachers = new ArrayList<>();
        mathTeachers.add(t2);

        List<Staff> csStaff = new ArrayList<>();
        csStaff.add(s1);
        List<Staff> mathStaff = new ArrayList<>();
        mathStaff.add(s2);

        Department cs = new Department("Computer Science", 101, 1001, csTeachers, csStaff, "csDept");
        Department math = new Department("Math", 102, 1043, mathTeachers, mathStaff, "mathDept");
        
        System.out.println(cs);
        System.out.println(math);
    }

}
