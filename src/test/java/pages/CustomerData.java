package pages;

import framework.PathUtils;
import framework.ReusableLibrary;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.nio.file.Path;

public class CustomerData extends ReusableLibrary {

    private By txt_NationalIdentificationNumber= By.xpath("//input[@id='txt_NoKtp']");
    private By txt_FullName=By.xpath("//input[@id='txt_NamaLengkap']");

    private By btn_Gender=By.xpath("//button[@id='cbx_JenisKelamin']");
    private By option_Gender(String gender)
    {
        return By.xpath("//button[@id='cbx_JenisKelamin']/following-sibling::ul/descendant::li/descendant::div[normalize-space(text())='"+gender+"']");
    }

    private By txt_PlaceOfBirth=By.xpath("//input[@id='txt_TempatLahir']");

    private By btn_DateOfBirth=By.xpath("(//label[text()='Tanggal Lahir']/following-sibling::div[contains(@class,'input-group-icon')])[1]");
    private By btn_Year=By.xpath("//span[@class='month-year-text']");
    private By btn_Year2004=By.xpath("//div[@class='year']/descendant::button[text()='2004']");
    private By btn_Day11=By.xpath("//div[text()='11']");

    private By txt_MothersName=By.xpath("//input[@id='txt_NamaIbuKandung']");
    private By txt_PhoneNumber=By.xpath("//input[@id='txt_NomorHandphone']");

    private By btn_PinCode=By.xpath("//input[@placeholder='Masukkan kode pos']");

    private By btn_SubDistrict=By.xpath("//button[@id='cbx_Kelurahan']");
    private By option_SubDistrict(String option)
    {
        return By.xpath("//button[@id='cbx_Kelurahan']/following-sibling::ul/descendant::li/descendant::div[normalize-space(text())='"+option+"']");
    }

    private By txt_NeighborhoodAssociation=By.xpath("//input[@id='txt_Rt']");
    private By txt_CommunityAssociation=By.xpath("//input[@id='txt_rw']");

    private By txt_HomeAddress=By.id("txt_AlamatRumah");
    private By btn_CheckIndonesianPopulationAdministration=By.xpath("//span[text()='Cek Dukcapil']");

    private By btn_Proceed=By.xpath("(//div[text()='Data isian nasabah sudah sesuai dengan Dukcapil, silakan lanjutkan data pengajuan.']/following::button/descendant::span[text()='Mengerti'])[2]");
    private By btn_Continue=By.xpath("//span[text()='Selanjutnya']/ancestor::button");


    /***************************************************************************************************************************************************/

    public void enterNationalIdentificationNumber(String nationalIdentificationNumber)
    {
        PathUtils.applySleep(3000);
        seleniumUtils.enterData(txt_NationalIdentificationNumber,nationalIdentificationNumber,10,"National Identification Number");
    }

    public void enterFullName(String fullName)
    {
        seleniumUtils.enterData(txt_FullName,fullName,"Full Name");
    }

    public void selectGender(String gender)
    {
        seleniumUtils.clickOnElement(btn_Gender,"Gender");
        seleniumUtils.clickOnElement(option_Gender(gender),gender+" option");
    }

    public void enterPlaceOfBirth(String placeOfBirth)
    {
        seleniumUtils.enterData(txt_PlaceOfBirth,placeOfBirth,"Place of Birth");
    }

    public void selectDateOfBirth()
    {
        seleniumUtils.clickOnElement(btn_DateOfBirth,"Date of Birth");
        seleniumUtils.clickOnElement(btn_Year,"Year");
        seleniumUtils.clickOnElement(btn_Year2004,"Year 2004");
        seleniumUtils.clickOnElement(btn_Day11,"Day 11");
    }

    public void enterMothersName(String mothersName)
    {
        seleniumUtils.enterData(txt_MothersName,mothersName,"Mother's Name");
    }

    public void enterPhoneNumber(String phoneNumber)
    {
        seleniumUtils.enterData(txt_PhoneNumber,phoneNumber,"Phone Number");
    }

    public void performSendKeysOnPhoneNumber()
    {
        elementUtils.findElement(txt_PhoneNumber).sendKeys(Keys.DOWN);
    }

    public void enterPinCode(String pinCode)
    {
        seleniumUtils.enterData(btn_PinCode,pinCode,"Pin Code");
        PathUtils.applySleep(5000);
    }

    public void selectSubDistrict(String subDistrict)
    {
        jsFunctions.scrollToElement(elementUtils.findElement(btn_SubDistrict));
        PathUtils.applySleep(2000);
        seleniumUtils.clickOnElement(btn_SubDistrict,"Sub District");
        seleniumUtils.clickOnElement(option_SubDistrict(subDistrict),subDistrict+" option");
    }

    public void enterNeighborhoodAssociation(String neighborhoodAssociation)
    {
        seleniumUtils.enterData(txt_NeighborhoodAssociation,neighborhoodAssociation,"Neighborhood Association");
    }

    public void enterCommunityAssociation(String communityAssociation)
    {
        seleniumUtils.enterData(txt_CommunityAssociation,communityAssociation,"Community Association");
    }

    public void enterHomeAddress(String homeAddress)
    {
        seleniumUtils.enterData(txt_HomeAddress,homeAddress,"Home Address");
    }

    public void clickOnCheckIndonesianPopulationAdministration()
    {
        seleniumUtils.clickOnElement(btn_CheckIndonesianPopulationAdministration,"Check Indonesian Population Administration");
    }

    public void clickOnProceed()
    {
        seleniumUtils.clickOnElement(btn_Proceed,"Proceed");
    }

    public void clickOnContinue()
    {
        seleniumUtils.clickOnElement(btn_Continue,"Continue");
    }


}
