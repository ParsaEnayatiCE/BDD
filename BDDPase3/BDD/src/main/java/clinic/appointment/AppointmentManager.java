package clinic.appointment;

import java.time.LocalDateTime;

public class AppointmentManager {
    private static final String CONFIRMED = "تایید";
    private static final String CANCELED = "لغو";
    private static final String EMERGENCY_PATIENT = "اورژانسی";
    private static final String REGULAR_PATIENT = "عادی";
    private static final String APPROVED_INSURANCE = "تاییدشده";
    private static final String REJECTED_INSURANCE = "ردشده";

    public String determineAppointmentStatus(String patientType, String insuranceStatus,
                                             Doctor doctor, LocalDateTime appointmentTime) {
        // Rule 1: Doctor Availability
        if (!doctor.isAvailableOn(appointmentTime.toLocalDate())) {
            return CANCELED;
        }

        // Rule 2: Emergency Patient
        if (EMERGENCY_PATIENT.equals(patientType)) {
            // Emergency patient and doctor not available after appointment date
            if (appointmentTime.toLocalDate().isAfter(doctor.getAvailableUntil())) {
                return CANCELED;
            }
            return CONFIRMED;
        }

        // Rule 3: Regular Patient with Approved Insurance
        if (REGULAR_PATIENT.equals(patientType) && APPROVED_INSURANCE.equals(insuranceStatus)) {
            return CONFIRMED;
        }

        // Rule 4: Rejected Insurance
        if (REJECTED_INSURANCE.equals(insuranceStatus)) {
            return CANCELED;
        }

        // Default case - if none of the rules apply
        return CANCELED;
    }
}