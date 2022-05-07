import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Department {

    private String departmentName;
    private int departmentId;
    private int deanId;
    private Teacher dean;
    private List<Teacher> teachers;
    private List<Staff> staff;

    public Department(String departmentName, int departmentId, int deanId, List<Teacher> teachers, List<Staff> staff) {
        this.departmentName = departmentName;
        this.departmentId = departmentId;
        this.deanId = deanId;
        this.teachers = teachers;
        this.staff = staff;
    }

    public void addTeacher(Teacher teacher) {
        if (teachers == null) {
            teachers = new ArrayList<>();
        }
        teachers.add(teacher);
    }

    public void addStaff(Staff staffMember) {
        if (staff == null) {
            staff = new ArrayList<>();
        }
        staff.add(staffMember);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public void loadDept(String name) {
        File deptFile = new File(name);

        try (Scanner read = new Scanner(deptFile)){
            while (read.hasNextLine()) {
                String[] attr = read.nextLine().split("\\|");
                if (attr[0].equals("Staff") && !staffMemberExists(attr[2])) {
                    addStaff(new Staff(attr[1], Integer.parseInt(attr[2]), Integer.parseInt(attr[3]), attr[4],
                            Integer.parseInt(attr[5]), attr[6], Double.parseDouble(attr[7])));
                } else if (attr[0].equals("Teacher")) {
                    addTeacher(new Teacher(attr[1], Integer.parseInt(attr[2]), Integer.parseInt(attr[3]), attr[4],
                            Integer.parseInt(attr[5]), attr[6], attr[7]));
                }
            }

            verifyDean();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (DepartmentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Department loaded from " + name);
    }

    private boolean staffMemberExists(String id) {
        if (staff == null)
            return false;
        int parsedId = Integer.parseInt(id);
        for (Staff s : staff) {
            if (s.getId() == parsedId) {
                return true;
            }
        }
        return false;
    }

    private boolean teacherExists(String id) {
        if (teachers == null)
            return false;
        int parsedId = Integer.parseInt(id);
        for (Teacher t : teachers) {
            if (t.getId() == parsedId) {
                return true;
            }
        }
        return false;
    }

    public void saveDept(String name) {
        File deptFile = new File(name);
        StringBuilder sb = new StringBuilder();
        try (FileWriter fw = new FileWriter(deptFile)) {

            for (Teacher t : teachers) {
                sb.append(t.toString()).append("\n");
            }
            for (Person p : staff) {
                sb.append(p.toString()).append("\n");
            }
            fw.write(sb.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Department saved to " + name);
    }

    public boolean verifyDean() {
        boolean isValid = false;
        for (Teacher t : teachers) {
            isValid = (t.getId() == deanId);
        }
        if (isValid) {
            return true;
        } else throw new DepartmentException("No dean found!");
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Department Information:");
        str.append("Name: ").append(departmentName).append("\n");
        str.append("Department ID: ").append(departmentId).append("\n");
        str.append("Dean ID: ").append(deanId).append("\n");
        str.append("Employee List: ");
        for (Teacher t : teachers) {
            str.append(t).append("\n");
        }
        for (Staff s : staff) {
            str.append(s).append("\n");
        }
        return str.toString();
    }
}
