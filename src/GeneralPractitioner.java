import java.time.DayOfWeek;
import java.util.Set;

public class GeneralPractitioner extends HealthProfessional {

    private final String room;

    public GeneralPractitioner(String id, String name, Set<DayOfWeek> availableDays, String room) {
        super(id, name, availableDays);
        if (room == null || room.trim().isEmpty()) {
            throw new IllegalArgumentException("Room must not be empty.");
        }
        this.room = room;
    }

    public GeneralPractitioner(String id, String name, String room) {
        super(id, name);
        if (room == null || room.trim().isEmpty()) {
            throw new IllegalArgumentException("Room must not be empty.");
        }
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    @Override
    public String getRole() {
        return "General Practitioner";
    }

    public void prescribe(String medication) {
        System.out.println(getName() + " prescribes " + medication + ".");
    }

    @Override
    public String toString() {
        return super.toString() + " | room=" + room;
    }
}
