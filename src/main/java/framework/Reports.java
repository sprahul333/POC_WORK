package framework;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import framework.constants.LogStatus;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Reports {

    private TestUtil testUtil;

    public void logReportsToTheFile(LogStatus logStatus, String message)
    {
        ExtentTest reports=testUtil.getExtentTest();
        switch (logStatus)
        {
            case PASS -> reports.log(Status.PASS,message);
            case FAIL -> reports.log(Status.FAIL,message);
            case WARNING -> reports.log(Status.WARNING,message);
            case INFO -> reports.log(Status.INFO,message);
            case SKIP -> reports.log(Status.SKIP,message);
        }
    }

}
