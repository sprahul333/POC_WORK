package pages.passion;

import framework.ReusableLibrary;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppraiserPage extends ReusableLibrary {

    By btn_MyTasks= By.xpath("//span[contains(text(),'TugasKu')]");

    By btn_SelectRecord(String record)
    {
        return By.xpath("//span[contains(text(),'"+record+"')]");
    }

    By btn_Approve=By.xpath("//span[text()='Transfer']");

    By btn_YesContinue=By.xpath("//span[text()='Yakin, Lanjutkan']");

    /*************************************************************************************************************************************/

    public AppraiserPage clickOnMyTasks()
    {
        seleniumUtils.clickOnElement(btn_MyTasks,5,"My Tasks");
        return this;
    }

    public AppraiserPage selectRecord(String referenceNumber)
    {
        seleniumUtils.clickOnElement(btn_SelectRecord(referenceNumber),5,"Select Record : "+referenceNumber);
        return this;
    }

    public AppraiserPage clickOnApprove()
    {
        seleniumUtils.clickOnElement(btn_Approve,5,"Approve");
        return this;
    }

    public AppraiserPage clickOnContinue()
    {
        seleniumUtils.clickOnElement(btn_YesContinue,5,"Continue");
        return this;
    }

}
