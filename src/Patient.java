public class Patient {

    private final String name;
    private final String phone;

    public Patient(String name, String phone) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient name must not be empty.");
        }
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Patient phone must not be empty.");
        }
        if (!phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Patient phone must be 10 digits.");
        }
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return name + " (" + phone + ")";
    }
}