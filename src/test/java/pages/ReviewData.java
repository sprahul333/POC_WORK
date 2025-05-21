package pages;

import framework.ReusableLibrary;
import org.openqa.selenium.By;

public class ReviewData extends ReusableLibrary {

    private By btn_SubmitApplication= By.xpath("//span[text()='Ajukan Pengajuan']/ancestor::button");

    //find Elements
    private By cbx_Review= By.xpath("//div[@class='checkbox-item']/descendant::label");
    private By btn_Save= By.xpath("//span[text()='Simpan']/ancestor::button");
    private By fld_ReferenceNumber=By.xpath("//div[contains(.,'Data sedang dalam pengajuan') and contains(@class,'confirmation-modal-body')]");

    /***************************************************************************************************************************************************/

    public void clickOnSubmitApplication()
    {
        seleniumUtils.clickOnElement(btn_SubmitApplication,"Submit Application");
    }

    public void clickOnReview()
    {
        seleniumUtils.clickOnElements(cbx_Review,10,"Accepting Terms and Conditions");
    }

    public void clickOnSave()
    {
        seleniumUtils.clickOnElement(btn_Save,"Save");
    }

    public String getReferenceNumber()
    {
        return seleniumUtils.getElementText(fld_ReferenceNumber,"Reference Number").split("pengajuan")[1].split("Tanggal")[0].trim().replaceAll("[^0-9]","");
    }
}
