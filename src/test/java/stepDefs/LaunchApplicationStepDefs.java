package stepDefs;

import framework.ReusableLibrary;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.id.Dan;

public class LaunchApplicationStepDefs extends ReusableLibrary {
    @Given("Launch the Application")
    @Dan("Luncurkan Aplikasi")
    public void launchTheApplication() {
        seleniumUtils.launchApplication(propertiesUtil.getURL());
    }
}
