package runners;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "./src/test/resources/features",
        tags = "@English",
        glue = {"stepDefs"},
        monochrome = true,
        plugin = {"pretty", "html:target/cucumber-report.html"}
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @DataProvider
    public Object[][] runScenarios()
    {
        return super.scenarios();
    }
}
