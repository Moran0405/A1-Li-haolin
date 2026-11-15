import java.time.DayOfWeek;
import java.util.Set;

public class Dietitian extends HealthProfessional {

    private final String focusArea;

    public Dietitian(String id, String name, Set<DayOfWeek> availableDays, String focusArea) {
        super(id, name, availableDays);
        if (focusArea == null || focusArea.trim().isEmpty()) {
            throw new IllegalArgumentException("Focus area must not be empty.");
        }
        this.focusArea = focusArea;
    }

    public Dietitian(String id, String name, String focusArea) {
        super(id, name);
        if (focusArea == null || focusArea.trim().isEmpty()) {
            throw new IllegalArgumentException("Focus area must not be empty.");
        }
        this.focusArea = focusArea;
    }

    public String getFocusArea() {
        return focusArea;
    }

    @Override
    public String getRole() {
        return "Dietitian";
    }

    public void createMealPlan(String patientName) {
        System.out.println(getName() + " creates a meal plan for " + patientName + ".");
    }

    @Override
    public String toString() {
        return super.toString() + " | focus=" + focusArea;
    }
}
