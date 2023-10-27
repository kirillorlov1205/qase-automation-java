package ui.tests;

import io.qameta.allure.Description;
import ui.model.Constants;
import ui.model.Project;
import ui.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.service.LoginPageService;
import ui.service.ProjectPageService;
import ui.service.ProjectsListPageService;
import utils.Enums;

import static utils.TestDataGenerator.*;

public class ProjectsListPageTest extends BaseTest {

    private LoginPageService loginPageService;
    private ProjectsListPageService projectsListPageService;
    private ProjectPageService projectPageService;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
        projectsListPageService = new ProjectsListPageService();
        projectPageService = new ProjectPageService();
    }

    @Test(description = "Verify successful project creation", priority = 1)
    @Description("Successful project creation")
    public void verifySuccessfulProjectCreation() {
        Project project = new Project();
        String actualProjectTitle = loginPageService
                .login(new User())
                .createNewProject(project)
                .getProjectTitle();
        Assert.assertTrue(actualProjectTitle.contains(project.getProjectCode().toUpperCase()), "New project " +
                "hasn't been created");
    }

    @Test(description = "Verify empty project name validation", priority = 2)
    @Description("Empty project name validation")
    public void verifyEmptyProjectNameValidation() {
        Project projectWithEmptyName = new Project("", generateRandomString(2, 10), generateRandomString(2,
                10), Enums.ProjectAccessTypes.Public);
        projectsListPageService.openProjectsListPage()
                .createNewProject(projectWithEmptyName);
        Assert.assertTrue(projectPageService.isProjectPageNotOpened(), "Empty project name validation failed");
    }

    @Test(description = "Verify empty project code validation", priority = 3)
    @Description("Empty project code validation")
    public void verifyEmptyProjectCodeValidation() {
        Project projectWithEmptyCode = new Project(generateRandomString(2, 10), " ", generateRandomString(10,
                20), Enums.ProjectAccessTypes.Public);
        projectsListPageService.openProjectsListPage()
                .createNewProject(projectWithEmptyCode);
        Assert.assertTrue(projectPageService.isProjectPageNotOpened(), "Empty project code validation failed");
    }

    @Test(description = "Verify automatic project code filling", priority = 4)
    @Description("Automatic project code filling")
    public void verifyAutomaticProjectCodeFilling() {
        Project projectWithEmptyCode = new Project("test".concat(generateRandomAlphabeticString(2, 6)), "",
                generateRandomString(10, 20), Enums.ProjectAccessTypes.Public);
        String actualProjectTitle = projectsListPageService
                .openProjectsListPage()
                .createNewProject(projectWithEmptyCode)
                .getProjectTitle();
        Assert.assertTrue(actualProjectTitle.contains(projectWithEmptyCode.getProjectName().toUpperCase()),
                "Project code value hasn't been filled automatically");
    }

    @Test(description = "Verify less than limit code validation", priority = 5)
    @Description("Less than limit code validation")
    public void verifyLessThanLimitCodeValidation() {
        Project projectWithLessThanLimitCode = new Project(generateRandomString(2, 10), generateRandomAlphabeticString(
                1, 1), generateRandomString(10, 20), Enums.ProjectAccessTypes.Public);
        projectsListPageService.openProjectsListPage()
                .createNewProject(projectWithLessThanLimitCode);
        String validationMessage = projectsListPageService.getInvalidCodeValidationMessage();
        Assert.assertEquals(validationMessage, Constants.CODE_LESS_THAN_LIMIT_VALIDATION_MESSAGE, "Validation" +
                " message doesn't match expected");
    }

    @Test(description = "Verify more than limit code validation", priority = 6)
    @Description("More than limit code validation")
    public void verifyMoreThanLimitCodeValidation() {
        Project projectWithMoreThanLimitCode = new Project(generateRandomString(2, 10), generateRandomAlphabeticString(
                11, 20), generateRandomString(10, 20), Enums.ProjectAccessTypes.Public);
        projectsListPageService.openProjectsListPage()
                .createNewProject(projectWithMoreThanLimitCode);
        String validationMessage = projectsListPageService.getInvalidCodeValidationMessage();
        Assert.assertEquals(validationMessage, Constants.CODE_MORE_THAN_LIMIT_VALIDATION_MESSAGE, "Validation " +
                "message doesn't match expected");
    }

    @Test(description = "Verify no automatic code filing with numeric project name", priority = 7)
    @Description("No automatic code filing with numeric project name")
    public void verifyNoAutomaticCodeFilingWithNumericProjectName() {
        Project projectWIthNumericName = new Project(generateRandomNumericString(2, 10), "",
                generateRandomString(10, 20), Enums.ProjectAccessTypes.Public);
        projectsListPageService.openProjectsListPage()
                .clickCreateNewProjectButton()
                .fillProjectNameField(projectWIthNumericName.getProjectName());
        Assert.assertTrue(projectsListPageService.isProjectCodeFieldEmpty(), "Project code field is not empty");
    }

    @Test(description = "Verify private member access selection", priority = 8)
    @Description("Private member access selection")
    public void verifyPrivateMemberAccessSelection() {
        Project projectWIthPrivateAccess = new Project(generateRandomString(2, 10), generateRandomString(2, 10),
                generateRandomString(10, 20), Enums.ProjectAccessTypes.Private);
        projectsListPageService.openProjectsListPage()
                .clickCreateNewProjectButton()
                .selectProjectAccessType(projectWIthPrivateAccess.getProjectAccessType());
        Assert.assertTrue(projectsListPageService.isPrivateMemberAccessDisplayed(), "Private access type is " +
                "not selected");
    }
}
