package api.tests;

import api.adapters.ProjectAdapter;
import api.models.Project;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static utils.TestDataGenerator.generateRandomAlphabeticString;

public class ProjectTest {
    private Project project;

    @BeforeClass
    public void setUp() {
        project = Project.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .code(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .build();
    }

    @Test(description = "New project creation", priority = 1)
    public void checkNewProjectCreation() {
        String createdProjectCode = new ProjectAdapter().createNewProject(project).body().path("result.code");
        Assert.assertEquals(createdProjectCode, project.getCode(), "created project code doesn't match expected");
    }

    @Test(description = "Project without title creation validation", priority = 2)
    public void checkProjectWithoutTitleCreationValidation() {
        Project projectWithoutTitle = Project.builder()
                .code(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        String expectedValidationMessage = "Title is required.";
        String actualValidationMessage = new ProjectAdapter().createNewProject(projectWithoutTitle).body().path(
                "errorFields[0].error");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't" +
                " match expected");
    }

    @Test(description = "Project without code creation validation", priority = 3)
    public void checkProjectWithoutCodeCreationValidation() {
        Project projectWithoutCode = Project.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        String expectedValidationMessage = "Project code is required.";
        String actualValidationMessage = new ProjectAdapter().createNewProject(projectWithoutCode).body().path(
                "errorFields[0].error");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't" +
                " match expected");
    }

    @Test(description = "Get project by code", priority = 4)
    public void checkGetProjectByCode() {
        String projectCode = new ProjectAdapter().getProjectByCode(project.getCode()).body().path("result.code");
        Assert.assertEquals(projectCode, project.getCode(), "created project code doesn't match expected");
    }

    @Test(description = "Get project by wrong code validation", priority = 5)
    public void checkGetProjectByWrongCodeValidation() {
        String wrongCode = "WrongCode";
        String expectedValidationMessage = "Project not found";
        String actualValidationMessage = new ProjectAdapter().getProjectByCode(wrongCode).body().path("error" +
                "Message");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Get all projects", priority = 6)
    public void checkGetAllProjects() {
        int statusCode = new ProjectAdapter().getAllProjects().statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match");
    }

    @Test(description = "Project deletion by code", priority = 7)
    public void checkProjectDeletionByCode() {
        int statusCode = new ProjectAdapter().deleteProjectByCode(project.getCode()).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match");
    }

    @Test(description = "Wrong code validation while project deletion", priority = 8)
    public void checkWrongCodeValidationWhileProjectDeletion() {
        String expectedValidationMessage = "Project not found";
        String actualValidationMessage = new ProjectAdapter().deleteProjectByCode(project.getCode()).body().path("error" +
                "Message");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't match expected");
    }
}
