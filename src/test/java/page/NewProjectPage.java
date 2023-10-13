package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

public class NewProjectPage extends BasePage {

    @FindBy(xpath = "//h1[@class='fGDnu0']")
    private WebElement newProjectTitle;

    public String getNewProjectTitle() {
        return Waiter.waitElementToBeDisplayed(newProjectTitle).getText();
    }
//
//    public boolean isNewProjectPageOpened() {
//        return Waiter.waitElementToBeDisplayed(newProjectTitle).isDisplayed();
//    }

    public boolean isNewProjectPageNotOpened() {
        return Waiter.waitElementToBeNotDisplayed(newProjectTitle);
    }
}
