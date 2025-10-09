package actions.customer;

import pages.customer.BankTransactionPage;

public class BankTransactionActions extends BankTransactionPage {

    public BankTransactionActions clickOnTransactions()
    {
        seleniumUtils.clickOn(btn_Transactions,"Transactions");
        return this;
    }

    public BankTransactionActions clickOnDeposit()
    {
        seleniumUtils.clickOn(btn_Deposit,"Deposit");
        return this;
    }

    public BankTransactionActions clickOnWithdrawal()
    {
        seleniumUtils.clickOn(btn_Withdrawal,"Withdrawal");
        return this;
    }

    public BankTransactionActions clickOnDepositAmount()
    {
        seleniumUtils.clickOn(btn_DepositAmount,"Deposit Amount");
        return this;
    }

    public BankTransactionActions clickOnWithdrawAmount()
    {
        seleniumUtils.clickOn(btn_WithdrawAmount,"Withdraw Amount");
        return this;
    }

    public BankTransactionActions enterAmount(String amount)
    {
        seleniumUtils.typeOn(txt_Amount,amount,"Amount");
        return this;
    }

}