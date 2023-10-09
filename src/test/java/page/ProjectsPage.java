package page;

import driver.UiDriverActions;
import elementsWrappers.Button;
import elementsWrappers.Input;
import org.openqa.selenium.By;
import utils.Enums;

public class ProjectsPage extends BasePage {

    private static final String PROJECTS_PAGE_URL = "https://app.qase.io/projects";
    private static final By CREATE_NEW_PROJECT_BUTTON_LOCATOR = By.xpath("//button[@id='createButton']");
    private static final By PROJECT_NAME_LOCATOR = By.xpath("//input[@id='project-name']");
    private static final By PROJECT_CODE_LOCATOR = By.xpath("//input[@id='project-code']");
    private static final By PROJECT_DESCRIPTION_LOCATOR = By.xpath("//textarea[@name='description-" + "area']");
    private static final By SUBMIT_PROJECT_CREATION_BUTTON_LOCATOR = By.xpath("//button[@type='submit']");
    private static final String PROJECT_ACCESS_TYPE_BUTTON_LOCATOR = "//span[contains(text(),'%s')]";
    private static final By PROJECT_DROPDOWN_BUTTON_LOCATOR = By.xpath("//a[contains(@class,'btn-dropdown')]");
    private static final By REMOVE_PROJECT_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'Delete')]");
    private static final By SUBMIT_PROJECT_REMOVING_BUTTON_LOCATOR = By.xpath("//button[contains(@class," +
            "'b_jd28')]");

    public boolean isProjectsPageDisplayed() {
        return new Button(CREATE_NEW_PROJECT_BUTTON_LOCATOR).isDisplayed();
    }

    public ProjectsPage clickCreateNewProjectButton() {
        new Button(CREATE_NEW_PROJECT_BUTTON_LOCATOR).click();
        return this;
    }

    public ProjectsPage fillProjectName(String projectName) {
        new Input(PROJECT_NAME_LOCATOR).writeText(projectName);
        return this;
    }

    public ProjectsPage fillProjectCode(String projectCode) {
        new Input(PROJECT_CODE_LOCATOR).clear().writeText(projectCode);
        return this;
    }

    public ProjectsPage fillProjectDescription(String projectCode) {
        new Input(PROJECT_DESCRIPTION_LOCATOR).writeText(projectCode);
        return this;
    }

    public ProjectsPage selectProjectAccessType(Enums.ProjectAccessTypes projectAccessType) {
        new Button(By.xpath(String.format(PROJECT_ACCESS_TYPE_BUTTON_LOCATOR, projectAccessType))).click();
        return this;
    }

    public ProjectsPage submitProjectCreation() {
        new Button(SUBMIT_PROJECT_CREATION_BUTTON_LOCATOR).click();
        return this;
    }

    public ProjectsPage openProjectsPage() {
        UiDriverActions.openPage(PROJECTS_PAGE_URL);
        return this;
    }

    public ProjectsPage clickProjectDropdownButton() {
        new Button(PROJECT_DROPDOWN_BUTTON_LOCATOR).click();
        return this;
    }

    public ProjectsPage clickRemoveProjectButton() {
        new Button(REMOVE_PROJECT_BUTTON_LOCATOR).click();
        return this;
    }

    public ProjectsPage submitProjectRemoving() {
        new Button(SUBMIT_PROJECT_REMOVING_BUTTON_LOCATOR).click();
        return this;
    }

//    public boolean isNewProjectCreated() {
//        return UiDriverActions.findElements(SUBMIT_PROJECT_CREATION_BUTTON_LOCATOR).getSize() > 0;
//    }
}
