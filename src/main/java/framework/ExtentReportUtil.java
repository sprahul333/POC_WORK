package framework;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportUtil {

    public synchronized ExtentReports getExtentReports(String testCaseName) {

        ExtentSparkReporter sparkReporter;

        if(!testCaseName.equalsIgnoreCase("Consolidated"))
        {
            if (System.getProperty("Parallel_Not").equalsIgnoreCase("Parallel"))
            {
                String path = PathUtils.getExtentReportsPath(testCaseName + System.currentTimeMillis());
                sparkReporter = new ExtentSparkReporter(path);
            }

            else
                sparkReporter = new ExtentSparkReporter(PathUtils.getExtentReportsPath(testCaseName));
        }

        else
        {
            sparkReporter = new ExtentSparkReporter(PathUtils.getExtentReportsPath(testCaseName));
        }

        sparkReporter.config().setReportName("XYZ Banking Test Cases");
        sparkReporter.config().setDocumentTitle("Automation Test Results");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setTimelineEnabled(true);

        //Show the preview of the image when it is captured in Base64
        sparkReporter.config().thumbnailForBase64(true);

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter); //We will mention what kind of report object we are using

        extentReports.setSystemInfo("Operating System",System.getProperty("os.name"));
        extentReports.setSystemInfo("User Name",System.getProperty("user.name"));
        extentReports.setSystemInfo("Java Version",System.getProperty("java.version"));

        return extentReports;
    }
}
