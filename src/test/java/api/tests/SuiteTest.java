package api.tests;

import api.adapters.ProjectAdapter;
import api.adapters.SuiteAdapter;
import api.models.Project;
import api.models.Suite;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Retry;

import static java.net.HttpURLConnection.HTTP_OK;
import static utils.TestDataGenerator.generateRandomAlphabeticString;

public class SuiteTest {
    private static final String SUITE_ID = "/1";
    private Project project;
    private Suite suite;

    @BeforeClass
    public void setUp() {
        project = Project.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .code(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        new ProjectAdapter().createNewProject(project);
        suite = Suite.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .preconditions(generateRandomAlphabeticString(2, 10))
                .build();
    }

    @AfterClass
    public void deleteProject() {
        new ProjectAdapter().deleteProjectByCode(project.getCode());
    }

    @Test(description = "Check new suite creation", priority = 1, retryAnalyzer = Retry.class)
    @Description("New suite creation")
    public void checkNewSuiteCreation() {
        int expectedSuiteId = 1;
        int actualSuiteId = new SuiteAdapter().createNewSuite(project.getCode(), suite).body().path("result.id");
        Assert.assertEquals(actualSuiteId, expectedSuiteId, "Suite id doesn't match expected");
    }

    @Test(description = "Check suite without title creation validation", priority = 2)
    @Description("Suite without title creation validation")
    public void checkSuiteWithoutTitleCreationValidation() {
        Suite suiteWithoutTitle = Suite.builder()
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        String expectedValidationMessage = "The title field is required.";
        String actualValidationMessage = new SuiteAdapter().createNewSuite(project.getCode(), suiteWithoutTitle)
                .body().path("errorFields[0].error");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't" +
                " match expected");
    }

    @Test(description = "Check get suite by id", priority = 3)
    @Description("Get suite by id")
    public void checkGetSuiteById() {
        String createdSuiteTitle = new SuiteAdapter().getSuiteById(project.getCode(), SUITE_ID).body().path(
                "result.title");
        Assert.assertEquals(createdSuiteTitle, suite.getTitle(), "Created suite title doesn't match expected");
    }

    @Test(description = "Check get suite by wrong id validation", priority = 4)
    @Description("Get suite by wrong id validation")
    public void checkGetSuiteByWrongIdValidation() {
        String wrongId = "/3";
        String expectedValidationMessage = "Suite not found";
        String actualValidationMessage = new SuiteAdapter().getSuiteById(project.getCode(), wrongId).body().path(
                "errorMessage");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Check get all suites", priority = 5)
    @Description("Get all suites")
    public void checkGetAllSuites() {
        int expectedSuitesQuantity = 1;
        int actualSuitesQuantity = new SuiteAdapter().getAllSuitesByProjectCode(project.getCode()).body().path(
                "result.total");
        Assert.assertEquals(actualSuitesQuantity, expectedSuitesQuantity, "Suites quantity doesn't match " +
                "expected");
    }

    @Test(description = "Check suite updating", priority = 6)
    @Description("Suite updating")
    public void checkSuiteUpdating() {
        Suite updateSuite = Suite.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .preconditions(generateRandomAlphabeticString(2, 10))
                .build();
        int statusCode = new SuiteAdapter().updateSuite(project.getCode(), SUITE_ID, updateSuite).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match expected");
    }

    @Test(description = "Check delete suite by id", priority = 7)
    @Description("Delete suite by id")
    public void checkSuiteDeletionById() {
        int statusCode = new SuiteAdapter().deleteSuiteById(project.getCode(), SUITE_ID).getStatusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match expected");
    }
}
