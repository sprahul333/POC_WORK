package business.passion;

import framework.ReusableLibrary;
import pages.passion.AppraiserPage;
import pages.passion.LoginPage;
import pages.passion.PawnApplicationPage;

public class PassionBusiness extends ReusableLibrary {

    LoginPage loginPage=new LoginPage();
    PawnApplicationPage  pawnApplicationPage=new PawnApplicationPage();
    AppraiserPage appraiserPage=new AppraiserPage();

    public void loginToApplication()
    {
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
        String registrationNumber=pawnApplicationPage.enterMenuID("41010")
                .clickOnFind()
                .enterCIFNumber("")
                .selectTerm("")
                .selectSpecialRate("")
                .selectTransactionPurpose("")
                .selectEconomicSector("")
                .selectCollateralRubic("")
                .selectBusinessOwnership("")
                .clickOnAdd()
                .selectCollateralType("")
                .enterQuantity("")
                .enterColour("")
                .enterMarketPlace("")
                .enterDescription("")
                .clickOnSaveDetails()
                .clickOnSave()
                .clickOnContinue()
                .clickOnOK().getRegistrationNumber();

        pawnApplicationPage.clickOnSaveData();
    }

    public void approveTheRequest()
    {
        appraiserPage.clickOnMyTasks()
                .selectRecord("")
                .clickOnApprove()
                .clickOnContinue();
    }
}
