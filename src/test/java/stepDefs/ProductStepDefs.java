package stepDefs;

import business.BusinessComponents;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductStepDefs {

    BusinessComponents businessComponents = new BusinessComponents();
    String referenceNumber="";

    @Given("the Account Officer is logged in")
    public void loginAsAccountOfficer() {
        businessComponents.loginToApplication();
    }

    @Given("the Account Officer is on the Non-Pawn Application page")
    public void navigateToNonPawnApplicationPage() {
        businessComponents.navigateToNonPawnApplication();
    }


    @And("the Account Officer fills in the application data with disbursement outlet {string}, application purpose {string},  loan purpose {string}, rubric {string}, loan amount {string}, and product {string}")
    public void theAccountOfficerFillsInTheApplicationDataWithDisbursementOutletApplicationPurposeLoanPurposeRubricLoanAmountAndProduct(String disbursementOutlet, String applicationPurpose,String loanPurpose, String rubric, String loanAmount, String product) {
        businessComponents.fillApplicationData(disbursementOutlet, applicationPurpose, loanPurpose, rubric, loanAmount, product);
    }

    @And("the Account Officer fills in the customer data with customer id {string}, customer name {string}, gender {string}, PlaceOfBirth {string}, PinCode {string}, SubDistrict {string} and verify from Dukcapil")
    public void theAccountOfficerFillsInTheCustomerDataWithCustomerIdCustomerNameGenderPlaceOfBirthPinCodeSubDistrictAndVerifyFromDukcapil(String customerID, String customerName, String gender, String placeOfBirth, String pinCode, String subDistrict) {
        businessComponents.fillCustomerData(customerID, customerName, gender, placeOfBirth, pinCode, subDistrict);
    }


    @And("the Account Officer adds One Collateral with Collateral Category {string}, Collateral Type {string}, Collateral Condition {string}")
    public void theAccountOfficerAddsNewVehicleWithCollateralCategoryCollateralTypeCollateralCondition(String collateralCategory, String collateralType, String collateralCondition) {
        businessComponents.enterCollateralData(collateralCategory, collateralType, collateralCondition);
    }

    @When("the Account Officer clicks submit for product {string}")
    public void clickSubmitProduct(String product) {
        referenceNumber=businessComponents.reviewTermsAndConditionsAndData();
    }

    @Then("the application is successful")
    public void verifyApplicationSuccess() {
        //Hit the api call
        System.out.println("Reference Number: "+referenceNumber);
    }

}
