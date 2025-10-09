package business;

import actions.HomeActions;
import actions.bankManager.AddCustomerActions;
import actions.bankManager.BankManagerActions;
import actions.bankManager.OpenAccountActions;
import framework.ReusableLibrary;
import framework.constants.LogStatus;
import org.testng.Assert;

public class BankManagerBusiness extends ReusableLibrary {

    HomeActions homeActions = new HomeActions();
    BankManagerActions bankManagerBusiness = null;
    AddCustomerActions addCustomerActions = null;
    OpenAccountActions openAccountActions = null;

    public void navigateToBankManagerScreen()
    {
        bankManagerBusiness = homeActions.clickOnHome().clickOnBankManagerLogin();
    }

    public void navigateToAddCustomersScreen()
    {
        addCustomerActions = bankManagerBusiness.clickOnAddCustomers();
    }

    public void navigateToOpenCustomerScreen()
    {
        openAccountActions = bankManagerBusiness.clickOnOpenAccount();
    }

    public void createCustomer(String firstName,String lastName, String postalCode)
    {
        testUtil.setData("Customer Name",firstName+" "+lastName);

        String customerID=addCustomerActions.enterFirstName(firstName)
                .enterLastName(lastName)
                .enterPostCode(postalCode)
                .clickOnAddCustomer();

        testUtil.setData("Customer ID",customerID);
    }

    public void createAccounts(String currency)
    {
        String acccountID=openAccountActions
                .selectCustomer(testUtil.getData("Customer Name"))
                .selectCurrency(currency)
                .clickOnProcess();

        testUtil.setData("Account ID",acccountID);
    }

    public void validateCustomerDetails()
    {
        String data=testUtil.getData("Customer ID");
        Assert.assertTrue(data.contains("Customer added successfully"));

        reports.logReportsToTheFile(LogStatus.INFO,"Customer is created successfully for the customer id: "+testUtil.getData("Customer ID").split(":")[1]);
    }

    public void searchForCustomer()
    {
        homeActions.clickOnHome().clickOnBankManagerLogin()
                .clickOnCustomers()
                .enterDataIntoCustomer(testUtil.getData("Customer Name"));

    }

    public void validateAccountDetails()
    {
        String data=testUtil.getData("Account ID");
        Assert.assertTrue(data.contains("Account created successfully"));

        reports.logReportsToTheFile(LogStatus.INFO,"Account is created successfully for the customer id: "+testUtil.getData("Customer ID").split(":")[1]+" and the account number is: "+testUtil.getData("Account ID"));
    }
}
