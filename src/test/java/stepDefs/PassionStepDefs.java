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

    @And("User Fills the Pawn Application for Customer ID {string} and Amount {string}")
    public void userFillsThePawnApplication(String customerID, String amount) {
        passionBusiness.fillPawnApplicationPage(customerID, amount);
    }

    @When("Login to the Appraiser Application")
    public void loginToTheAppraiserApplication() {
        passionBusiness.loginToApplicationUsingSupervisorCredentials();
    }

    @When("Login to the Cashier Application")
    public void loginToTheCashierApplication() {
        passionBusiness.loginToApplicationUsingCashierCredentials();
    }

    @Then("Approve the Pawn Application")
    public void approveThePawnApplication() {
        passionBusiness.approveTheRequest();
    }

    @Then("Approve the Pawn Application at the third level")
    public void approveThePawnApplicationAtThirdLevel() {
        passionBusiness.approveTheRequestAtThirdLevel();
    }

    @Then("Approve the Pawn Application using branch manager credentials")
    public void approveThePawnApplicationUsingBranchManager() {
        passionBusiness.approveTheRequestUsingBranchManager();
    }

    @When("Login to the Branch Manager Application")
    public void loginToTheBranchManagerApplication() {
        passionBusiness.loginToApplicationUsingBranchManagerCredentials();
    }

    @Then("Approve the Pawn Application for disbursement using SBG Number {string}")
    public void approveThePawnApplicationForDisbursementUsingSBGNumber(String sbgNumber)
    {
        passionBusiness.approveTheRequestAtCashierLevelForDisbursement(sbgNumber);
    }
}
