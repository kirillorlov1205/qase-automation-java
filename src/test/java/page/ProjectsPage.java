package page;

import elementsWrappers.Button;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

public class ProjectsPage extends BasePage {

    private static final By CREATE_NEW_PROJECT_BUTTON = By.xpath("//button[@id='createButton']");

    @FindBy(xpath = "//table[contains(@class,'projects-table')]")
    private WebElement projectsTable;

    public boolean isProjectsListDisplayed() {
        return Waiter.waitElementToBeDisplayed(projectsTable).isDisplayed();
    }

    public ProjectsPage clickCreateNewProjectButton() {
        new Button(CREATE_NEW_PROJECT_BUTTON);
        return this;
    }
}
