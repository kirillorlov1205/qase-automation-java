package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

public class ProjectsPage extends BasePage {

    @FindBy(xpath = "//table[contains(@class,'projects-table')]")
    private WebElement projectsTable;

    public boolean isProjectsListDisplayed() {
        return Waiter.waitElementToBeDisplayed(projectsTable).isDisplayed();
    }
}
