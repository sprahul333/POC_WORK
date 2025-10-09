package actions;

import actions.bankManager.BankManagerActions;
import actions.customer.CustomerHomeScreenActions;
import pages.HomePage;

public class HomeActions extends HomePage {

    public HomeActions clickOnHome()
    {
        seleniumUtils.clickOn(btn_home,"Home");
        return this;
    }

    public CustomerHomeScreenActions clickOnCustomerLogin()
    {
        seleniumUtils.clickOn(btn_CustomerLogin,"Customer Login");
        return new CustomerHomeScreenActions();
    }

    public BankManagerActions clickOnBankManagerLogin()
    {
        seleniumUtils.clickOn(btn_BankManagerLogin,"Bank Manager Login");
        return new BankManagerActions();
    }

    public HomeActions clickOnLogout()
    {
        seleniumUtils.clickOn(btn_Logout,"Logout");
        return this;
    }

}