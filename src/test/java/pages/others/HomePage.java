package pages.others;

import framework.ReusableLibrary;
import org.openqa.selenium.By;

public class HomePage extends ReusableLibrary {

    private By img_NonPawnApplication=By.xpath("//span[text()='Pengajuan Non Gadai']/preceding-sibling::span/img");

    /***************************************************************************************************************************************************/

    public void checkIfElementIsLoaded()
    {
        seleniumUtils.checkIfElementIsLoaded(img_NonPawnApplication,"Non Pawn Application");
    }

    public void clickOnNonPawnApplication()
    {
        seleniumUtils.clickOnElement(img_NonPawnApplication,"Non Pawn Application");
    }
}
