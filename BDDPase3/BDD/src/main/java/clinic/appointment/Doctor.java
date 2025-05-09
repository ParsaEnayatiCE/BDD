package clinic.appointment;

import java.time.LocalDate;

public class Doctor {
    private String name;
    private String department;
    private LocalDate availableUntil;

    public Doctor(String name, String department, LocalDate availableUntil) {
        this.name = name;
        this.department = department;
        this.availableUntil = availableUntil;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public LocalDate getAvailableUntil() {
        return availableUntil;
    }

    public boolean isAvailableOn(LocalDate date) {
        return !date.isAfter(availableUntil);
    }
}