import java.time.DayOfWeek;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public abstract class HealthProfessional {

    private final String id;
    private final String name;
    private final Set<DayOfWeek> availableDays;

    public HealthProfessional(String id, String name, Set<DayOfWeek> availableDays) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Health professional ID must not be empty.");
        }
        if (!id.matches("\\d+")) {
            throw new IllegalArgumentException("Health professional ID must contain digits only.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Health professional name must not be empty.");
        }
        if (availableDays == null || availableDays.isEmpty()) {
            throw new IllegalArgumentException("At least one available day is required.");
        }
        this.id = id;
        this.name = name;
        this.availableDays = Collections.unmodifiableSet(EnumSet.copyOf(availableDays));
    }

    public HealthProfessional(String id, String name) {
        this(id, name, EnumSet.range(DayOfWeek.MONDAY, DayOfWeek.FRIDAY));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<DayOfWeek> getAvailableDays() {
        return availableDays;
    }

    public abstract String getRole();

    @Override
    public String toString() {
        return String.format("[%s] id=%s name=%s available=%s", getRole(), id, name, availableDays);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HealthProfessional)) {
            return false;
        }
        HealthProfessional that = (HealthProfessional) other;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
