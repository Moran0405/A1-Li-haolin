import java.time.LocalTime;

public class Appointment implements Comparable<Appointment> {

    private final Patient patient;
    private final HealthProfessional professional;
    private final LocalTime time;

    public Appointment(Patient patient, HealthProfessional professional, LocalTime time) {
        if (patient == null) {
            throw new IllegalArgumentException("An appointment must have a patient.");
        }
        if (professional == null) {
            throw new IllegalArgumentException("An appointment must have a health professional.");
        }
        if (time == null) {
            throw new IllegalArgumentException("An appointment must have a time.");
        }
        if (!TimeSlot.isValid(time)) {
            throw new IllegalArgumentException(
                    "Appointment time must be a 30-minute slot between 08:00 and 16:00.");
        }
        this.patient = patient;
        this.professional = professional;
        this.time = time;
    }

    public Patient getPatient() {
        return patient;
    }

    public HealthProfessional getProfessional() {
        return professional;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public int compareTo(Appointment other) {
        return this.time.compareTo(other.time);
    }

    @Override
    public String toString() {
        return String.format("%s | with %s (id=%s, %s) | at %s",
                patient, professional.getName(), professional.getId(),
                professional.getRole(), time);
    }
}