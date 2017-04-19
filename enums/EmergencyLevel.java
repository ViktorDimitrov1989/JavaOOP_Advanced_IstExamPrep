package enums;

public enum EmergencyLevel {
    MINOR,
    MAJOR,
    DISASTER;


    @Override
    public String toString() {
        char upper = this.name().charAt(0);
        char lower = Character.toLowerCase(upper);
        return this.name().toLowerCase().replace(lower, upper);
    }
}
