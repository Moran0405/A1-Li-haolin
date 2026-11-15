import java.time.DayOfWeek;
import java.util.Set;

public class Nurse extends HealthProfessional {

    private final String ward;

    public Nurse(String id, String name, Set<DayOfWeek> availableDays, String ward) {
        super(id, name, availableDays);
        if (ward == null || ward.trim().isEmpty()) {
            throw new IllegalArgumentException("Ward must not be empty.");
        }
        this.ward = ward;
    }

    public Nurse(String id, String name, String ward) {
        super(id, name);
        if (ward == null || ward.trim().isEmpty()) {
            throw new IllegalArgumentException("Ward must not be empty.");
        }
        this.ward = ward;
    }

    public String getWard() {
        return ward;
    }

    @Override
    public String getRole() {
        return "Nurse";
    }

    public void takeBloodSample(String patientName) {
        System.out.println(getName() + " takes a blood sample from " + patientName + ".");
    }

    @Override
    public String toString() {
        return super.toString() + " | ward=" + ward;
    }
}
