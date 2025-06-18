package pages.passion;

import framework.PathUtils;
import framework.ReusableLibrary;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class PawnApplicationPage extends ReusableLibrary {

    By txt_MenuCode= By.xpath("//input[@id='txt_menu']");
    By btn_Find=By.xpath("//span[text()='Cari']");

    By txt_CIFNumber=By.xpath("//input[@id='txt_noCIF']");
    By ddl_Term=By.xpath("//div[@id='cmb_jangkaWaktu']/select");
    By ddl_SpecialRate=By.xpath("//div[@id='cmb_tarifKhusus']/select");

    By ddl_TransactionPurpose=By.xpath("//div[@id='cmb_tujuanTransaksi']/select");
    By ddl_EconomicSector=By.xpath("//div[@id='cmb_sektorEkonomi']/select");
    By ddl_CollateralRubic=By.xpath("//div[@id='cmb_rubikJaminan']/select");
    By ddl_BusinessOwnership=By.xpath("//div[@id='cmb_kepemilikanUsaha']/select");

    By btn_Add=By.xpath("(//span[text()='Tambah'])[2]");

    By ddl_CollateralType=By.xpath("//div[@id='cmb_tipeJaminan']/select");
    By txt_Quantity=By.xpath("//input[@id='txt_Jumlah']");
    By txt_Color=By.xpath("//input[@id='txt_Warna']");

    By txt_Brand=By.xpath("//input[@id='txt_Merek']");

    By txt_MarketPrice=By.xpath("//input[@id='txt_HargaPasar']");

    By txt_Description=By.xpath("//textarea[@id='txt_Keterangan']");
    By btn_SaveDetails=By.xpath("(//span[text()='Simpan'])[2]");

    By btn_Save=By.xpath("//span[text()='Simpan']");
    By btn_Continue=By.xpath("//span[text()='Yakin, Lanjutkan']");

    By btn_OK_RequiresAppraiserVerification=By.xpath("//div[text()='Data Pinjaman Membutuhkan Verifikasi Penaksir, Silahkan Lihat Pada Tugasku Penaksir']/../../../descendant::span[text()='Ok']");

    By fld_RegistrationNumber=By.xpath("//div[contains(text(),'Data berhasil disimpan')]");
    By btn_Ok_SaveData=By.xpath("//div[contains(text(),'Data berhasil disimpan')]/../../../descendant::span[text()='Ok']");

    /****************************************************************************************************************************************************************/

    public PawnApplicationPage enterMenuID(String menuID)
    {
        seleniumUtils.enterData(txt_MenuCode,menuID,5,"Menu ID");
        seleniumUtils.sendKeys(txt_MenuCode, Keys.ENTER);
        return this;
    }

    public PawnApplicationPage clickOnFind()
    {
        seleniumUtils.clickOnElement(btn_Find,5,"Find");
        return this;
    }

    public PawnApplicationPage enterCIFNumber(String cifNumber)
    {
        seleniumUtils.enterData(txt_CIFNumber,cifNumber,5,"CIF Number");
        seleniumUtils.sendKeys(txt_CIFNumber,Keys.ENTER);
        PathUtils.applySleep(5000);
        driver.findElement(By.xpath("//div[text()='Nasabah adalah Penerima Subsidi SM, lakukan pengecekan pada Menu Inquiry Pinjaman Transaksi Subsidi']/../../../descendant::span[text()='Ok']/..")).click();
        return this;
    }

    public PawnApplicationPage selectTerm(String term)
    {
        seleniumUtils.selectValueFromDropDown(ddl_Term,term,5,"Term");
        return this;
    }

    public PawnApplicationPage selectSpecialRate(String specialRate)
    {
        seleniumUtils.selectValueFromDropDown(ddl_SpecialRate,specialRate,5,"Special Rate");
        return this;
    }

    public PawnApplicationPage selectTransactionPurpose(String purpose)
    {
        seleniumUtils.selectValueFromDropDown(ddl_TransactionPurpose,purpose,5,"Transaction Purpose");
        return this;
    }

    public PawnApplicationPage selectEconomicSector(String economicSector)
    {
        seleniumUtils.selectValueFromDropDown(ddl_EconomicSector,economicSector,5,"Economic Sector");
        return this;
    }

    public PawnApplicationPage selectCollateralRubic(String collateralRubic)
    {
        seleniumUtils.selectValueFromDropDown(ddl_CollateralRubic,collateralRubic,5,"Collateral Rubic");
        return this;
    }

    public PawnApplicationPage selectBusinessOwnership(String businessOwnership)
    {
        seleniumUtils.selectValueFromDropDown(ddl_BusinessOwnership,businessOwnership,5,"Business Ownership");
        return this;
    }

    public PawnApplicationPage clickOnAdd()
    {
        seleniumUtils.clickOnElement(btn_Add,5,"Add");
        return this;
    }

    public PawnApplicationPage selectCollateralType(String collateralType)
    {
        seleniumUtils.selectValueFromDropDown(ddl_CollateralType,collateralType,5,"Collateral Type");
        return this;
    }

    public PawnApplicationPage enterQuantity(String quantity)
    {
        seleniumUtils.enterData(txt_Quantity,quantity,5,"Quantity");
        return this;
    }

    public PawnApplicationPage enterColour(String colour)
    {
        seleniumUtils.enterData(txt_Color,colour,5,"Colour");
        return this;
    }

    public PawnApplicationPage enterBrand(String brand)
    {
        seleniumUtils.enterData(txt_Brand,brand,5,"Brand");
        return this;
    }

    public PawnApplicationPage enterMarketPrice(String marketPlace)
    {
        seleniumUtils.enterData(txt_MarketPrice,marketPlace,5,"Market Place");
        seleniumUtils.sendKeys(txt_MarketPrice,Keys.ENTER);
        return this;
    }

    public PawnApplicationPage enterDescription(String description)
    {
        seleniumUtils.enterData(txt_Description,description,5,"Description");
        return this;
    }

    public PawnApplicationPage clickOnSaveDetails()
    {
        seleniumUtils.clickOnElement(btn_SaveDetails,5,"Save");
        return this;
    }

    public PawnApplicationPage clickOnSave()
    {
        seleniumUtils.clickOnElement(btn_Save,5,"Save");
        return this;
    }

    public PawnApplicationPage clickOnContinue()
    {
        seleniumUtils.clickOnElement(btn_Continue,5,"Continue");
        return this;
    }

    public PawnApplicationPage clickOnOK()
    {
        seleniumUtils.clickOnElement(btn_OK_RequiresAppraiserVerification,5,"OK");
        return this;
    }

    public String getRegistrationNumber()
    {
        return seleniumUtils.getElementText(fld_RegistrationNumber,5,"Registration Number").split(":")[1].trim();
    }

    public PawnApplicationPage clickOnSaveData()
    {
        seleniumUtils.clickOnElement(btn_Ok_SaveData,5,"Save");
        return this;
    }

}
