package pages.bankManager;

import framework.ReusableLibrary;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class OpenAccountPage extends ReusableLibrary {

    By ddl_CustomerName= By.id("userSelect");
    By ddl_Currency = By.id("currency");
    By btn_Process = By.xpath("//button[text()='Process']");
}