package actions.bankManager;

import pages.bankManager.BankmanagerPage;

public class BankManagerActions extends BankmanagerPage {

    public AddCustomerActions clickOnAddCustomers()
    {
        seleniumUtils.clickOn(btn_AddCustomer,"Add Customer");
        return new AddCustomerActions();
    }

    public OpenAccountActions clickOnOpenAccount()
    {
        seleniumUtils.clickOn(btn_OpenAccount,"Open Account");
        return new OpenAccountActions();
    }

    public CustomerActions clickOnCustomers()
    {
        seleniumUtils.clickOn(btn_Customers,"Customers");
        return new CustomerActions();
    }

}