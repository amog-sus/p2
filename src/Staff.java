public class Staff extends Person implements Payable {

    private final String duty;
    private final double hoursWorked;

    public Staff(String name, int age, int hiredYear, String gender, int id, String duty, double hoursWorked) {
        super(name, age, hiredYear, gender, id);
        this.duty = duty;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double computePayRoll() {
        return 48 * ((hoursWorked > 40) ? 40 : hoursWorked);
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%d|%d|%s|%d|%s|%.2f", "Staff", getName(), getAge(), getHiredYear(), getGender(),
                getId(), duty, hoursWorked);
    }

    @Override
    double determineBonus() {
        return (hoursWorked * Math.log(getHiredYear() - 1990));
    }
}
