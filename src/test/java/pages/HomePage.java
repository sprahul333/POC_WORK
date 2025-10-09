package pages;

import framework.ReusableLibrary;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class HomePage extends ReusableLibrary {

    By btn_home= By.xpath("//button[text()='Home']");
    By btn_CustomerLogin = By.xpath("//button[text()='Customer Login']");
    By btn_BankManagerLogin = By.xpath("//button[text()='Bank Manager Login']");
    By btn_Logout=By.xpath("//button[text()='Logout']");
}