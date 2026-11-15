import java.time.DayOfWeek;
import java.util.Set;

public class Specialist extends HealthProfessional {

    private final String specialty;

    public Specialist(String id, String name, Set<DayOfWeek> availableDays, String specialty) {
        super(id, name, availableDays);
        if (specialty == null || specialty.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialty must not be empty.");
        }
        this.specialty = specialty;
    }

    public Specialist(String id, String name, String specialty) {
        super(id, name);
        if (specialty == null || specialty.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialty must not be empty.");
        }
        this.specialty = specialty;
    }

    public String getSpecialty() {
        return specialty;
    }

    @Override
    public String getRole() {
        return "Specialist";
    }

    public void refer(String patientName, String toDepartment) {
        System.out.println(getName() + " refers " + patientName + " to " + toDepartment + ".");
    }

    @Override
    public String toString() {
        return super.toString() + " | specialty=" + specialty;
    }
}
