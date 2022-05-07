import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Department with a name, ID, dean, and teachers/staff. Automatically saves to a new file after modifying staff lists.
 */
public class Department {

    private String departmentName;
    private int departmentId;
    private int deanId;
    private Teacher dean;
    private List<Teacher> teachers;
    private List<Staff> staff;
    private String saveFile;

    public Department(String departmentName, int departmentId, int deanId,
                      List<Teacher> teachers, List<Staff> staff, String saveFile) {
        this.departmentName = departmentName;
        this.departmentId = departmentId;
        this.deanId = deanId;
        this.teachers = teachers;
        this.staff = staff;
        this.saveFile = saveFile;

        loadDept(saveFile);
        if ((teachers != null || !teachers.isEmpty()) || (staff == null || !staff.isEmpty())) {
            saveDept(saveFile);
        }
    }

    /**
     * Add a teacher to a department, and save after adding.
     * @param teacher Teacher to add to department
     */
    public void addTeacher(Teacher teacher) {
        if (teachers == null) {
            teachers = new ArrayList<>();
        }
        if (!teacherExists(teacher.getId())) {
            teachers.add(teacher);
            saveDept(saveFile);
        }
        else {
            throw new DepartmentException(String.format(
                    "Teacher with ID %d is already in this department!", teacher.getId()));
        }
    }

    /**
     * Add a staff member to a department, and save once added.
     * @param staffMember Staff member to add.
     */
    public void addStaff(Staff staffMember) {
        if (staff == null) {
            staff = new ArrayList<>();
        }
        staff.add(staffMember);
    }

    /**
     * Remove teacher and save.
     * @param teacher Teacher to remove
     */
    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
        saveDept(saveFile);
    }

    /**
     * Remove staff member and save.
     * @param staffMember Staff member to remove
     */
    public void removeStaff(Staff staffMember) {
        staff.remove(staffMember);
        saveDept(saveFile);
    }

    /**
     * Load department from file.
     * @param name File name to read from
     */
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

    /**
     * Save department state to a file.
     * @param name File to save to.
     */
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

            verifyDean();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (DepartmentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Department saved to " + name);
    }

    /**
     * Determine whether a given staff member is in the department.
     * @param id Staff ID to check for
     * @return Whether staff member is currently in the department.
     */
    private boolean staffMemberExists(String id) {
        if (staff == null || staff.isEmpty())
            return false;
        int parsedId = Integer.parseInt(id);
        for (Staff s : staff) {
            if (s.getId() == parsedId) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine whether a given teacher is in the department.
     * @param id Teacher ID to check for
     * @return Whether teacher is currently in the department.
     */
    private boolean teacherExists(int id) {
        if (teachers == null || teachers.isEmpty())
            return false;
        for (Teacher t : teachers) {
            if (t.getId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Determine whether there is a teacher in the department that matches the department's dean ID.
     * @return Whether there is a valid dean in the department
     */
    public boolean verifyDean() {
        for (Teacher t : teachers) {
            if (t.getId() == deanId) {
                dean = t;
                return true;
            }
        }
        throw new DepartmentException("No dean found!");
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Department Information: \n");
        str.append("Name: ").append(departmentName).append("\n");
        str.append("Department ID: ").append(departmentId).append("\n");
        str.append("Dean ID: ").append(deanId).append("\n");
        str.append("Employee List: \n");
        for (Teacher t : teachers) {
            str.append(t).append("\n");
        }
        for (Staff s : staff) {
            str.append(s).append("\n");
        }
        return str.toString();
    }
}
