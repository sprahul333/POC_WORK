package framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import lombok.Data;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.HashMap;
import java.util.Map;

@Data
public class TestUtil {

    private WebDriver driver;
    private ExcelUtils excelUtils;
    private SeleniumUtils seleniumUtils;
    private JSFunctions jsFunctions;
    private PropertiesUtil propertiesUtil;
    private ElementUtils elementUtils;
    private Reports reports;
    private ExtentReports extentReports;
    private ExtentTest extentTest;
    private String scenarioName;
    private Actions actions;
    private String currentStep;
    private SQLActions sqlActions;

    private Map<String,String> testData=new HashMap<>();
    private Map<String,Integer> tracker=new HashMap<>();

    public void setData(String key,String value)
    {
        testData.put(key,value);
    }

    public String getData(String key)
    {
        return testData.getOrDefault(key,"");
    }

    public synchronized void setTracker(String key,int value)
    {
        tracker.put(key,value);
    }

    public synchronized int getTracker(String key)
    {
        return tracker.getOrDefault(key,-1);
    }
}
