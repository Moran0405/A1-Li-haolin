import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class AssignmentOne {

    public static void main(String[] args) {
        System.out.println("=== PROG2004 A1: Health Service Appointment System ===");

        List<HealthProfessional> professionals = new ArrayList<>();

        GeneralPractitioner gp1 = new GeneralPractitioner("1001", "Dr Alice Chen", "Room 1");
        GeneralPractitioner gp2 = new GeneralPractitioner("1002", "Dr Ben Patel",
                EnumSet.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY, DayOfWeek.SATURDAY), "Room 2");
        GeneralPractitioner gp3 = new GeneralPractitioner("1003", "Dr Carol Wu", "Room 3");
        Specialist specialist = new Specialist("2001", "Dr Daniel Kim",
                EnumSet.of(DayOfWeek.MONDAY, DayOfWeek.FRIDAY), "Cardiology");
        Nurse nurse = new Nurse("3001", "Nina Brown", "Ward B");
        Dietitian dietitian = new Dietitian("4001", "Owen Green",
                EnumSet.of(DayOfWeek.TUESDAY, DayOfWeek.THURSDAY), "Diabetes nutrition");

        professionals.add(gp1);
        professionals.add(gp2);
        professionals.add(gp3);
        professionals.add(specialist);
        professionals.add(nurse);
        professionals.add(dietitian);

        for (HealthProfessional professional : professionals) {
            System.out.println("  " + professional);
        }

        gp1.prescribe("Amoxicillin");
        specialist.refer("John Smith", "Cardiology Ward");
        nurse.takeBloodSample("John Smith");
        dietitian.createMealPlan("John Smith");
        Patient john = new Patient("John Smith", "0412345678");
        Patient mary = new Patient("Mary Jones", "0498765432");
        Patient david = new Patient("David Lee", "0433000111");

        AppointmentManager manager = new AppointmentManager();

        System.out.println(manager.add(new Appointment(john, gp1, LocalTime.of(9, 0))));
        System.out.println(manager.add(new Appointment(mary, gp2, LocalTime.of(10, 30))));
        System.out.println(manager.add(new Appointment(david, specialist, LocalTime.of(8, 30))));
        System.out.println(manager.add(new Appointment(john, nurse, LocalTime.of(11, 0))));
        System.out.println(manager.add(new Appointment(mary, dietitian, LocalTime.of(13, 30))));

        printAppointments("All appointments", manager.getAll());
        printAppointments("Sorted by time", manager.sortedByTime());
        printAppointments("Filtered by professional id",
                manager.findByProfessionalId("1002"));
        printAppointments("Filtered by patient phone",
                manager.findByPatientPhone("0412345678"));

        System.out.println("Error case 1: double booking");
        System.out.println(manager.add(new Appointment(john, gp1, LocalTime.of(9, 0))));

        System.out.println("Error case 2: invalid time slot");
        try {
            manager.add(new Appointment(john, gp3, LocalTime.of(8, 15)));
        } catch (IllegalArgumentException exception) {
            System.out.println("  Rejected: " + exception.getMessage());
        }

        System.out.println("Cancel by professional id + time");
        System.out.println(manager.cancelByProfessionalAndTime("1001", LocalTime.of(9, 0)));
        System.out.println(manager.cancelByProfessionalAndTime("1001", LocalTime.of(9, 0)));

        System.out.println("Cancel by patient phone");
        System.out.println(manager.cancelByPatientPhone("0498765432"));
        System.out.println(manager.cancelByPatientPhone("0000000000"));

        printAppointments("Remaining appointments (sorted)", manager.sortedByTime());

    }

    private static void printAppointments(String title, List<Appointment> appointments) {
        System.out.println("  " + title + " (" + appointments.size() + "):");
        for (Appointment appointment : appointments) {
            System.out.println("    " + appointment);
        }
    }
}
