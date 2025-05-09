package clinic.appointment;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class AppointmentStepDefs {
    private String patientType;
    private String insuranceStatus;
    private Doctor doctor;
    private LocalDateTime appointmentTime;
    private AppointmentManager appointmentManager;
    private String appointmentStatus;

    // Mapping between English and Persian status values
    private static final Map<String, String> statusMap = new HashMap<>();
    static {
        statusMap.put("confirmed", "تایید");
        statusMap.put("canceled", "لغو");
        // Add other mappings as needed
    }

    @Given("the appointment system is ready")
    public void theAppointmentSystemIsReady() {
        appointmentManager = new AppointmentManager();
    }

    @Given("a {string} patient")
    public void aPatientWithType(String type) {
        this.patientType = type;
    }

    @Given("the patient's insurance status is {string}")
    public void thePatientInsuranceStatusIs(String status) {
        this.insuranceStatus = status;
    }

    @Given("doctor {string} from {string} department is available until {string}")
    public void doctorFromDepartmentIsAvailableUntil(String name, String department, String availableUntil) {
        LocalDate date = LocalDate.parse(availableUntil);
        this.doctor = new Doctor(name, department, date);
    }

    @When("an appointment is requested for {string}")
    public void anAppointmentIsRequestedFor(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.appointmentTime = LocalDateTime.parse(dateTime, formatter);
        this.appointmentStatus = appointmentManager.determineAppointmentStatus(
                patientType, insuranceStatus, doctor, appointmentTime);
    }

    @Then("the appointment status should be {string}")
    public void theAppointmentStatusShouldBe(String expectedStatus) {
        // If the expected status is in English and has a Persian mapping, use that mapping
        if (statusMap.containsKey(expectedStatus)) {
            expectedStatus = statusMap.get(expectedStatus);
        }
        Assert.assertEquals(expectedStatus, appointmentStatus);
    }
}