package pages.passion;

import framework.ReusableLibrary;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginPage extends ReusableLibrary {

    By txt_UserName= By.id("txt_NIK");
    By txt_Password=By.xpath("//input[@Id='txt_katasandi']");
    By btn_login=By.xpath("//span[text()='Login']");
    By btn_Logout=By.xpath("//span[text()='Keluar']");
    /************************************************************************************************************************/

    public LoginPage enterUserName(String userName)
    {
        seleniumUtils.enterData(txt_UserName,userName,5,"User Name");
        return this;
    }

    public LoginPage enterPassword(String password)
    {
        seleniumUtils.enterData(txt_Password,password,5,"Password");
        return this;
    }

    public LoginPage clickLogin()
    {
        seleniumUtils.clickOnElement(btn_login,5,"Login");
        return this;
    }

    public LoginPage clickOnLogOut()
    {
        seleniumUtils.clickOnElement(btn_Logout,5,"Logout");
        return this;
    }


}
