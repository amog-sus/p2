public abstract class Person {

    private String name;
    private int age;
    private int hiredYear;
    private String gender;
    private int id;

    public Person(String name, int age, int hiredYear, String gender, int id) {
        this.name = name;
        this.age = age;
        this.hiredYear = hiredYear;
        this.gender = gender;
        this.id = id;
    }

    abstract double determineBonus();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHiredYear() {
        return hiredYear;
    }

    public void setHiredYear(int hiredYear) {
        this.hiredYear = hiredYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
