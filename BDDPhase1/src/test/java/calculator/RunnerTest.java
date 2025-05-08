package calculator;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",   // where your *.feature files live
        glue     = "calculator",                    // the package that contains your MyStepdefs class
        plugin   = { "pretty", "html:target/cucumber-report.html" },
        monochrome = true
)
public class RunnerTest {
}
