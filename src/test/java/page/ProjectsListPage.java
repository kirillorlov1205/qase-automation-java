package page;

import driver.UiDriverActions;
import elementsWrappers.Button;
import elementsWrappers.Input;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import utils.Enums;
import utils.Waiter;

@Log4j2
public class ProjectsListPage extends BasePage {

    private static final String PROJECTS_LIST_PAGE_URL = "https://app.qase.io/projects";
    private static final By CREATE_NEW_PROJECT_BUTTON_LOCATOR = By.xpath("//button[@id='createButton']");
    private static final By PROJECT_NAME_LOCATOR = By.xpath("//input[@id='project-name']");
    private static final By PROJECT_CODE_LOCATOR = By.xpath("//input[@id='project-code']");
    private static final By PROJECT_DESCRIPTION_LOCATOR = By.xpath("//textarea[@name='description-" + "area']");
    private static final By SUBMIT_PROJECT_CREATION_BUTTON_LOCATOR = By.xpath("//button[@type='submit']");
    private static final String PROJECT_ACCESS_TYPE_BUTTON_LOCATOR = "//span[contains(text(),'%s')]";
    private static final String PROJECT_DROPDOWN_BUTTON_LOCATOR = "//tr[%s]//a[contains(@class,'btn-dropdown')]";
    private static final String REMOVE_PROJECT_BUTTON_LOCATOR = "//tr[%s]//button[contains(text(),'Delete')]";
    private static final By SUBMIT_PROJECT_REMOVING_BUTTON_LOCATOR = By.xpath("//button[contains(@class," +
            "'b_jd28')]");
    private static final By PROJECTS_LIST_LOCATOR = By.xpath("//tr[@class='project-row']");

    public boolean isProjectsPageDisplayed() {
        return new Button(CREATE_NEW_PROJECT_BUTTON_LOCATOR).isDisplayed();
    }

    public ProjectsListPage clickCreateNewProjectButton() {
        log.info("Click create new project button");
        new Button(CREATE_NEW_PROJECT_BUTTON_LOCATOR).click();
        return this;
    }

    public ProjectsListPage fillProjectName(String projectName) {
        log.info("Fill project name");
        new Input(PROJECT_NAME_LOCATOR).writeText(projectName);
        return this;
    }

    public ProjectsListPage fillProjectCode(String projectCode) {
        log.info("Fill project code");
        new Input(PROJECT_CODE_LOCATOR).clear().writeText(projectCode);
        return this;
    }

    public ProjectsListPage fillProjectDescription(String projectCode) {
        log.info("Fill project description");
        new Input(PROJECT_DESCRIPTION_LOCATOR).writeText(projectCode);
        return this;
    }

    public ProjectsListPage selectProjectAccessType(Enums.ProjectAccessTypes projectAccessType) {
        log.info("Select project access type");
        new Button(By.xpath(String.format(PROJECT_ACCESS_TYPE_BUTTON_LOCATOR, projectAccessType))).click();
        return this;
    }

    public ProjectsListPage submitProjectCreation() {
        log.info("Submit project creation");
        new Button(SUBMIT_PROJECT_CREATION_BUTTON_LOCATOR).click();
        return this;
    }

    public ProjectsListPage removeCreatedProjects() {
        log.info("Remove created projects");
        int projectsQuantity = Waiter.waitElementsToBeDisplayedByLocator(PROJECTS_LIST_LOCATOR).size();
        for (int i = projectsQuantity; i > 0; i--) {
            new Button(By.xpath(String.format(PROJECT_DROPDOWN_BUTTON_LOCATOR, i))).click();
            new Button(By.xpath(String.format(REMOVE_PROJECT_BUTTON_LOCATOR, i))).click();
            new Button(SUBMIT_PROJECT_REMOVING_BUTTON_LOCATOR).click();
        }
        return this;
    }

    public ProjectsListPage openProjectsListPage() {
        log.info("Open projects list page");
        UiDriverActions.openPage(PROJECTS_LIST_PAGE_URL);
        return this;
    }

    public boolean isNewProjectCreated() {
        return Waiter.waitElementsToBeDisplayedByLocator(SUBMIT_PROJECT_CREATION_BUTTON_LOCATOR).size() > 0;
    }
}
