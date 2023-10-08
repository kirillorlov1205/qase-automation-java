package page;

import elementsWrappers.Button;
import elementsWrappers.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

public class ProjectsPage extends BasePage {

    private static final By CREATE_NEW_PROJECT_BUTTON_LOCATOR = By.xpath("//button[@id='createButton']");
    private static final By PROJECT_NAME_LOCATOR = By.xpath("//input[@id='project-name']");
    private static final By PROJECT_CODE_LOCATOR = By.xpath("//input[@id='project-code']");

    @FindBy(xpath = "//table[contains(@class,'projects-table')]")
    private WebElement projectsTable;

    public boolean isProjectsListDisplayed() {
        return Waiter.waitElementToBeDisplayed(projectsTable).isDisplayed();
    }

    public ProjectsPage clickCreateNewProjectButton() {
        new Button(CREATE_NEW_PROJECT_BUTTON_LOCATOR);
        return this;
    }

    public ProjectsPage fillProjectName(String projectName){
        new Input(PROJECT_NAME_LOCATOR).writeText(projectName);
        return this;
    }

    public ProjectsPage fillProjectCode(String projectCode){
        new Input(PROJECT_CODE_LOCATOR).writeText(projectCode);
        return this;
    }
}
