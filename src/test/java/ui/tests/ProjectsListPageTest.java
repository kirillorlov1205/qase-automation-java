package ui.tests;

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
                .projectCode("")
                .projectDescription(generateRandomString(2, 10))
                .projectAccessType(Enums.ProjectAccessTypes.Public)
                .build();
        String actualProjectTitle = loginPageService
                .login(new User())
                .createNewProject(project)
                .getProjectTitle();
        projectsListPageService.deleteProjectByCode(project.getProjectCode());
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

    @Test(description = "Verify automatic project code filling", priority = 4)
    @Description("Automatic project code filling")
    public void verifyAutomaticProjectCodeFilling() {
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
        projectsListPageService.deleteProjectByCode(projectWithEmptyCode.getProjectName().toUpperCase());
        Assert.assertTrue(actualProjectTitle.contains(projectWithEmptyCode.getProjectName().toUpperCase()),
                "Project code value not filled automatically");
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
