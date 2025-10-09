package pages.bankManager;

import framework.ReusableLibrary;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;

@FieldDefaults(level = AccessLevel.PROTECTED)
public class CustomerPage extends ReusableLibrary {

    By txt_Customer= By.xpath("//input[@placeholder='Search Customer']");
}