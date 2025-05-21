package stepDefs;

import business.BusinessComponents;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductStepDefs {

    BusinessComponents businessComponents = new BusinessComponents();
    String referenceNumber="";;
    @Given("the Account Officer is logged in")
    public void loginAsAccountOfficer() {
        businessComponents.loginToApplication();
    }

    @Given("the Account Officer is on the Non-Pawn Application page")
    public void navigateToNonPawnApplicationPage() {
        businessComponents.navigateToNonPawnApplication();
    }

    @And("the Account Officer fills in the application data with loan purpose {string}, rubric {string}, loan amount {string}, and product {string}")
    public void fillApplicationData(String loanPurpose, String rubric, String loanAmount, String product) {
        businessComponents.fillApplicationData("Outlet Name", "Loan Purpose", loanPurpose, rubric, loanAmount, product);
    }

    @And("the Account Officer fills in the customer data with {string} from Dukcapil")
    public void fillCustomerData(String customerId) {
        businessComponents.fillCustomerData("1234567890123456", "Customer Name","","","","");
    }

    @And("the Account Officer adds 1 new vehicle collateral {string}")
    public void addVehicleCollateral(String vehicleType) {
        businessComponents.enterCollateralData(vehicleType,"","");
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
