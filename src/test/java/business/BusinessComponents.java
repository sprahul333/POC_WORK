package business;

import framework.PathUtils;
import framework.ReusableLibrary;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import pages.*;

public class BusinessComponents extends ReusableLibrary {

    private LoginPage loginPage=new LoginPage();
    private HomePage homePage=new HomePage();
    private ApplicationData applicationData=new ApplicationData();
    private CustomerData customerData=new CustomerData();
    private CollateralData collateralData=new CollateralData();
    private ReviewData reviewData=new ReviewData();

    public void loginToApplication()
    {
        seleniumUtils.launchApplication(propertiesUtil.getURL());
        loginPage.enterUserName(propertiesUtil.getUserName());
        loginPage.enterPassword(propertiesUtil.getPassword());
        loginPage.clickOnLoginButton();
    }

    public void navigateToNonPawnApplication()
    {
        homePage.checkIfElementIsLoaded();
        homePage.clickOnNonPawnApplication();
    }

    public String reviewTermsAndConditionsAndData()
    {
        reviewData.clickOnSubmitApplication();
        reviewData.clickOnReview();
        reviewData.clickOnSave();

        System.out.println("Reference Number: "+reviewData.getReferenceNumber());
        return reviewData.getReferenceNumber();
    }

    public void fillApplicationData(String disbursementOutlet,String applicationPurpose,String loanPurpose,String category,String loanAmount,String product)
    {
        applicationData.enterDisbursementOutlet(disbursementOutlet);
        applicationData.selectApplicationPurpose(applicationPurpose);
        applicationData.selectLoanPurpose(loanPurpose);
        applicationData.selectCategory(category);
        applicationData.enterLoanAmount(loanAmount);
        applicationData.selectProduct(product);
        applicationData.clickOnContinue();
    }

    public void fillCustomerData(String idNumber,String customerName, String gender,String placeOfBirth,String pinCode,String subDistrict)
    {
        customerData.enterNationalIdentificationNumber(idNumber);
        customerData.enterFullName(customerName);
        customerData.selectGender(gender);
        customerData.enterPlaceOfBirth(placeOfBirth);
        customerData.selectDateOfBirth();
        customerData.enterMothersName("AUTOMATION NAME");
        customerData.enterPhoneNumber("081239871238");

        for(int i = 0; i<elementUtils.findElements(By.xpath("//div[contains(@class,'custom-file-upload') and not(@id)]")).size(); i++)
        {

            try {
                seleniumUtils.performMouseHover(driver.findElements(By.xpath("//div[contains(@class,'custom-file-upload') and not(@id)]")).get(i),"Capture Image");

                if(i==elementUtils.findElements(By.xpath("//div[contains(@class,'custom-file-upload') and not(@id)]")).size()-1)
                {
                    customerData.performSendKeysOnPhoneNumber();
                    driver.findElement(By.tagName("body")).sendKeys(Keys.ARROW_DOWN);
                }

                seleniumUtils.clickOnElement(driver.findElements(By.xpath("//div[contains(@class,'custom-file-upload') and not(@id)]")).get(i),"Capture Image");
            }


            catch (ElementClickInterceptedException e1)
            {
//                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)",driver.findElements(By.xpath("//div[contains(@class,'custom-file-upload') and not(@id)]")).get(i));
//                PathUtils.applySleep(2000);
////                    a1.sendKeys(Keys.PAGE_UP).build().perform();
//
//                int y=driver.findElements(By.xpath("//div[contains(@class,'custom-file-upload') and not(@id)]")).get(i).getLocation().getY();
//                ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+y+")");
//
//                a1.moveToElement(driver.findElements(By.xpath("//div[contains(@class,'custom-file-upload') and not(@id)]")).get(i)).build().perform();
                seleniumUtils.performWheelScroll(driver.findElements(By.xpath("//div[contains(@class,'custom-file-upload') and not(@id)]")).get(i));
                driver.findElements(By.xpath("//div[contains(@class,'custom-file-upload') and not(@id)]")).get(i).click();
            }

            catch(ElementNotInteractableException e4)
            {
                seleniumUtils.performWheelScroll(driver.findElements(By.xpath("//div[contains(@class,'custom-file-upload') and not(@id)]")).get(i));
                driver.findElements(By.xpath("//div[contains(@class,'custom-file-upload') and not(@id)]")).get(i).click();
            }

            seleniumUtils.clickOnElement(By.xpath("//div[@role='dialog' and  not(contains(@style,'none'))]/descendant::li[normalize-space(text())='Kamera']"),5,"Camera");
            seleniumUtils.clickOnElement(By.xpath("//img[@alt='Take Image']"),5,"Take Image");
            seleniumUtils.clickOnElement(By.xpath("//button[contains(@id,'cameraChoose')]"),5,"Camera Choose");

//            driver.findElement(By.xpath("//div[@role='dialog' and  not(contains(@style,'none'))]/descendant::li[normalize-space(text())='Kamera']")).click();
//            driver.findElement(By.xpath("//img[@alt='Take Image']")).click();
//            driver.findElement(By.xpath("//button[contains(@id,'cameraChoose')]")).click();

            PathUtils.applySleep(2000);
        }

        customerData.enterPinCode(pinCode);
        customerData.selectSubDistrict(subDistrict);
        customerData.enterNeighborhoodAssociation("222");
        customerData.enterCommunityAssociation("333");
        customerData.enterHomeAddress("Address New");
        customerData.clickOnCheckIndonesianPopulationAdministration();
        customerData.clickOnProceed();
        customerData.clickOnContinue();
    }

    public void enterCollateralData(String collateralCategory,String collateralType,String collateralCondition)
    {
        collateralData.clickOnAddCollateral();
        collateralData.selectCategory(collateralCategory);
        collateralData.selectCollateralType(collateralType);
        collateralData.selectCollateralCondition(collateralCondition);
        collateralData.enterBKPBNumber("BPKB00213023");
        collateralData.clickOnCheckBPKB();
        collateralData.clickOnProceed();

        collateralData.enterLicensePlateNumber("B1234ABC");
        collateralData.enterChassisNumber("12345678901234567");
        collateralData.enterFuelCapacity("50");
        collateralData.enterYearOfManufacture("2020");

        collateralData.clickOnSaveCollateral();
        collateralData.clickOnContinue();
    }
}
