package api.tests;

import api.adapters.CaseAdapter;
import api.adapters.ProjectAdapter;
import api.adapters.RunAdapter;
import api.models.Case;
import api.models.Project;
import api.models.Run;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.net.HttpURLConnection.HTTP_OK;
import static utils.TestDataGenerator.generateRandomAlphabeticString;

public class RunTest {
    private static final String RUN_ID = "/1";
    private Project project;
    private Run run;
    private Case testCase;

    @BeforeClass
    public void setUp() {
        project = Project.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .code(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        testCase = Case.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .type(1)
                .build();
        run = Run.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .cases(new int[]{1, 2}).build();
        new ProjectAdapter().createNewProject(project);
        new CaseAdapter().createNewCase(project.getCode(), testCase);
        new CaseAdapter().createNewCase(project.getCode(), testCase);
    }

    @AfterClass
    public void deleteProject() {
        new ProjectAdapter().deleteProjectByCode(project.getCode());
    }

    @Test(description = "New run creation", priority = 1)
    public void checkNewRunCreation() {
        int expectedRunId = 1;
        int actualRunId = new RunAdapter().createNewRun(project.getCode(), run).body().path("result.id");
        Assert.assertEquals(actualRunId, expectedRunId, "Run id doesn't match expected");
    }

    @Test(description = "Run without title creation validation", priority = 2)
    public void checkRunWithoutTitleCreationValidation() {
        String expectedValidationMessage = "The title field is required.";
        Run runWithoutTitle = Run.builder()
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        String actualValidationMessage = new RunAdapter().createNewRun(project.getCode(), runWithoutTitle).body()
                .path("errorFields[0].error");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't" +
                "doesn't match expected");
    }

    @Test(description = "Get run by id", priority = 3)
    public void checkGetRunById() {
        String createdRunTitle = new RunAdapter().getRunById(project.getCode(), RUN_ID).body().path("result.title");
        Assert.assertEquals(createdRunTitle, run.getTitle(), "Created run title doesn't match expected");
    }

    @Test(description = "Get run by wrong id", priority = 4)
    public void checkGetRunByWrongId() {
        String wrongRunId = "/5";
        String expectedValidationMessage = "Test run not found";
        String actualValidationMessage = new RunAdapter().getRunById(project.getCode(), wrongRunId).body().path(
                "errorMessage");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Get all runs", priority = 5)
    public void checkGetAllRuns() {
        int expectedRunsQuantity = 1;
        int actualRunsQuantity = new RunAdapter().getAllRunsByProjectCode(project.getCode()).body().path("result" +
                ".total");
        Assert.assertEquals(actualRunsQuantity, expectedRunsQuantity, "Plans quantity doesn't match expected");
    }

    @Test(description = "Run publicity updating", priority = 6)
    public void checkRunPublicityUpdating() {
        Run runWithStatus = Run.builder()
                .status(true).build();
        int statusCode = new RunAdapter().updateRunPublicity(project.getCode(), RUN_ID, runWithStatus).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match expected");
    }

    @Test(description = "Run completion", priority = 7)
    public void checkRunCompletion() {
        int statusCode = new RunAdapter().completeRun(project.getCode(), RUN_ID).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match expected");
    }

    @Test(description = "Run deletion by id", priority = 8)
    public void checkRunDeletionById() {
        int statusCode = new RunAdapter().deleteRunById(project.getCode(), RUN_ID).getStatusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match");
    }
}
