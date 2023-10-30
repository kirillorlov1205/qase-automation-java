package ui.page;

import api.adapters.ProjectAdapter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.driver.UiDriverActions;
import utils.Enums;
import utils.Waiter;

import java.util.List;

@Log4j2
public class ProjectsListPage extends BasePage {
    private static final String PROJECTS_LIST_PAGE_URL = "https://app.qase.io/projects";
    private static final String PROJECT_ACCESS_TYPE_BUTTON = "//span[contains(text(),'%s')]";

    @FindBy(xpath = "//button[@id='createButton']")
    private WebElement createNewProjectButton;

    @FindBy(xpath = "//input[@id='project-name']")
    private WebElement projectNameField;

    @FindBy(xpath = "//input[@id='project-code']")
    private WebElement projectCodeField;

    @FindBy(xpath = "//textarea[@name='description-area']")
    private WebElement projectDescriptionField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitProjectCreationButton;

    @FindBy(xpath = "//button[contains(@class,'b_jd28')]")
    private WebElement submitProjectRemovingButton;

    @FindBy(xpath = "//div[@class='fkMMG8']")
    private WebElement invalidCodeValidationMessage;

    @FindBy(xpath = "//tr[@class='project-row']")
    private List<WebElement> projectList;

    @FindBy(xpath = "//span[contains(text(),'Add all members to this project')]")
    private WebElement privateMemberAccess;

    public boolean isProjectsPageDisplayed() {
        return Waiter.waitTwoMinutesElementToBeDisplayed(createNewProjectButton).isDisplayed();
    }

    public ProjectsListPage clickCreateNewProjectButton() {
        log.info("Click 'Create new project' button");
        Waiter.waitTwoMinutesElementToBeDisplayed(createNewProjectButton).click();
        return this;
    }

    public ProjectsListPage fillProjectName(String projectName) {
        log.info("Fill 'Project name'");
        Waiter.waitTwoMinutesElementToBeDisplayed(projectNameField).sendKeys(projectName);
        return this;
    }

    public ProjectsListPage fillProjectCode(String projectCode) {
        log.info("Fill 'Project code'");
        Waiter.waitElementToBeDisplayed(projectCodeField).clear();
        Waiter.waitElementToBeDisplayed(projectCodeField).sendKeys(projectCode);
        return this;
    }

    public ProjectsListPage fillProjectDescription(String projectCode) {
        log.info("Fill 'Project description'");
        Waiter.waitElementToBeDisplayed(projectDescriptionField).sendKeys(projectCode);
        return this;
    }

    public ProjectsListPage selectProjectAccessType(Enums.ProjectAccessTypes projectAccessType) {
        log.info(String.format("Select project access type: '%s'", projectAccessType));
        Waiter.waitElementToBeDisplayedByLocator(By.xpath(String.format(PROJECT_ACCESS_TYPE_BUTTON, projectAccessType))
        ).click();
        return this;
    }

    public ProjectsListPage submitProjectCreation() {
        log.info("Submit project creation");
        submitProjectCreationButton.click();
        return this;
    }

    public ProjectsListPage deleteProjectByCode(String code) {
        log.info(String.format("Delete project with code '%s'", code));
        new ProjectAdapter().deleteProjectByCode(code);
        return this;
    }

    public ProjectsListPage openProjectsListPage() {
        log.info("Open 'Projects list' ui.page");
        UiDriverActions.openPage(PROJECTS_LIST_PAGE_URL);
        return this;
    }

    public String getInvalidCodeValidationMessage() {
        return Waiter.waitElementToBeDisplayed(invalidCodeValidationMessage).getText();
    }

    public boolean isProjectCodeFieldEmpty() {
        return projectCodeField.getAttribute("value").isEmpty();
    }

    public boolean isPrivateMemberAccessDisplayed() {
        return privateMemberAccess.isDisplayed();
    }
}
