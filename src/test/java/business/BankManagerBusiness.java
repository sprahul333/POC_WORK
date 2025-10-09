package business;

import actions.HomeActions;
import actions.bankManager.AddCustomerActions;
import actions.bankManager.BankManagerActions;
import framework.ReusableLibrary;
import org.testng.Assert;

public class BankManagerBusiness extends ReusableLibrary {

    HomeActions homeActions = new HomeActions();
    BankManagerActions bankManagerBusiness = null;
    AddCustomerActions addCustomerActions = null;

    public void navigateToBankManagerScreen()
    {
        bankManagerBusiness = homeActions.clickOnHome().clickOnBankManagerLogin();
    }

    public void navigateToAddCustomersScreen()
    {
        addCustomerActions = bankManagerBusiness.clickOnAddCustomers();
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
        String customerID=homeActions.clickOnHome().clickOnBankManagerLogin()
                .clickOnOpenAccount()
                .selectCustomer(testUtil.getData("Customer Name"))
                .selectCurrency(currency)
                .clickOnProcess();

        testUtil.setData("Customer ID",customerID);
    }

    public void validateCustomerDetails()
    {
        String data=testUtil.getData("Customer ID");
        Assert.assertTrue(data.contains("Customer added successfully"));
    }

    public void searchForCustomer()
    {
        homeActions.clickOnHome().clickOnBankManagerLogin()
                .clickOnCustomers()
                .enterDataIntoCustomer(testUtil.getData("Customer Name"));
    }

}
