package framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import lombok.Data;

@Data
public class TestUtil {

    private ExtentReports extentReports;
    private ExtentTest extentTest;
    private String scenarioName;
    private Reports reports;
    private String currentStep;
}
