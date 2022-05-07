import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Teacher t = new Teacher("Evan", 1, 2021, "Male", 1001, "Computer Science", "DEC", true);
        Staff s = new Staff("Also Evan", 2, 2021, "Male", 1003, "H", 21);

        List<Teacher> teacherList = new ArrayList<Teacher>();
        teacherList.add(t);

        List<Staff> staffList = new ArrayList<Staff>();
        staffList.add(s);

        Department d = new Department("Computer Science", 101, 1001, teacherList, staffList);

        System.out.println(d);

        d.saveDept("testFile.txt");

        Department d2 = new Department(null, 0, 1, null, null);
        d2.loadDept("testFile.txt");

        System.out.println(d2);
    }

}
