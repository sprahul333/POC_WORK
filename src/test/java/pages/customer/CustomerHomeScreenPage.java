package pages.customer;

import framework.ReusableLibrary;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class CustomerHomeScreenPage extends ReusableLibrary {

    By ddl_CustomerName= By.id("userSelect");
    By btn_Login=By.xpath("//button[text()='Login']");
}