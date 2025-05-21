package stepDefs;

import business.BusinessAPI;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Api_Step_Defs
{
    BusinessAPI businessAPI =new BusinessAPI();

    @Given("User Logins to the Application")
    public void userLoginsToTheApplication() {
        businessAPI.loginToAPI();
    }

    @When("I Make An Enquiry with the following details")
    public void iMakeAnEnquiryWithTheFollowingDetails(DataTable dataTable)
    {
        businessAPI.makeAnInquiry(dataTable);
    }

    @And("I Create a VA for the below details")
    public void iCreateAVAForTheBelowDetails(DataTable dataTable) {
        businessAPI.createVA(dataTable);
    }

    @And("I Also Inquire for the Payments with the help of below details")
    public void iAlsoInquireForThePaymentsWithTheHelpOfBelowDetails(DataTable dataTable) {
        businessAPI.inquirePayment(dataTable);
    }

    @Then("I Make the Payment using the given transaction details")
    public void iMakeThePaymentUsingTheGivenTransactionDetails(DataTable dataTable) {
        businessAPI.makePayment(dataTable);
    }
}
