package pages.others;

import framework.ReusableLibrary;
import org.openqa.selenium.By;

public class LoginPage extends ReusableLibrary {

    private By txt_UserName= By.xpath("//input[@placeholder='Username HCMS / Aralia']");
    private By txt_Password= By.xpath("//input[@placeholder='Password']");
    private By btn_Login= By.xpath("//button[text()='Masuk']");

    /*******************************************************************************************************************************/


    public void enterUserName(String userName)
    {
        seleniumUtils.enterData(txt_UserName, userName,10,"User Name");
    }

    public void enterPassword(String password)
    {
        seleniumUtils.enterData(txt_Password, password,"Password");
    }

    public void clickOnLoginButton()
    {
        seleniumUtils.clickOnElement(btn_Login,"Login Button");
    }


}
