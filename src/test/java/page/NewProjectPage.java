package page;

import driver.UiDriverActions;
import elementsWrappers.Heading;
import org.openqa.selenium.By;

public class NewProjectPage extends BasePage {

    private static final By NEW_PROJECT_TITLE = By.xpath("//h1[@class='fGDnu0']");

    public String getNewProjectTitle() {
        return new Heading(NEW_PROJECT_TITLE).getText();
    }

    public boolean isNewProjectPageOpened() {
        return !UiDriverActions.isNotDisplayed(NEW_PROJECT_TITLE);
    }
}
