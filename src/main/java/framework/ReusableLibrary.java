package framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ReusableLibrary {

    public static ThreadLocal<TestUtil> testUtilThreadLocal = new ThreadLocal<>();

    protected ExtentReports extentReports=testUtilThreadLocal.get().getExtentReports();
    protected ExtentTest extentTest=testUtilThreadLocal.get().getExtentTest();
    protected Reports reports=testUtilThreadLocal.get().getReports();
    protected String scenarioName=testUtilThreadLocal.get().getScenarioName();
}
