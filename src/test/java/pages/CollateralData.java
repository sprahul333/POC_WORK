package pages;

import framework.ReusableLibrary;
import org.openqa.selenium.By;

public class CollateralData extends ReusableLibrary {

    private By btn_AddCollateral=By.xpath("//span[text()='Tambah Jaminan']/ancestor::button");

    private By btn_Category=By.xpath("//button[@id='cbx_Rubrik']");
    private By option_Category(String option)
    {
        return By.xpath("//button[@id='cbx_Rubrik']/following-sibling::ul/descendant::li/descendant::div[normalize-space(text())='"+option+"']");
    }

    public By btn_CollateralType=By.xpath("//button[@id='cbx_TipeJaminan']");
    private By option_CollateralType(String option)
    {
        return By.xpath("//button[@id='cbx_TipeJaminan']/following-sibling::ul/descendant::li/descendant::div[normalize-space(text())='"+option+"']");
    }

    private By btn_CollateralCondition=By.xpath("//button[@id='cbx_kondisiJaminan']");
    private By option_CollateralCondition(String option) {
        return By.xpath("//button[@id='cbx_kondisiJaminan']/following-sibling::ul/descendant::li/descendant::div[normalize-space(text())='BEKAS']");
    }

    private By txt_BKPBNumber=By.xpath("//input[@id='txt_nomorBpkb']");
    private By btn_CheckBPKB=By.xpath("//span[text()='Cek BPKB']/ancestor::button");

    private By btn_Proceed=By.xpath("(//div[text()='Berhasil diverifikasi, silakan lanjutkan proses pengajuan.']/../following-sibling::div/descendant::button)[2]");

    private By txt_LicensePlateNumber=By.id("txt_noPolisi");
    private By txt_ChassisNumber=By.id("txt_noRangka");
    private By txt_FuelCapacity=By.id("txt_noMesin");

    private By txt_YearOfManufacture=By.id("txt_TahunPerakitan");

    private By btn_SaveCollateral=By.xpath("//button[@id='cbx_JaminanSimpan']");

    private By btn_Continue=By.xpath("//span[text()='Selanjutnya']/ancestor::button");


    /***************************************************************************************************************************************************/

    public void clickOnAddCollateral()
    {
        seleniumUtils.clickOnElement(btn_AddCollateral,"Add Collateral");
    }

    public void selectCategory(String category)
    {
        seleniumUtils.clickOnElement(btn_Category,"Category");
        seleniumUtils.clickOnElement(option_Category(category),category+" option");
    }

    public void selectCollateralType(String collateralType)
    {
        seleniumUtils.clickOnElement(btn_CollateralType,"Collateral Type");
        seleniumUtils.clickOnElement(option_CollateralType(collateralType),collateralType+" option");
    }

    public void selectCollateralCondition(String collateralCondition)
    {
        seleniumUtils.clickOnElement(btn_CollateralCondition,"Collateral Condition");
        seleniumUtils.clickOnElement(option_CollateralCondition(collateralCondition),collateralCondition+" option");
    }

    public void enterBKPBNumber(String bpkbNumber)
    {
        seleniumUtils.enterData(txt_BKPBNumber,bpkbNumber,"BPKB Number");
    }

    public void clickOnCheckBPKB()
    {
        seleniumUtils.clickOnElement(btn_CheckBPKB,"Check BPKB");
    }

    public void clickOnProceed()
    {
        seleniumUtils.clickOnElement(btn_Proceed,"Proceed");
    }

    public void enterLicensePlateNumber(String licensePlateNumber)
    {
        seleniumUtils.enterData(txt_LicensePlateNumber,licensePlateNumber,"License Plate Number");
    }

    public void enterChassisNumber(String chassisNumber)
    {
        seleniumUtils.enterData(txt_ChassisNumber,chassisNumber,"Chassis Number");
    }

    public void enterFuelCapacity(String fuelCapacity)
    {
        seleniumUtils.enterData(txt_FuelCapacity,fuelCapacity,"Fuel Capacity");
    }

    public void enterYearOfManufacture(String yearOfManufacture)
    {
        seleniumUtils.enterData(txt_YearOfManufacture,yearOfManufacture,"Year of Manufacture");
    }

    public void clickOnSaveCollateral()
    {
        seleniumUtils.clickOnElement(btn_SaveCollateral,"Save Collateral");
    }

    public void clickOnContinue()
    {
        seleniumUtils.clickOnElement(btn_Continue,"Continue");
    }

}
