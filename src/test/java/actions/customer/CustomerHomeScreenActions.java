package actions.customer;

import pages.customer.CustomerHomeScreenPage;

public class CustomerHomeScreenActions extends CustomerHomeScreenPage {

    public CustomerHomeScreenActions selectCustomerName(String customerName)
    {
        seleniumUtils.selectOption(ddl_CustomerName,customerName,"Customer Name");
        return this;
    }

    public BankTransactionActions clickOnLogin()
    {
        seleniumUtils.clickOn(btn_Login,"Customer Login");
        return new BankTransactionActions();
    }

}