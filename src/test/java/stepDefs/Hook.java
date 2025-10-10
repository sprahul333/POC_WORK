package stepDefs;

import com.aventstack.extentreports.ExtentTest;
import framework.*;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.*;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;
import lombok.SneakyThrows;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

//This is the main component of the Cucumber framework.
//Where it facilitates the activities that needs to be performed before and after execution of test cases
public class Hook {

    TestUtil testUtil = new TestUtil();
    private int currentStepDefIndex = 0;
    ExtentTest testCase;
    ExtentTest stepDef;
    private static int counter=1;
    static Map<String, Integer> scenariosCounter = Collections.synchronizedMap(new HashMap<String, Integer>());
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
    public void performBefore(Scenario sc)
    {
        if(testUtil.getData("Parallel_Not").isBlank())
        {
            testUtil.setData("Parallel_Not",Thread.activeCount() - 2 > 0 ? "Parallel" : "Not Parallel");
            System.setProperty("Parallel_Not", Thread.activeCount() - 2 > 0 ? "Parallel" : "Not Parallel");
        }

        if(System.getProperty("Parallel_Not").equals("Parallel"))
        {
            PathUtils.applySleep(ThreadLocalRandom.current().nextInt(1000,10000));
        }

        testUtil =Optional.ofNullable(ReusableLibrary.testUtilThread.get())
                .map(testUtil -> {
                    // Check if the driver is null, and reinitialize it if needed
                    if (testUtil.getDriver() == null) {
                        testUtil.setDriver(BrowserUtils.getDriver(testUtil.getPropertiesUtil().getBrowser()));
                        testUtil.setReports(new Reports(testUtil.getDriver(), testUtil));
                        testUtil.setElementUtils(new ElementUtils(testUtil.getDriver(), testUtil.getReports()));
                        testUtil.setJsFunctions(new JSFunctions(testUtil.getDriver()));
                        testUtil.setActions(new Actions(testUtil.getDriver()));
                        testUtil.setSeleniumUtils(new SeleniumUtils(testUtil.getDriver(), testUtil.getElementUtils(), testUtil.getReports(), testUtil.getJsFunctions(),testUtil.getActions()));
                        testUtil.setScenarioName(getScenarioName(sc));
                    }
                    return testUtil;  // Return the already initialized or reinitialized testUtil
                })
                .orElseGet(() -> {
                    // Initialize testUtil if it's not already initialized
                    testUtil.setPropertiesUtil(new PropertiesUtil());
                    testUtil.setDriver(BrowserUtils.getDriver(testUtil.getPropertiesUtil().getBrowser()));
                    testUtil.setReports(new Reports(testUtil.getDriver(), testUtil));
                    testUtil.setElementUtils(new ElementUtils(testUtil.getDriver(), testUtil.getReports()));
                    testUtil.setExcelUtils(new ExcelUtils());
                    testUtil.setJsFunctions(new JSFunctions(testUtil.getDriver()));
                    testUtil.setActions(new Actions(testUtil.getDriver()));
                    testUtil.setSeleniumUtils(new SeleniumUtils(testUtil.getDriver(), testUtil.getElementUtils(), testUtil.getReports(), testUtil.getJsFunctions(),testUtil.getActions()));
                    testUtil.setSqlActions(new SQLActions());
                    testUtil.setScenarioName(getScenarioName(sc));

                    if (testUtil.getPropertiesUtil().getConsolidatedOrIndividualReport().equalsIgnoreCase("Consolidated")) {
                        testUtil.setExtentReports(new ExtentReportUtil().getExtentReports("Consolidated"));
                    }

                    ReusableLibrary.testUtilThread.set(testUtil); // Set the testUtil to the thread
                    return testUtil;  // Return the newly initialized testUtil
                });


        if (testUtil.getPropertiesUtil().getConsolidatedOrIndividualReport().equalsIgnoreCase("Individual"))
            testUtil.setExtentReports(new ExtentReportUtil().getExtentReports(getScenarioName(sc)));

        testUtil.setData("Language",getLanguage(sc));

        if (testUtil.getPropertiesUtil().getConsolidatedOrIndividualReport().equalsIgnoreCase("Consolidated"))
        {
            fetchScenarioNameInParallel(sc);
            testCase = testUtil.getExtentReports().createTest(testUtil.getScenarioName());
        }

        else {
            testCase = testUtil.getExtentReports().createTest(testUtil.getScenarioName());
        }

        testUtil.setExtentTest(testCase);
    }

    private synchronized void fetchScenarioNameInParallel(Scenario sc)
    {
        if(testUtil.getData("Parallel_Not").equalsIgnoreCase("Parallel"))
        {
            Random random = new Random();
            long delay = Math.abs(random.nextInt(6000));
            PathUtils.applySleep(delay);

            if (scenariosCounter.containsKey(sc.getName())) {
                scenariosCounter.put(sc.getName(), scenariosCounter.get(sc.getName()) + 1);

            } else {
                scenariosCounter.put(sc.getName(), 1);
            }

            System.out.println(scenariosCounter);

            testUtil.setScenarioName(getScenarioName(sc)+"_"+scenariosCounter.get(sc.getName()));
        }

        else
        {
            testUtil.setScenarioName(getScenarioName(sc) + "_" + counter);
            counter++;
        }

    }

    @After
    public void performAfter(Scenario sc) {
        //Prints whether the test case is passed or failed or skipped or undefined
        System.out.println(sc.getStatus());
//        System.out.println("Performing actions after each scenario");

        testUtil.getExtentReports().flush();


        if(testUtil.getPropertiesUtil().getBrowserClose().equalsIgnoreCase("true")) {
            testUtil.getDriver().quit();
            testUtil.setDriver(null);
        }
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

    @SneakyThrows
    public String getLanguage(Scenario sc) {
        return Files.lines(Paths.get(sc.getUri())).filter(s->s.startsWith("#language:"))
                .findFirst().orElseGet(() -> "");
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
