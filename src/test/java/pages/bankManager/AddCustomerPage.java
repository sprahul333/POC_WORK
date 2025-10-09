package pages.bankManager;

import framework.ReusableLibrary;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class AddCustomerPage extends ReusableLibrary {

    By txt_FirstName = By.xpath("//input[@placeholder='First Name']");
    By txt_LastName = By.xpath("//input[@placeholder='Last Name']");
    By txt_PostCode = By.xpath("//input[@placeholder='Post Code']");
    By btn_AddCustomer = By.xpath("//button[text()='Add Customer']");
}