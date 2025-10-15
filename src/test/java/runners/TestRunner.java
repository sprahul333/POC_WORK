package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;

@CucumberOptions(
        features = "./src/test/resources/features/SampleFeature.feature",
        glue = "stepDefs",
        tags = "@CreateCustomer or @CreateAccount",
        monochrome = true,
        plugin = {"pretty", "html: CucumberHTMLReport.html","json: CucumberJSONReport.json","junit: CucumberJSONReport.xml"},
        publish = true,
        dryRun = false
)

public class TestRunner extends AbstractTestNGCucumberTests
{
    @DataProvider(parallel = true)
    public Object[][] scenarios()
    {
        return super.scenarios();
    }
}
