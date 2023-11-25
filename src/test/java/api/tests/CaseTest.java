package api.tests;

import api.adapters.CaseAdapter;
import api.adapters.ProjectAdapter;
import api.models.Case;
import api.models.Project;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Retry;

import static java.net.HttpURLConnection.HTTP_OK;
import static utils.TestDataGenerator.generateRandomAlphabeticString;

public class CaseTest {
    private static final String CASE_ID = "/1";
    private Project project;
    private Case testCase;

    @BeforeClass
    public void setUp() {
        project = Project.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .code(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        new ProjectAdapter().createNewProject(project);
        testCase = Case.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .type(1)
                .priority(2)
                .severity(1)
                .preconditions(generateRandomAlphabeticString(2, 10))
                .postconditions(generateRandomAlphabeticString(2, 10))
                .build();
    }

    @AfterClass
    public void deleteProject() {
        new ProjectAdapter().deleteProjectByCode(project.getCode());
    }

    @Test(description = "Check new case creation", priority = 1, retryAnalyzer = Retry.class)
    @Description("New case creation")
    public void checkNewCaseCreation() {
        int expectedCaseId = 1;
        int actualCaseId = new CaseAdapter().createNewCase(project.getCode(), testCase).body().path("result.id");
        Assert.assertEquals(actualCaseId, expectedCaseId, "Case id doesn't match expected");
    }

    @Test(description = "Check case without title creation validation", priority = 2)
    @Description("Case without title creation validation")
    public void checkCaseWithoutTitleCreationValidation() {
        String expectedValidationMessage = "The title field is required.";
        Case caseWithoutTitle = Case.builder()
                .type(1)
                .build();
        String actualValidationMessage = new CaseAdapter().createNewCase(project.getCode(), caseWithoutTitle).body()
                .path("message");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Check new case creation in balk", priority = 3)
    @Description("New case creation in balk")
    public void checkNewCaseCreationInBalk() {
        int expectedCaseId = 2;
        int actualCaseId = new CaseAdapter().createNewCase(project.getCode(), testCase).body().path("result.id");
        Assert.assertEquals(actualCaseId, expectedCaseId, "Case id doesn't match expected");
    }

    @Test(description = "Check get case by id", priority = 4)
    @Description("Get case by id")
    public void checkGetCaseById() {
        String createdCaseTitle = new CaseAdapter().getCaseById(project.getCode(), CASE_ID).body().path("result." +
                "title");
        Assert.assertEquals(createdCaseTitle, testCase.getTitle(), "Created case title doesn't match expected");
    }

    @Test(description = "Check get case by wrong id validation", priority = 5)
    @Description("Get case by wrong id validation")
    public void checkGetCaseByWrongIdValidation() {
        String wrongCaseId = "/5";
        String expectedValidationMessage = "TestCase not found";
        String actualValidationMessage = new CaseAdapter().getCaseById(project.getCode(), wrongCaseId).body().path(
                "errorMessage");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Check get all cases", priority = 6)
    @Description("Get all cases")
    public void checkGetAllCases() {
        int expectedCasesQuantity = 2;
        int actualCasesQuantity = new CaseAdapter().getAllCasesByProjectCode(project.getCode()).body().path(
                "result.total");
        Assert.assertEquals(actualCasesQuantity, expectedCasesQuantity, "Cases quantity doesn't match " +
                "expected");
    }

    @Test(description = "Check case updating", priority = 7)
    @Description("Case updating")
    public void checkCaseUpdating() {
        Case updateCase = Case.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .preconditions(generateRandomAlphabeticString(2, 10))
                .type(1)
                .build();
        int statusCode = new CaseAdapter().updateCase(project.getCode(), CASE_ID, updateCase).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match expected");
    }

    @Test(description = "Check case deletion by id", priority = 8)
    @Description("Case deletion by id")
    public void checkCaseDeletionById() {
        int statusCode = new CaseAdapter().deleteCaseById(project.getCode(), CASE_ID).getStatusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match");
    }
}
