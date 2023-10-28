package ui.tests;

import api.adapters.ProjectAdapter;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.model.Project;
import ui.model.User;
import ui.service.LoginPageService;
import ui.service.ProjectPageService;
import ui.service.ProjectsListPageService;
import utils.Enums;

import static utils.TestDataGenerator.*;

public class ProjectsListPageTest extends BaseTest {
    private static final String CODE_LESS_THAN_LIMIT_VALIDATION_MESSAGE = "The code must be at least 2 characters.";
    private static final String CODE_MORE_THAN_LIMIT_VALIDATION_MESSAGE = "The code may not be greater than 10 " +
            "characters.";
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
        Project project = Project.builder()
                .projectName(generateRandomAlphabeticString(2, 10))
                .projectCode(generateRandomAlphabeticString(2, 10))
                .projectDescription(generateRandomString(2, 10))
                .projectAccessType(Enums.ProjectAccessTypes.Public)
                .build();
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
        Project projectWithEmptyName = Project.builder()
                .projectName("")
                .projectCode(generateRandomAlphabeticString(2, 10))
                .projectDescription(generateRandomString(2, 10))
                .projectAccessType(Enums.ProjectAccessTypes.Public)
                .build();
        projectsListPageService.openProjectsListPage()
                .createNewProject(projectWithEmptyName);
        Assert.assertTrue(projectPageService.isProjectPageNotOpened(), "Empty project name validation failed");
    }

    @Test(description = "Verify empty project code validation", priority = 3)
    @Description("Empty project code validation")
    public void verifyEmptyProjectCodeValidation() {
        Project projectWithEmptyCode = Project.builder()
                .projectName(generateRandomAlphabeticString(2, 10))
                .projectCode("")
                .projectDescription(generateRandomString(2, 10))
                .projectAccessType(Enums.ProjectAccessTypes.Public)
                .build();
        projectsListPageService.openProjectsListPage()
                .createNewProject(projectWithEmptyCode);
        Assert.assertTrue(projectPageService.isProjectPageNotOpened(), "Empty project code validation failed");
    }

    @Test(description = "Verify automatic project code filling", priority = 4)
    @Description("Automatic project code filling")
    public void verifyAutomaticProjectCodeFilling() {
        loginPageService.openLoginPage().login(new User());
        Project projectWithEmptyCode = Project.builder()
                .projectName(generateRandomAlphabeticString(2, 10))
                .projectCode("")
                .projectDescription(generateRandomString(2, 10))
                .projectAccessType(Enums.ProjectAccessTypes.Public)
                .build();
        String actualProjectTitle = projectsListPageService
                .openProjectsListPage()
                .createNewProject(projectWithEmptyCode)
                .getProjectTitle();
        new ProjectAdapter().deleteProjectByCode(projectWithEmptyCode.getProjectName());
        Assert.assertTrue(actualProjectTitle.contains(projectWithEmptyCode.getProjectName().toUpperCase()),
                "Project code value not filled automatically");
    }

    @Test(description = "Verify less than limit code validation", priority = 5)
    @Description("Less than limit code validation")
    public void verifyLessThanLimitCodeValidation() {
        Project projectWithLessThanLimitCode = Project.builder()
                .projectName(generateRandomAlphabeticString(2, 10))
                .projectCode(generateRandomAlphabeticString(1, 1))
                .projectDescription(generateRandomString(2, 10))
                .projectAccessType(Enums.ProjectAccessTypes.Public)
                .build();
        projectsListPageService.openProjectsListPage()
                .createNewProject(projectWithLessThanLimitCode);
        String validationMessage = projectsListPageService.getInvalidCodeValidationMessage();
        Assert.assertEquals(validationMessage, CODE_LESS_THAN_LIMIT_VALIDATION_MESSAGE, "Validation" +
                " message doesn't match expected");
    }

    @Test(description = "Verify more than limit code validation", priority = 6)
    @Description("More than limit code validation")
    public void verifyMoreThanLimitCodeValidation() {
        Project projectWithMoreThanLimitCode = Project.builder()
                .projectName(generateRandomAlphabeticString(2, 10))
                .projectCode(generateRandomAlphabeticString(11, 11))
                .projectDescription(generateRandomString(2, 10))
                .projectAccessType(Enums.ProjectAccessTypes.Public)
                .build();
        projectsListPageService.openProjectsListPage()
                .createNewProject(projectWithMoreThanLimitCode);
        String validationMessage = projectsListPageService.getInvalidCodeValidationMessage();
        Assert.assertEquals(validationMessage, CODE_MORE_THAN_LIMIT_VALIDATION_MESSAGE, "Validation message " +
                "doesn't match expected");
    }

    @Test(description = "Verify no automatic code filing with numeric project name", priority = 7)
    @Description("No automatic code filing with numeric project name")
    public void verifyNoAutomaticCodeFilingWithNumericProjectName() {
        Project projectWIthNumericName = Project.builder()
                .projectName(generateRandomNumericString(2, 10))
                .projectCode("")
                .projectDescription(generateRandomString(2, 10))
                .projectAccessType(Enums.ProjectAccessTypes.Public)
                .build();
        projectsListPageService.openProjectsListPage()
                .clickCreateNewProjectButton()
                .fillProjectNameField(projectWIthNumericName.getProjectName());
        Assert.assertTrue(projectsListPageService.isProjectCodeFieldEmpty(), "Project code field not empty");
    }

    @Test(description = "Verify private member access selection", priority = 8)
    @Description("Private member access selection")
    public void verifyPrivateMemberAccessSelection() {
        Project projectWIthPrivateAccess = Project.builder()
                .projectName(generateRandomAlphabeticString(2, 10))
                .projectCode(generateRandomAlphabeticString(2, 10))
                .projectDescription(generateRandomString(2, 10))
                .projectAccessType(Enums.ProjectAccessTypes.Private)
                .build();
        projectsListPageService.openProjectsListPage()
                .clickCreateNewProjectButton()
                .selectProjectAccessType(projectWIthPrivateAccess.getProjectAccessType());
        Assert.assertTrue(projectsListPageService.isPrivateMemberAccessDisplayed(), "Private access type not" +
                " selected");
    }
}
