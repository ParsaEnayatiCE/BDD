package clinic.appointment;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/clinic/appointment/appointment_management.feature",
        glue = {"clinic.appointment"},
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class AppointmentRunnerTest {
}