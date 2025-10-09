package actions.bankManager;

import pages.bankManager.CustomerPage;

public class CustomerActions extends CustomerPage {

    public CustomerPage enterDataIntoCustomer(String customerName)
    {
        seleniumUtils.typeOn(txt_Customer,"Customer Name",customerName);
        return this;
    }

}