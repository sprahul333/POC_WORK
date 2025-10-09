package pages.bankManager;

import framework.ReusableLibrary;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class BankmanagerPage extends ReusableLibrary {

    By btn_AddCustomer= By.xpath("//button[normalize-space(text())='Add Customer']");
    By btn_OpenAccount = By.xpath("//button[normalize-space(text())='Open Account']");
    By btn_Customers = By.xpath("//button[normalize-space(text())='Customers']");
}