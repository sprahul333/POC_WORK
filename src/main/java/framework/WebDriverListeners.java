package framework;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;

public class WebDriverListeners implements WebDriverListener
{
    public void beforeClick(WebElement element) {
        FileUtility.writeDataToTheFile(PathUtils.getResultsPath()+"//"+"SeleniumActions.txt", "Clicking on the element: "+element.toString());
    }

    public void afterClick(WebElement element) {
        FileUtility.writeDataToTheFile(PathUtils.getResultsPath()+"//"+"SeleniumActions.txt", "Clicked on the element: "+element.toString());
    }


    public void beforeSendKeys(WebElement element, CharSequence... keysToSend) {
        FileUtility.writeDataToTheFile(PathUtils.getResultsPath()+"//"+"SeleniumActions.txt", "Entering the data to the element: "+element.toString());
    }

    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        FileUtility.writeDataToTheFile(PathUtils.getResultsPath()+"//"+"SeleniumActions.txt", "Entered the data to the element: "+element.toString());
    }

}
