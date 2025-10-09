package actions.bankManager;

import pages.bankManager.OpenAccountPage;

public class OpenAccountActions extends OpenAccountPage {

    public OpenAccountActions selectCustomer(String customerName)
    {
        seleniumUtils.selectOption(ddl_CustomerName,customerName,"Customer Name");
        return this;
    }

    public OpenAccountActions selectCurrency(String currency)
    {
        seleniumUtils.selectOption(ddl_Currency,currency,"Currency");
        return this;
    }

    public String clickOnProcess()
    {
        String msg=seleniumUtils.clickOn(btn_Process,"Process")
                .getAlertText();

        seleniumUtils.acceptAlert();
        return msg;
    }

}