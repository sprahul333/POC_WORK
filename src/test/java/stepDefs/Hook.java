package stepDefs;

import com.aventstack.extentreports.ExtentTest;
import framework.ExtentReportUtil;
import framework.Reports;
import framework.ReusableLibrary;
import framework.TestUtil;
import io.cucumber.core.backend.TestCaseState;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCase;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Hook {

    TestUtil testUtil=new TestUtil();
    private int currentStepDefIndex = 0;
    ExtentTest testCase;
    ExtentTest stepDef;

    @Before
    public void beforeScenario(Scenario scenario) {

        testUtil=Optional.ofNullable(ReusableLibrary.testUtilThreadLocal.get())
                .orElseGet(() -> {
                    testUtil.setReports(new Reports(testUtil));
                    return testUtil;
                });

        testUtil.setExtentReports(new ExtentReportUtil().getExtentReports());

        testUtil.setScenarioName(getScenarioName(scenario));
        testCase = testUtil.getExtentReports().createTest(testUtil.getScenarioName());
        testUtil.setExtentTest(testCase);

    }


    @BeforeStep
    public void performBeforeStep(Scenario sc) {
        testUtil.setCurrentStep(getCurrentStepText(sc));
        stepDef = testCase.createNode(getCurrentStepText(sc));
        testUtil.setExtentTest(stepDef);

        currentStepDefIndex++;
    }

    public String getScenarioName(Scenario sc) {
        //sc.getSourceTagNames() --> Will return a collection of strings
        //Collectors.joining() --> Joining the strings on the basis of '_' symbol
        return sc.getSourceTagNames().stream().collect(Collectors.joining("_")).replace("@", "");
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

    @After
    public void afterScenario(Scenario scenario) {

        System.out.println(scenario.getStatus());
//        System.out.println("Performing actions after each scenario");

        testUtil.getExtentReports().flush();

    }
}
