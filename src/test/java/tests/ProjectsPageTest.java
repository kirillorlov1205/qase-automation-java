package tests;

import io.qameta.allure.Description;
import model.Project;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.LoginPageService;
import service.ProjectsPageService;
import utils.Enums;
import utils.Utils;

public class ProjectsPageTest extends BaseTest {

    private LoginPageService loginPageService;
    private ProjectsPageService projectsPageService;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
        projectsPageService = new ProjectsPageService();
    }

    @Test(description = "Verify successful project creation", priority = 1)
    @Description("Successful project creation")
    public void verifySuccessfulProjectCreation() {
        User user = new User();
        Project project = new Project();
        String projectTitle = loginPageService
                .login(user)
                .createNewProject(project)
                .getNewProjectTitle();
        Assert.assertTrue(projectTitle.contains(project.getProjectCode()), "New project hasn't been created");
    }

//    @Test(description = "Verify empty project name validation", priority = 2)
//    @Description("Empty project name validation")
//    public void verifySuccessfulProjectCreation() {
//        Project projectWithEmptyName = new Project("", Utils.getRandomString(10), Utils
//                .getRandomString(10), Enums.ProjectAccessTypes.Public);
//        projectsPageService.createNewProject(projectWithEmptyName)
//                .getNewProjectTitle();
//        Assert.assertTrue(), "New project hasn't been created");
//    }
}
