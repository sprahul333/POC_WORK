package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = "./src/test/resources/features", //Path of the feature files
        glue = "stepDefs", //Path/Package of the step definitions
        tags = "@CreateAndApprovePawnApplication and @English", //Mention the tag name to run the specific scenarios
        monochrome = true, //If true, it will display the console output in a proper readable format
        plugin = {"pretty", "html: CucumberHTMLReport.html","json: CucumberJSONReport.json","junit: CucumberJSONReport.xml"},
        publish = true, //Helps in publishing the reports onto the cucumber cloud server
        dryRun = false //Checking whether each step is having a method or step definition or not
)

//In the tags if we provide "And", that means it checks for the test cases which has both the tags
//In the tags If we provide "Or", that means it checks for the test cases which has either of the tags

public class TestRunner extends AbstractTestNGCucumberTests {

    //Below data provider is used to collect all the required scenarios based on the tags mentioned in the CucumberOptions
    //Once the scenarios are collected, then it will pass the scenario details to the Hook class via Scenario object
    @DataProvider(parallel = false) //--> Helps us in running the test cases in parallel mode
    public Object[][] scenarios()
    {
        return super.scenarios();
    }
}
