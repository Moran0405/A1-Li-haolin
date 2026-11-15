import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AppointmentManager {

    private final List<Appointment> appointments = new ArrayList<>();

    public String add(Appointment appointment) {
        if (appointment == null) {
            return "WARNING: appointment must not be null.";
        }
        for (Appointment existing : appointments) {
            boolean sameProfessional = existing.getProfessional().equals(appointment.getProfessional());
            boolean sameTime = existing.getTime().equals(appointment.getTime());
            if (sameProfessional && sameTime) {
                return String.format("WARNING: %s is already booked at %s.",
                        appointment.getProfessional().getName(), appointment.getTime());
            }
        }
        appointments.add(appointment);
        return String.format("OK: appointment added -> %s", appointment);
    }

    public List<Appointment> getAll() {
        return new ArrayList<>(appointments);
    }

    public List<Appointment> findByProfessionalId(String professionalId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId().equals(professionalId)) {
                result.add(appointment);
            }
        }
        return result;
    }

    public List<Appointment> findByPatientPhone(String phone) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getPhone().equals(phone)) {
                result.add(appointment);
            }
        }
        return result;
    }

    public List<Appointment> sortedByTime(List<Appointment> source) {
        List<Appointment> result = new ArrayList<>(source);
        Collections.sort(result);
        return result;
    }

    public List<Appointment> sortedByTime() {
        return sortedByTime(appointments);
    }

    public String cancelByProfessionalAndTime(String professionalId, LocalTime time) {
        Iterator<Appointment> iterator = appointments.iterator();
        while (iterator.hasNext()) {
            Appointment appointment = iterator.next();
            if (appointment.getProfessional().getId().equals(professionalId)
                    && appointment.getTime().equals(time)) {
                iterator.remove();
                return String.format("OK: cancelled -> %s", appointment);
            }
        }
        return String.format("WARNING: no appointment found for professional %s at %s.",
                professionalId, time);
    }

    public String cancelByPatientPhone(String phone) {
        Iterator<Appointment> iterator = appointments.iterator();
        int removed = 0;
        while (iterator.hasNext()) {
            Appointment appointment = iterator.next();
            if (appointment.getPatient().getPhone().equals(phone)) {
                iterator.remove();
                removed++;
            }
        }
        if (removed == 0) {
            return String.format("WARNING: no appointment found for patient phone %s.", phone);
        }
        return String.format("OK: cancelled %d appointment(s) for patient phone %s.", removed, phone);
    }
}