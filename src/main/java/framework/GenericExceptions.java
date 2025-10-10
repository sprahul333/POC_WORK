package framework;

import com.aventstack.extentreports.Status;
import framework.constants.LogStatus;

public class GenericExceptions extends RuntimeException {
    public GenericExceptions(String message) {
        super(message);

        ReusableLibrary.testUtilThread.get().getReports().logReportsToTheFile(LogStatus.FAIL_SCREENSHOT,message);
    }
}
