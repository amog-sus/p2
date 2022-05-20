import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        this.saveFile = saveFile;

        loadDept(saveFile);
        if ((this.teachers != null) && (this.staff != null)) {
            saveDept(saveFile);
        }
        for (Teacher t : teachers) {
            addTeacher(t);
        }
        for (Staff s : staff) {
            addStaff(s);
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
            System.out.println(String.format(
                    "Teacher with ID %d is already in this department,"
                            + " not adding again.", teacher.getId()));
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
        if (!staffMemberExists(staffMember.getId())) {
            staff.add(staffMember);
            saveDept(saveFile);
        }
        else {
            System.out.println(String.format(
                    "Staff member with ID %d is already in this department,"
                            + " not adding again.", staffMember.getId()));
        }
    }

    /**
     * Remove teacher and save.
     * @param teacher Teacher to remove
     */
    public void removeTeacher(Teacher teacher) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teacher.getId() == teachers.get(i).getId()) {
                teachers.remove(teachers.get(i));
            }
        }
        saveDept(saveFile);
    }

    /**
     * Remove staff member and save.
     * @param staffMember Staff member to remove
     */
    public void removeStaff(Staff staffMember) {
        if (staff == null) 
            return;
        for (int i = 0; i < staff.size(); i++) {
            if (staffMember.getId() == staff.get(i).getId()) {
                staff.remove(staff.get(i));
            }
        }
        saveDept(saveFile);
    }

    /**
     * Load department from file.
     * @param name File name to read from
     */
    public void loadDept(String name) {
        File tFile = new File(name + "_teachers.ser");
        File sFile = new File(name + "_staff.ser");

        try (FileInputStream fos = new FileInputStream(tFile);
                ObjectInputStream ois = new ObjectInputStream(fos)) {
                this.teachers = (List<Teacher>) ois.readObject();
            
        } catch (FileNotFoundException ex) {
            System.out.println("The file " + name + " does not exist!");
        } catch (IOException ex) {
            System.out.println("An error has occurred while loading the file" + name + ".");
        } catch (ClassNotFoundException ex) {
            System.out.println("You should never see this!");
        }
        
        try (FileInputStream fos = new FileInputStream(sFile);
                ObjectInputStream ois = new ObjectInputStream(fos)) {
                this.staff = (List<Staff>) ois.readObject();
            
        } catch (FileNotFoundException ex) {
            System.out.println("The file " + name + " does not exist!");
        } catch (IOException ex) {
            System.out.println("An error has occurred while loading the file" + name + ".");
        } catch (ClassNotFoundException ex) {
            System.out.println("You should never see this!");
        }

        
    }

    /**
     * Save department state to a file.
     * @param name File to save to.
     */
    public void saveDept(String name) {
        File tFile = new File(name + "_teachers.ser");
        File sFile = new File(name + "_staff.ser");
        
        try (FileOutputStream fos = new FileOutputStream(tFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(teachers);
        } catch (IOException ex) {
            ex.getMessage();
        }
        
        try (FileOutputStream fos = new FileOutputStream(sFile);
                ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                    oos.writeObject(staff);
        } catch (IOException ex) {
            ex.getMessage();
        }
    }

    /**
     * Determine whether a given staff member is in the department.
     * @param id Staff ID to check for
     * @return Whether staff member is currently in the department.
     */
    private boolean staffMemberExists(int id) {
        if (staff == null || staff.isEmpty())
            return false;
        for (Staff s : staff) {
            if (s.getId() == id) {
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
        if (teachers == null) 
            return false;
        for (Teacher t : teachers) {
            if (t.getId() == deanId) {
                dean = t;
                return true;
            }
        }
        System.out.println("No dean found!");
        return false;
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
