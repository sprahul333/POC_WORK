package business;

import actions.HomeActions;
import framework.ReusableLibrary;
import pages.HomePage;

public class CustomerBusiness extends ReusableLibrary {

    HomeActions homeActions = new HomeActions();

    public void performDeposits(String amount)
    {
        homeActions.clickOnHome().clickOnCustomerLogin()
                .selectCustomerName(testUtil.getData("Customer Name"))
                .clickOnLogin()
                .clickOnDeposit()
                .enterAmount(amount)
                .clickOnDepositAmount();
    }


    public void performWithdrawal(String amount)
    {
        homeActions.clickOnHome().clickOnCustomerLogin()
                .selectCustomerName(testUtil.getData("Customer Name"))
                .clickOnLogin()
                .clickOnWithdrawal()
                .enterAmount(amount)
                .clickOnWithdrawAmount();
    }
}
