public class FullTimeTeacher extends Teacher {

    public FullTimeTeacher(String name, int age, int hiredYear, String gender, int id, String specialty, String degree) {
        super(name, age, hiredYear, gender, id, specialty, degree);
        super.setFullTime(true);
    }
}
