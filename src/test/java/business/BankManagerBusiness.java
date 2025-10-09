package business;

import actions.HomeActions;
import framework.ReusableLibrary;

public class BankManagerBusiness extends ReusableLibrary {

    HomeActions homeActions = new HomeActions();

    public void createCustomer(String firstName,String lastName, String postalCode)
    {
        testUtil.setData("Customer Name",firstName+" "+lastName);

        String customerID=homeActions.clickOnHome().clickOnBankManagerLogin()
                .clickOnAddCustomers()
                .enterFirstName(firstName)
                .enterLastName(lastName)
                .enterPostCode(postalCode)
                .clickOnAddCustomer();

        System.out.println(customerID);
    }

    public void createAccounts(String currency)
    {
        String customerID=homeActions.clickOnHome().clickOnBankManagerLogin()
                .clickOnOpenAccount()
                .selectCustomer(testUtil.getData("Customer Name"))
                .selectCurrency(currency)
                .clickOnProcess();

        System.out.println(customerID);
    }

    public void searchForCustomer()
    {
        homeActions.clickOnHome().clickOnBankManagerLogin()
                .clickOnCustomers()
                .enterDataIntoCustomer(testUtil.getData("Customer Name"));
    }

}
