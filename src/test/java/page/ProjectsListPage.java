package page;

import driver.UiDriverActions;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Enums;
import utils.Waiter;

import java.util.List;

@Log4j2
public class ProjectsListPage extends BasePage {

    private static final String PROJECTS_LIST_PAGE_URL = "https://app.qase.io/projects";
    private static final String PROJECT_ACCESS_TYPE_BUTTON = "//span[contains(text(),'%s')]";
    private static final String PROJECT_DROPDOWN_BUTTON = "//tr[%s]//a[contains(@class,'btn-dropdown')]";
    private static final String REMOVE_PROJECT_BUTTON = "//tr[%s]//button[contains(text(),'Delete')]";

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
        return Waiter.waitElementToBeDisplayed(createNewProjectButton).isDisplayed();
    }

    public ProjectsListPage clickCreateNewProjectButton() {
        log.info("Click create new project button");
        Waiter.waitElementToBeDisplayed(createNewProjectButton).click();
        return this;
    }

    public ProjectsListPage fillProjectName(String projectName) {
        log.info("Fill project name");
        Waiter.waitElementToBeDisplayed(projectNameField).sendKeys(projectName);
        return this;
    }

    public ProjectsListPage fillProjectCode(String projectCode) {
        log.info("Fill project code");
        Waiter.waitElementToBeDisplayed(projectCodeField).clear();
        Waiter.waitElementAttributeToBe(projectCodeField, "value", "");
//        projectCodeField.getAttribute("value").isEmpty();
        projectCodeField.sendKeys(projectCode);
        return this;
    }

    public ProjectsListPage fillProjectDescription(String projectCode) {
        log.info("Fill project description");
        Waiter.waitElementToBeDisplayed(projectDescriptionField).sendKeys(projectCode);
        return this;
    }

    public ProjectsListPage selectProjectAccessType(Enums.ProjectAccessTypes projectAccessType) {
        log.info("Select project access type");
        Waiter.waitElementToBeDisplayedByLocator(By.xpath(String.format(PROJECT_ACCESS_TYPE_BUTTON, projectAccessType))
        ).click();
        return this;
    }

    public ProjectsListPage submitProjectCreation() {
        log.info("Submit project creation");
        Waiter.waitElementToBeDisplayed(submitProjectCreationButton).click();
        return this;
    }

    public ProjectsListPage removeCreatedProjects() {
        log.info("Remove created projects");
        int projectsQuantity = Waiter.waitElementsToBeDisplayed(projectList).size();
        if (projectsQuantity > 0) {
            for (int i = projectsQuantity; i > 0; i--) {
                try {
                    removeCreatedProjectByIndex(i);
                } catch (StaleElementReferenceException e) {
                    log.warn("StaleElementReferenceException was thrown\n Retrying to remove project");
                    removeCreatedProjectByIndex(i);
                }
            }
        }
        return this;
    }

    public ProjectsListPage removeCreatedProjectByIndex(int index) {
        Waiter.waitElementToBeDisplayedByLocator(By.xpath(String.format(PROJECT_DROPDOWN_BUTTON, index))).click();
        Waiter.waitElementToBeDisplayedByLocator(By.xpath(String.format(REMOVE_PROJECT_BUTTON, index))).click();
        Waiter.waitElementToBeDisplayed(submitProjectRemovingButton).click();
        return this;
    }

    public ProjectsListPage openProjectsListPage() {
        log.info("Open projects list page");
        UiDriverActions.openPage(PROJECTS_LIST_PAGE_URL);
        return this;
    }

    public String getInvalidCodeValidationMessage() {
        return Waiter.waitElementToBeDisplayed(invalidCodeValidationMessage).getText();
    }

    public boolean isProjectCodeFieldEmpty() {
        return Waiter.waitElementToBeDisplayed(projectCodeField).getAttribute("value").isEmpty();
    }

    public boolean isPrivateMemberAccessDisplayed() {
        return Waiter.waitElementToBeDisplayed(privateMemberAccess).isDisplayed();
    }
}
