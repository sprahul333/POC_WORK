package pages.passion;

import framework.PathUtils;
import framework.ReusableLibrary;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AppraiserPage extends ReusableLibrary {

    By btn_MyTasks= By.xpath("//span[contains(text(),'TugasKu')]");

    By btn_SelectRecord(String record)
    {
        return By.xpath("//span[contains(text(),'"+record+"')]");
    }

    By btn_Simpan=By.xpath("//span[text()='Simpan']/..");

    By btn_Approve=By.xpath("//span[text()='Transfer']");

    By btn_YesContinue=By.xpath("//span[text()='Yakin, Lanjutkan']");

    By btn_KreditOK=By.xpath("//div[text()='Data Kredit membutuhkan Approval yang lebih tinggi, Silahkan lihat di menu Management Task!']/../../../descendant::span[text()='Ok']/..");

    By btn_OK=By.xpath("//span[text()='Ok']");

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

    public AppraiserPage clickOnSimpan()
    {
        PathUtils.applySleep(5000);
        jsFunctions.scrollToElement(elementUtils.findElement(btn_Simpan));
        seleniumUtils.clickOnElement(btn_Simpan,5,"Simpan");
        return this;
    }

    public AppraiserPage clickOnApprove()
    {
        jsFunctions.scrollToElement(elementUtils.findElement(btn_Approve));
        seleniumUtils.clickOnElement(btn_Approve,5,"Approve");
        return this;
    }

    public AppraiserPage clickOnContinue()
    {
        seleniumUtils.clickOnElement(btn_YesContinue,5,"Continue");
        return this;
    }

    public AppraiserPage clickOnOk()
    {
        seleniumUtils.clickOnElement(btn_OK,5,"OK");
        return this;
    }

    public AppraiserPage clickOnKreditOK()
    {
        seleniumUtils.clickOnElement(btn_KreditOK,5,"OK");
        return this;
    }

}
