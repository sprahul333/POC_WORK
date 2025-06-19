package business.passion;

import framework.ReusableLibrary;
import framework.constants.LogStatus;
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
        seleniumUtils.launchApplication(propertiesUtil.getURL());
        loginPage.enterUserName(propertiesUtil.getSupervisorUserName())
                .enterPassword(propertiesUtil.getSuperVisorPassword())
                .clickLogin();
    }

    public void loginToApplicationUsingCashierCredentials()
    {
        seleniumUtils.launchApplication(propertiesUtil.getURL());
        loginPage.enterUserName(propertiesUtil.getCashierUserName())
                .enterPassword(propertiesUtil.getCashierPassword())
                .clickLogin();
    }

    public void loginToApplicationUsingBranchManagerCredentials()
    {
        seleniumUtils.launchApplication(propertiesUtil.getURL());
        loginPage.enterUserName(propertiesUtil.getBranchUserName())
                .enterPassword(propertiesUtil.getBranchPassword())
                .clickLogin();
    }

    public void fillPawnApplicationPage(String customerID, String amount)
    {
        registrationNumber=pawnApplicationPage.enterMenuID("41010")
                .clickOnFind()
                .enterCIFNumber(customerID)
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
                .enterMarketPrice(amount)
                .enterDescription("NEW ARLOJI")
                .clickOnSaveDetails()
                .clickOnSave()
                .clickOnContinue()
                .clickOnOK().getRegistrationNumber();

        reports.logReportsToTheFile(LogStatus.INFO,"Registration Number generated is: "+registrationNumber);
        pawnApplicationPage.clickOnSaveData();

        loginPage.clickOnLogOut();
    }

    public void approveTheRequest()
    {
        registrationNumber=appraiserPage.clickOnMyTasks()
                .selectRecord(registrationNumber)
                .clickOnSimpan()
                .clickOnContinue()
                .clickOnKreditOK()
                .getReferenceNumber();

        appraiserPage.clickOnOk();
        loginPage.clickOnLogOut();

    }

    public void approveTheRequestUsingBranchManager()
    {
        registrationNumber=appraiserPage.clickOnMyTasks()
                .selectRecord(registrationNumber)
                .clickOnSimpan()
                .clickOnContinue()
                .clickOnDataSavedSuccessfully()
                        .getReferenceNumber();

        appraiserPage.clickOnOk();

        loginPage.clickOnLogOut();
    }

    public void approveTheRequest_BranchApproval()
    {
        registrationNumber=appraiserPage.clickOnMyTasks()
                .selectRecord(registrationNumber)
                .clickOnSimpan()
                .clickOnContinue()
                .clickOnKreditOK()
                .getReferenceNumber();

        appraiserPage.clickOnOk();

    }
}
