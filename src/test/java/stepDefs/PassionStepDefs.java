package stepDefs;

import business.passion.PassionBusiness;
import framework.ReusableLibrary;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PassionStepDefs extends ReusableLibrary
{
    PassionBusiness passionBusiness= new PassionBusiness();

    @Given("the Cashier successfully logs into the Passion application using credentials")
    public void theCashierSuccessfullyLogsIntoThePassionApplicationUsingCredentials() {
        passionBusiness.loginToApplication();
    }

    @And("User Fills the Pawn Application")
    public void userFillsThePawnApplication() {
        passionBusiness.fillPawnApplicationPage();
    }

    @When("Login to the Appraiser Application")
    public void loginToTheAppraiserApplication() {
        passionBusiness.loginToApplicationUsingSupervisorCredentials();
    }

    @Then("Approve the Pawn Application")
    public void approveThePawnApplication() {
        passionBusiness.approveTheRequest();
    }
}
