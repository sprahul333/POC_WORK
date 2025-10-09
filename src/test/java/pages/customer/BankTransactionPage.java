package pages.customer;

import framework.ReusableLibrary;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class BankTransactionPage extends ReusableLibrary {

    By btn_Transactions = By.xpath("//button[normalize-space(text())='Transactions']");
    By btn_Deposit =  By.xpath("//button[normalize-space(text())='Deposit']");
    By btn_Withdrawal =  By.xpath("//button[normalize-space(text())='Withdrawl']");
    By txt_Amount=By.xpath("//input[@placeholder='amount']");
    By btn_DepositAmount = By.xpath("//button[text()='Deposit']");
    By btn_WithdrawAmount = By.xpath("//button[text()='Withdraw']");
}