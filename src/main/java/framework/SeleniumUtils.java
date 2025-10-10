package framework;

import framework.constants.LogStatus;
import lombok.AllArgsConstructor;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@AllArgsConstructor //Based on the variables declared, it will create a parameterized constructor during the execution time
public class SeleniumUtils {

    WebDriver driver;
    ElementUtils elementUtils;
    Reports reports;
    JSFunctions jsFunctions;
    Actions actions;

    public SeleniumUtils clickOn(By by, String... labelName)
    {
        try {
            WebElement element = elementUtils.findElement(by,10,labelName);

            Optional.ofNullable(element).orElseThrow(() -> new GenericExceptions("Unable to find the element for " + labelName));

            jsFunctions.higlightElement(element);
            PathUtils.applySleep(500);
            reports.captureScreenshots();

            if(labelName.length>0)
                reports.logReportsToTheFile(LogStatus.INFO_SCREENSHOT,"Clicked on: <b>"+labelName[0]+"</b>");
            else
                reports.logReportsToTheFile(LogStatus.INFO_SCREENSHOT,"Clicked on the element");

            jsFunctions.disableHighlight(element);
            performMouseHover(by,labelName);
            element.click();
        }

        catch (ElementNotInteractableException e2)
        {
            if(labelName.length>0)
                throw new GenericExceptions("Element is not interactable for " + labelName+" please check it");
            else
                throw new GenericExceptions("Element is not interactable for the element please check it");

        }

        catch (StaleElementReferenceException e1)
        {
            if(labelName.length>0)
                throw new GenericExceptions("Element is stale for the  " + labelName+" please check it");
            else
                throw new GenericExceptions("Element is stale for the element please check it");
        }

        return this;
    }

    public SeleniumUtils clickOnElements(By by, int time, String... labelName)
    {
        try {
            List<WebElement> element = elementUtils.findElements(by, time);

            if(element.isEmpty())
            {
                if(labelName.length>0)
                    throw new GenericExceptions("Unable to find the element for " + labelName);
                else
                    throw new GenericExceptions("Unable to find the elements");

            }

            element.stream().forEach(e ->
            {
                jsFunctions.higlightElement(e);
                reports.captureScreenshots();
                jsFunctions.disableHighlight(e);

                performMouseHover(e);
                jsFunctions.scrollToElement(e);

                e.click();

            });
//            Optional.ofNullable(element).orElseThrow(() -> new GenericExceptions("Unable to find the element for " + labelName));


//            performMouseHover(element);

//            element.click();
            reports.logReportsToTheFile(LogStatus.INFO_SCREENSHOT,"Clicked on: <b>"+labelName+"</b>");
        }

        catch (ElementNotInteractableException e2)
        {
            throw new GenericExceptions("Element is not interactable for " + labelName+" please check it");
        }

        catch (StaleElementReferenceException e1)
        {
            throw new GenericExceptions("Element is stale for the  " + labelName+" please check it");
        }

        return this;
    }

    public String getTextBoxAttribute(By by, String... labelName)
    {
        WebElement element = elementUtils.findElement(by,labelName);
        Optional.ofNullable(element).orElseThrow(() -> new GenericExceptions("Unable to find the element for " + labelName));

        return Optional.ofNullable(element.getDomAttribute("value")).orElseGet(()->element.getDomProperty("value"));
    }

    public SeleniumUtils sendKeys(By by, Keys keys)
    {
        WebElement element = elementUtils.findElement(by);
        element.sendKeys(keys);
        return this;
    }

    public void typeOn(By by,String data,String... labelName)
    {
        WebElement element=elementUtils.findElement(by,10,labelName);

        Optional.ofNullable(element).orElseThrow(() -> new GenericExceptions("Unable to find the element for <b>" + labelName+"</b>"));

        element.clear();
        String existingData=getTextBoxAttribute(by,labelName);
        if(!(existingData.isEmpty() || existingData.isBlank()))
        {
            for(int i=0;i<existingData.length();i++)
            {
                element.sendKeys(Keys.BACK_SPACE);
            }
        }

        jsFunctions.higlightElement(element);

        element.sendKeys(data);
        
        if(labelName.length>0)
            reports.logReportsToTheFile(LogStatus.INFO_SCREENSHOT,"Entered Data for: <b>"+labelName+"</b> is: <b>"+data+"</b>");
        else
            reports.logReportsToTheFile(LogStatus.INFO_SCREENSHOT,"Entered Data for <b>"+data+"</b>");
        jsFunctions.disableHighlight(element);

    }

    public void closeRespectiveTabOrWindow(String urlOrTitle) throws InterruptedException {
        Set<String> handles = driver.getWindowHandles();

        for (String handle : handles) {
            driver.switchTo().window(handle);

            if (driver.getCurrentUrl().contains(urlOrTitle) || driver.getTitle().contains(urlOrTitle)) {
                driver.close();
            }

            Thread.sleep(1000);
        }
    }

    public SeleniumUtils switchToAllTheTabsAndWindows() throws InterruptedException {
        Set<String> handles = driver.getWindowHandles();

        for (String handle : handles) {
            driver.switchTo().window(handle);
            Thread.sleep(1000);
        }

        return this;
    }

    public String getCurrentURL()
    {
        return driver.getCurrentUrl();
    }

    public String launchApplication(String url)
    {
        if(url.isBlank() || url.isEmpty())
            throw new GenericExceptions("Given URL is empty or blank");

//        else if(!url.contains("https"))
//            throw new GenericExceptions("Given URL does not contain https:");
//
//        else if(!url.startsWith("https"))
//            throw new GenericExceptions("Given URL does not start with https:");

        driver.get(url);

        if(new PropertiesUtil().getResolutionSize().isBlank())
        driver.manage().window().maximize();

        driver.manage().timeouts().pageLoadTimeout(Duration.ofMinutes(10));

        reports.logReportsToTheFile(LogStatus.INFO_SCREENSHOT,"Launching the application for the URL: <a>"+url+"</a>");
//        reports.logReportsToTheFile(LogStatus.INFO_SCREENSHOT,"Launched the application successfully for: "+url);
        return driver.getWindowHandle();
    }

    public String createNewTabAndLaunchApplication(String url) {
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(url);

        return driver.getWindowHandle();
    }


    public String createNewWindowAndLaunchApplication(String url) {
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(url);

        return driver.getWindowHandle();
    }

    public void checkIfElementIsLoaded(By by,String... labelName)
    {
        while (elementUtils.findElements(by).size()==0)
        {
            PathUtils.applySleep(10000);
        }

        if(labelName.length>0)
            reports.logReportsToTheFile(LogStatus.INFO_SCREENSHOT,"Element is loaded for: "+labelName);
        else
            reports.logReportsToTheFile(LogStatus.INFO_SCREENSHOT,"Element is loaded");
    }

    public Optional<Alert> checkIfAlertIsPresent(int sec)
    {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(sec));

            //Optional.ofNullable --> Check if the given object is returning null or not
            //The whole agenda of optionals is to avoid null pointer exceptions
            return Optional.ofNullable(wait.until(ExpectedConditions.alertIsPresent()));
        }

        catch (TimeoutException e5)
        {
            throw new GenericExceptions("Unable to find the browser alert after waiting for: "+sec+" seconds");
        }
    }

    public void acceptAlert()
    {
        checkIfAlertIsPresent(3).ifPresentOrElse(alert -> alert.accept(),()->{
            throw new GenericExceptions("Alert is not present");
        });
    }

    public void dismissAlert()
    {
        checkIfAlertIsPresent(3).ifPresentOrElse(alert -> alert.dismiss(),()->{
            throw new GenericExceptions("Alert is not present");
        });
    }

    public SeleniumUtils enterDataInAlert(String data)
    {
        checkIfAlertIsPresent(3).ifPresentOrElse(alert -> {
            alert.sendKeys(data);
            alert.accept();
        },()->{
            throw new GenericExceptions("Alert is not present");
        });

        return this;
    }

    public String getAlertText()
    {
        //If the alert is present, then generate the text of the alert using .map() method
        return checkIfAlertIsPresent(3).map(Alert::getText)
                .orElseThrow(()->new GenericExceptions("Alert is not present"));
    }

    public SeleniumUtils performMouseHover(By by,String... labelName)
    {
        WebElement element=elementUtils.findElement(by,10,labelName);
        actions.moveToElement(element).build().perform();
        return this;
    }

    public SeleniumUtils performMouseHover(WebElement element,String... labelName)
    {
        actions.moveToElement(element).build().perform();
        return this;
    }

    public SeleniumUtils performDragAndDrop(By sourcePath, By destPath)
    {
        WebElement source=elementUtils.findElement(sourcePath,10,"Source");
        WebElement destination=elementUtils.findElement(destPath,10,"Destination");

        actions.dragAndDrop(source,destination).build().perform();

        return this;
    }

    public SeleniumUtils performRightClick(By by,String... labelName)
    {
        WebElement element=elementUtils.findElement(by,10,labelName);
        actions.contextClick(element).build().perform();
        return this;
    }

    public void performDoubleClick(By by,String... labelName)
    {
        WebElement element=elementUtils.findElement(by,10,labelName);
        actions.doubleClick(element).build().perform();
    }

    public String getSelectedOption(By by)
    {
        WebElement element=elementUtils.findElement(by);
        Select s1=new Select(element);
        return s1.getFirstSelectedOption().getText();
    }

    public SeleniumUtils selectOption(By by, String option, String... labelName)
    {
        WebElement element=elementUtils.findElement(by,10,labelName);
        Select s1=new Select(element);
        if(option.isBlank() || option.isEmpty())
        {
            List<WebElement> options=s1.getOptions();
            //ThreadLocalRandom.current().nextInt(0, options.size()-1) --> This is a function we select a random number between 0, options.size()-1
            s1.selectByIndex(ThreadLocalRandom.current().nextInt(0, options.size()-1));
        }

        else
        {
            try
            {
                s1.selectByVisibleText(option);
            }

            catch (NoSuchElementException r5)
            {
                try
                {
                    s1.selectByContainsVisibleText(option);
                }

                catch (NoSuchElementException r6)
                {
                    try
                    {
                        s1.selectByValue(option);
                    }

                    catch (NoSuchElementException r7)
                    {
                        try
                        {
                            s1.selectByIndex(Integer.parseInt(option));
                        }

                        catch (NoSuchElementException r8)
                        {
                            throw new GenericExceptions("Unable to select the value from the dropdown for "+labelName);
                        }

                    }
                }
            }
        }

        return this;
    }

    public String getElementText(By by,String... labelName)
    {
        WebElement element=elementUtils.findElement(by,10,labelName);

        if(element==null)
            throw new GenericExceptions("Unable to find the element");

        performMouseHover(element);

        jsFunctions.higlightElement(element);
        reports.captureScreenshots();
        jsFunctions.disableHighlight(element);

        if(labelName.length>0)
            reports.logReportsToTheFile(LogStatus.INFO_SCREENSHOT,"Text Fetched for: "+labelName+" is: "+element.getText());
        else
            reports.logReportsToTheFile(LogStatus.INFO_SCREENSHOT,"Text Fetched is: "+element.getText());
        return element.getText();
    }

    public void switchToFrame(String nameOrID)
    {
        try {
            driver.switchTo().frame(nameOrID);
        }

        catch (NoSuchFrameException e1)
        {
            throw new GenericExceptions("Unable to switch to the frame with name or ID: "+nameOrID);
        }
    }

    public SeleniumUtils switchToFrame(WebElement element,String... labelName)
    {
        try {
            driver.switchTo().frame(element);
        }

        catch (NoSuchFrameException e1)
        {
            if(labelName.length>0)
                throw new GenericExceptions("Unable to switch to the frame with element: "+labelName);
            else
                throw new GenericExceptions("Unable to switch to the frame on the basis of the element");
        }

        return this;
    }

    public SeleniumUtils switchToFrame(WebElement element,int time,String... labelName)
    {
        try {
            WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(time));
            Optional.ofNullable(wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element)))
                    .orElseThrow(()->new GenericExceptions("Unable to switch to the frame with element: "+labelName));
        }

        catch (NoSuchFrameException e1)
        {
            if(labelName.length>0)
                throw new GenericExceptions("Unable to switch to the frame with element: "+labelName);
            else
                throw new GenericExceptions("Unable to switch to the frame on the basis of element");
        }

        return this;
    }


    public SeleniumUtils switchToFrame(By by,String... labelName)
    {
        WebElement element=elementUtils.findElement(by);

        try {
            driver.switchTo().frame(element);
        }

        catch (NoSuchFrameException e1)
        {
            if(labelName.length>0)
                throw new GenericExceptions("Unable to switch to the frame with element: "+labelName);
            else
                throw new GenericExceptions("Unable to switch to the frame with elemeent");
        }

        return this;
    }

    public SeleniumUtils switchToFrame(By by,int time,String... labelName)
    {
        WebElement element=elementUtils.findElement(by);

        try {
            WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(time));
            Optional.ofNullable(wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element)))
                    .orElseThrow(()->new GenericExceptions("Unable to switch to the frame with element: "+labelName));
        }

        catch (NoSuchFrameException e1)
        {
            if(labelName.length>0)
                throw new GenericExceptions("Unable to switch to the frame with element: "+labelName);
            else
                throw new GenericExceptions("Unable to switch to the frame ");
        }

        return this;
    }


    public SeleniumUtils switchToFrame(String nameOrID, int time)
    {
        try {
            WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(time));
            Optional.ofNullable(wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrID)))
                    .orElseThrow(()->new GenericExceptions("Unable to switch to the frame with name or ID: "+nameOrID));
        }

        catch (NoSuchFrameException e1)
        {
            throw new GenericExceptions("Unable to switch to the frame with name or ID: "+nameOrID);
        }

        return this;
    }

    public SeleniumUtils switchToParentFrame()
    {
        driver.switchTo().parentFrame();
        return this;
    }

    public SeleniumUtils switchOutOfAllFrames()
    {
        driver.switchTo().defaultContent();
        return this;
    }

    public SeleniumUtils switchToFrame(int index)
    {
        try {
            driver.switchTo().frame(index);
        }

        catch (NoSuchFrameException e1)
        {
            throw new GenericExceptions("Unable to switch to the frame with index: "+index);
        }
        return this;
    }


    public SeleniumUtils performRequiredKeyboardActions(By by,Keys keys)
    {
        WebElement element=elementUtils.findElement(by);

        jsFunctions.higlightElement(element);
        reports.captureScreenshots();
        jsFunctions.disableHighlight(element);

        element.sendKeys(keys);

        return this;
    }

    public void performRequiredKeyboardActions(WebElement element,Keys keys)
    {
        jsFunctions.higlightElement(element);
        reports.captureScreenshots();
        jsFunctions.disableHighlight(element);

        element.sendKeys(keys);
    }

    public void performWheelScroll(By by)
    {
        WebElement element=elementUtils.findElement(by);
        Actions actions = new Actions(driver);
        actions.scrollToElement(element).perform();
    }

    public void performKeyBoardActionsOnBody(Keys keys)
    {
        elementUtils.findElement(By.tagName("body")).click();

        for(int i=0;i<3;i++) {
            driver.switchTo().activeElement().sendKeys(keys);
        }

    }

}

