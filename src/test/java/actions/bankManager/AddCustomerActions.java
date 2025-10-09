package actions.bankManager;

import pages.bankManager.AddCustomerPage;

public class AddCustomerActions extends AddCustomerPage {

    public AddCustomerActions enterFirstName(String firstName) {
        seleniumUtils.typeOn(txt_FirstName,"First Name",firstName);
        return this;
    }

    public AddCustomerActions enterLastName(String lastName) {
        seleniumUtils.typeOn(txt_LastName,"Last Name",lastName);
        return this;
    }

    public AddCustomerActions enterPostCode(String postCode) {
        seleniumUtils.typeOn(txt_PostCode,"Post Code",postCode);
        return this;
    }

    public String clickOnAddCustomer()
    {
        String msg = seleniumUtils.clickOn(btn_AddCustomer,"Add Customer")
                .getAlertText();

        seleniumUtils.acceptAlert();

        return msg;
    }

}