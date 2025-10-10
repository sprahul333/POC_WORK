package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "./src/test/resources/features/SampleFeature.feature", //Path of the feature files
        glue = "stepDefs", //Path/Package of the step definitions
        tags = "@CreateCustomer or @CreateAccount", //Mention the tag name to run the specific scenarios
        monochrome = true, //If true, it will display the console output in a proper readable format
        plugin = {"pretty", "html: CucumberHTMLReport.html","json: CucumberJSONReport.json","junit: CucumberJSONReport.xml"},
        publish = true, //Helps in publishing the reports onto the cucumber cloud server
        dryRun = false //Checking whether each step is having a method or step definition or not
)

public class TestRunner extends AbstractTestNGCucumberTests {

    @DataProvider(parallel = false)
    public Object[][] scenarios()
    {
        return super.scenarios();
    }
}
