package business.passion;

import framework.ReusableLibrary;
import pages.passion.AppraiserPage;
import pages.passion.LoginPage;
import pages.passion.PawnApplicationPage;

public class PassionBusiness extends ReusableLibrary {

    LoginPage loginPage=new LoginPage();
    PawnApplicationPage  pawnApplicationPage=new PawnApplicationPage();
    AppraiserPage appraiserPage=new AppraiserPage();
    String registrationNumber="";

    public void loginToApplication()
    {
        seleniumUtils.launchApplication(propertiesUtil.getURL());
        loginPage.enterUserName(propertiesUtil.getUserName())
                .enterPassword(propertiesUtil.getPassword())
                .clickLogin();
    }

    public void loginToApplicationUsingSupervisorCredentials()
    {
        loginPage.enterUserName(propertiesUtil.getSupervisorUserName())
                .enterPassword(propertiesUtil.getSuperVisorPassword())
                .clickLogin();
    }

    public void fillPawnApplicationPage()
    {
        registrationNumber=pawnApplicationPage.enterMenuID("41010")
                .clickOnFind()
                .enterCIFNumber("10000021218")
                .selectTerm("120 Hari")
                .selectSpecialRate("Ya")
                .selectTransactionPurpose("Usaha / Modal Kerja")
                .selectEconomicSector("Perdagangan")
                .selectCollateralRubic("Barang Elektronik")
                .selectBusinessOwnership("Tidak Memiliki Usaha")
                .clickOnAdd()
                .selectCollateralType("ARLOJI")
                .enterQuantity("1")
                .enterColour("HITAM")
                .enterBrand("ROLLEK")
                .enterMarketPrice("25000000")
                .enterDescription("NEW ARLOJI")
                .clickOnSaveDetails()
                .clickOnSave()
                .clickOnContinue()
                .clickOnOK().getRegistrationNumber();

        pawnApplicationPage.clickOnSaveData();

        loginPage.clickOnLogOut();
    }

    public void approveTheRequest()
    {
        appraiserPage.clickOnMyTasks()
                .selectRecord(registrationNumber)
                .clickOnApprove()
                .clickOnContinue();
    }
}
