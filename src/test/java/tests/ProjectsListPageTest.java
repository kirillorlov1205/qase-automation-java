package tests;

import io.qameta.allure.Description;
import model.Constants;
import model.Project;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.LoginPageService;
import service.NewProjectPageService;
import service.ProjectsListPageService;
import utils.Enums;
import utils.Utils;

public class ProjectsListPageTest extends BaseTest {

    private LoginPageService loginPageService;
    private ProjectsListPageService projectsListPageService;
    private NewProjectPageService newProjectPageService;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
        projectsListPageService = new ProjectsListPageService();
        newProjectPageService = new NewProjectPageService();
    }

    @AfterClass
    public void clean() {
        projectsListPageService
                .openProjectsListPage()
                .removeCreatedProjects();
    }

    @Test(description = "Verify successful project creation", priority = 1)
    @Description("Successful project creation")
    public void verifySuccessfulProjectCreation() {
        Project project = new Project();
        String actualProjectTitle = loginPageService
                .login(Constants.USER_WITH_VALID_CREDENTIALS)
                .createNewProject(project)
                .getNewProjectTitle();
        Assert.assertTrue(actualProjectTitle.contains(project.getProjectCode().toUpperCase()), "New project hasn't been created");
    }

    @Test(description = "Verify empty project name validation", priority = 2)
    @Description("Empty project name validation")
    public void verifyEmptyProjectNameValidation() {
        Project projectWithEmptyName = new Project("", Utils.getRandomString(9), Utils
                .getRandomString(9), Enums.ProjectAccessTypes.Public);
        projectsListPageService.openProjectsListPage()
                .createNewProject(projectWithEmptyName);
        Assert.assertFalse(newProjectPageService.isNewProjectPageOpened(), "Empty project name validation failed");
    }

    @Test(description = "Verify empty project code validation", priority = 3)
    @Description("Empty project code validation")
    public void verifyEmptyProjectCodeValidation() {
        Project projectWithEmptyCode = new Project(Utils.getRandomString(9), " ", Utils
                .getRandomString(9), Enums.ProjectAccessTypes.Public);
        projectsListPageService.openProjectsListPage()
                .createNewProject(projectWithEmptyCode);
        Assert.assertFalse(newProjectPageService.isNewProjectPageOpened(), "Empty project code validation failed");
    }

    @Test(description = "Verify automatic project code filling", priority = 4)
    @Description("Automatic project code filling")
    public void verifyAutomaticProjectCodeFilling() {
        Project projectWithEmptyCode = new Project(Utils.getRandomString(9), "", Utils
                .getRandomString(9), Enums.ProjectAccessTypes.Public);
        String actualProjectTitle = projectsListPageService
                .openProjectsListPage()
                .createNewProject(projectWithEmptyCode)
                .getNewProjectTitle();
        Assert.assertTrue(actualProjectTitle.contains(projectWithEmptyCode.getProjectName().toUpperCase()),
                "Project code value hasn't been filled automatically");
    }
}
