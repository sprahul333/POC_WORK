package stepDefs;

import com.aventstack.extentreports.ExtentTest;
import framework.*;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.*;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import org.codehaus.plexus.util.FileUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//This is the main component of the Cucumber framework.
//Where it facilitates the activities that needs to be performed before and after execution of test cases
public class Hook {

    //Different annotations present in Cucumber framework:
    //1. BeforeAll --> Before triggering the test cases of the test suite
    //2. AfterAll --> After triggering the test cases of the test suite
    //3. Before --> Set of activities that needs to be performed before every scenario is triggered
    //4. After --> Set of activities that needs to be performed after every scenario is triggered
    //5. BeforeStep --> Set of activities that needs to be performed before the exeuction of every step
    //6. AfterStep --> Set of activities that needs to be performed after the exeuction of every step

    TestUtil testUtil = new TestUtil();
    private int currentStepDefIndex = 0;
    ExtentTest testCase;
    ExtentTest stepDef;
    String dbTestCaseName="";

    @BeforeAll
    public static void performBeforeAll() {
        BrowserUtils.killExistingBrowsers();
        backupOldResults();
        PathUtils.generateResultsFolder(); //Generates the results folder as per the execution time stamp
    }

    @AfterAll
    public static void performAfterAll() {
        System.out.println("Performing actions after all scenarios");
    }

    @Before
    public void performBefore(Scenario sc) {
        //Prints the name of the scenario
        System.out.println(sc.getName());

        //Prints the list of tags that are present in the feature file against the given scenario
        System.out.println(sc.getSourceTagNames());

        //Prints the path of the feature file, where the scenario is being executed
        System.out.println(sc.getUri());

        System.out.println(sc.getLine());

        testUtil = Optional.ofNullable(ReusableLibrary.testUtilThread.get())
                .orElseGet(
                        () -> {
                            testUtil.setPropertiesUtil(new PropertiesUtil());
                            testUtil.setDriver(BrowserUtils.getDriver(testUtil.getPropertiesUtil().getBrowser()));
                            testUtil.setReports(new Reports(testUtil.getDriver(), testUtil));
                            testUtil.setElementUtils(new ElementUtils(testUtil.getDriver(),testUtil.getReports()));
                            testUtil.setExcelUtils(new ExcelUtils());
                            testUtil.setJsFunctions(new JSFunctions(testUtil.getDriver()));
                            testUtil.setSeleniumUtils(new SeleniumUtils(testUtil.getDriver(), testUtil.getElementUtils(), testUtil.getReports(), testUtil.getJsFunctions()));
                            testUtil.setSqlActions(new SQLActions());

                            if (testUtil.getPropertiesUtil().getConsolidatedOrIndividualReport().equalsIgnoreCase("Consolidated"))
                                testUtil.setExtentReports(new ExtentReportUtil().getExtentReports("Consolidated"));

                            ReusableLibrary.testUtilThread.set(testUtil);
                            return testUtil;
                        }
                );

        if (testUtil.getPropertiesUtil().getConsolidatedOrIndividualReport().equalsIgnoreCase("Individual"))
            testUtil.setExtentReports(new ExtentReportUtil().getExtentReports(getTestCaseName(sc)));

        testUtil.setScenarioName(getScenarioName(sc));
        testCase = testUtil.getExtentReports().createTest(testUtil.getScenarioName());
        testUtil.setExtentTest(testCase);
    }

    @After
    public void performAfter(Scenario sc) {
        //Prints whether the test case is passed or failed or skipped or undefined
        System.out.println(sc.getStatus());
//        System.out.println("Performing actions after each scenario");

        testUtil.getExtentReports().flush();
    }

    @BeforeStep
    public void performBeforeStep(Scenario sc) {
        testUtil.setCurrentStep(getCurrentStepText(sc));
        stepDef = testCase.createNode(getCurrentStepText(sc));
        testUtil.setExtentTest(stepDef);

        currentStepDefIndex++;

        FileUtility.writeDataToTheFile(PathUtils.getStepsLogPath(getTestCaseName(sc)), PathUtils.getCurrentDateTime("dd-MM-yyyy hh-mm-ss")+" --- Step Started for: "+testUtil.getCurrentStep() + " : " + "InProgress");
//        System.out.println("Performing actions before each step");
    }

    @AfterStep
    public void performAfterStep(Scenario sc) {

        //Prints whether the current step that we are executing is passed, failed, skipped, fatal etc..
//        System.out.println(stepDef.getStatus());
        FileUtility.writeDataToTheFile(PathUtils.getStepsLogPath(getTestCaseName(sc)), PathUtils.getCurrentDateTime("dd-MM-yyyy hh-mm-ss")+" --- Step Ended for: "+testUtil.getCurrentStep() + " : " + stepDef.getStatus());
    }

    //Forming a scenario name based on the tags
    public String getScenarioName(Scenario sc) {
        //sc.getSourceTagNames() --> Will return a collection of strings
        //Collectors.joining() --> Joining the strings on the basis of '_' symbol
        return sc.getSourceTagNames().stream().collect(Collectors.joining("_")).replace("@", "");
    }

    public String getTestCaseName(Scenario sc) {
        return sc.getSourceTagNames().stream().filter(s -> s.contains("TC:")).collect(Collectors.joining("")).replace("@TC:", "");
    }

    public String getCurrentStepText(Scenario sc) {
        Field f;
        String stepDefinitions = "";

        PickleStepTestStep currentStepDef = null;
        List<PickleStepTestStep> stepDefs = null;

        try {

            // Get the delegate from the scenario
            Field delegate = sc.getClass().getDeclaredField("delegate");
            delegate.setAccessible(true); //Using the java reflection concepts, we are breaking the concept of encapsulation and accessing the required fields
            TestCaseState testCaseState = (TestCaseState) delegate.get(sc);

            f = testCaseState.getClass().getDeclaredField("testCase");
            f.setAccessible(true); //Using the java reflection concepts, we are breaking the concept of encapsulation and accessing the required fields
            TestCase testCase = (TestCase) f.get(testCaseState);

            // You need to filter out before/after hooks
            stepDefs = testCase.getTestSteps().stream().filter(x -> x instanceof PickleStepTestStep).map(x -> (PickleStepTestStep) x).collect(Collectors.toList());

            for (int i = 0; i < stepDefs.size(); i++) {
                stepDefinitions += stepDefs.get(i).getStepText() + ",";
            }

            if (stepDefinitions.endsWith(","))
                stepDefinitions = stepDefinitions.substring(0, stepDefinitions.length() - 1);

            currentStepDef = stepDefs.get(currentStepDefIndex);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return currentStepDef.getStep().getText();
    }

    private static void backupOldResults() {
        try {

            //This recursively copies all files and subdirectories from the source directory into target directory
            //If the old results folder is missing then it will create a new one
            FileUtils.copyDirectoryStructure(new File("./Results"), new File("./Results/Old Results"));

            //Collects all subdirectory names in the ./Results directory, excluding the Old Results folder.
            List<String> folders = FileUtils.getDirectoryNames(new File("./Results"), "*", "Old Results", false, false);

            //Loops through each folder name in the folders list.
            //If the folder name is not Old Results (case-insensitive check), it forcibly deletes it using forceDelete.

            for (String string : folders) {
                if (!string.equalsIgnoreCase("Old Results")) {
                    FileUtils.forceDelete(new File("./Results/" + string));
                }
            }
        } catch (Exception ex) {

        }
    }


}
