package pages;

import framework.PathUtils;
import framework.ReusableLibrary;
import org.openqa.selenium.By;

public class ApplicationData extends ReusableLibrary {

    private By btn_Disbursement_Outlet=By.xpath("//label[text()='Outlet Pencairan']/../following-sibling::div/button");
    private By txt_Disbursement_Outlet=By.xpath("//label[text()='Outlet Pencairan']/../following-sibling::div/ul/descendant::input");
    private By option_Disbursement_Outlet=By.xpath("//label[text()='Outlet Pencairan']/../following-sibling::div/ul/descendant::li");

    private By btn_Application_Purpose=By.xpath("//button[@id='cmb_TujuanPengajuan']");
    private By option_Application_Purpose(String purpose)
    {
        return By.xpath("//button[@id='cmb_TujuanPengajuan']/following-sibling::ul/descendant::li/descendant::div[normalize-space(text())='"+purpose+"']");
    }

    private By btn_Loan_Purpose=By.xpath("//button[@id='cmb_TujuanKredit']");
    private By option_Loan_Purpose(String purpose)
    {
        return By.xpath("//button[@id='cmb_TujuanKredit']/following-sibling::ul/descendant::li/descendant::div[normalize-space(text())='"+purpose+"']");
    }

    private By btn_Category=By.xpath("//button[@id='cmb_Rubrik']");
    private By option_Category(String category)
    {
        return By.xpath("//button[@id='cmb_Rubrik']/following-sibling::ul/descendant::li/descendant::div[normalize-space(text())='"+category+"']");
    }

    private By txt_LoanAmount=By.xpath("//input[@placeholder='Masukkan rupiah']");

    private By btn_Product=By.xpath("//button[@id='cmb_Produk']");
    private By option_Product(String product)
    {
        return By.xpath("//button[@id='cmb_Produk']/following-sibling::ul/descendant::li/descendant::div[normalize-space(text())='"+product+"']");
    }

    private By btn_Continue=By.xpath("//span[text()='Selanjutnya']/ancestor::button");
    /***************************************************************************************************************************************************/

    public void enterDisbursementOutlet(String outlet)
    {
        PathUtils.applySleep(8000);
        seleniumUtils.clickOnElement(btn_Disbursement_Outlet,"Disbursement Outlet");
        seleniumUtils.enterData(txt_Disbursement_Outlet,outlet,"Disbursement Outlet");
        seleniumUtils.clickOnElement(option_Disbursement_Outlet,outlet+" option");
    }

    public void selectApplicationPurpose(String purpose)
    {
        seleniumUtils.clickOnElement(btn_Application_Purpose,"Application Purpose");
        seleniumUtils.clickOnElement(option_Application_Purpose(purpose),purpose+" option");
    }

    public void selectLoanPurpose(String purpose)
    {
        seleniumUtils.clickOnElement(btn_Loan_Purpose,"Loan Purpose");
        seleniumUtils.clickOnElement(option_Loan_Purpose(purpose),purpose+" option");
    }

    public void selectCategory(String category)
    {
        seleniumUtils.clickOnElement(btn_Category,"Category");
        seleniumUtils.clickOnElement(option_Category(category),category+" option");
    }

    public void enterLoanAmount(String amount)
    {
        seleniumUtils.enterData(txt_LoanAmount,amount,"Loan Amount");
    }

    public void selectProduct(String product)
    {
        seleniumUtils.clickOnElement(btn_Product,"Product");
        seleniumUtils.clickOnElement(option_Product(product),product+" option");
    }

    public void clickOnContinue()
    {
        seleniumUtils.clickOnElement(btn_Continue,"Continue");
    }
}
