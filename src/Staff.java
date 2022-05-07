public class Staff extends Person implements Payable {

    private final String duty;
    private final double hoursWorked;

    public Staff(String name, int age, int hiredYear, String gender, int id, String duty, double hoursWorked) {
        super(name, age, hiredYear, gender, id);
        this.duty = duty;
        this.hoursWorked = hoursWorked;
    }

    /**
     * Determine pay based on hours worked.
     * Pay is semantically equivalent to (hoursWorked * 32 * 2) * 0.75
     * @return Pay
     */
    @Override
    public double computePayRoll() {
        return 48 * ((hoursWorked > 40) ? 40 : hoursWorked);
    }

    @Override
    public String toString() {
        return String.format("%s|%s|%d|%d|%s|%d|%s|%.2f", "Staff", getName(), getAge(), getHiredYear(), getGender(),
                getId(), duty, hoursWorked);
    }

    /**
     * Determine bonus pay based on year hired.
     * @return Bonus pay
     */
    @Override
    double determineBonus() {
        return (hoursWorked * Math.log(getHiredYear() - 1990));
    }
}
