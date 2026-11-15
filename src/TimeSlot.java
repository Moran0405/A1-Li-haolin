import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class TimeSlot {

    public static final LocalTime OPENING = LocalTime.of(8, 0);
    public static final LocalTime CLOSING = LocalTime.of(16, 0);
    private static final int SLOT_MINUTES = 30;

    private TimeSlot() {
    }

    public static List<LocalTime> allSlots() {
        List<LocalTime> slots = new ArrayList<>();
        LocalTime current = OPENING;
        while (current.isBefore(CLOSING)) {
            slots.add(current);
            current = current.plusMinutes(SLOT_MINUTES);
        }
        return Collections.unmodifiableList(slots);
    }

    public static boolean isValid(LocalTime time) {
        if (time == null) {
            return false;
        }
        if (time.isBefore(OPENING) || !time.isBefore(CLOSING)) {
            return false;
        }
        return time.getMinute() % SLOT_MINUTES == 0
                && time.getSecond() == 0
                && time.getNano() == 0;
    }
}