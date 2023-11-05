package api.tests;

import api.adapters.CaseAdapter;
import api.adapters.PlanAdapter;
import api.adapters.ProjectAdapter;
import api.models.Case;
import api.models.Plan;
import api.models.Project;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Retry;

import static java.net.HttpURLConnection.HTTP_OK;
import static utils.TestDataGenerator.generateRandomAlphabeticString;

public class PlanTest {
    private static final String PLAN_ID = "/1";
    private Project project;
    private Plan plan;
    private Case testCase;

    @BeforeClass
    public void setUp() {
        project = Project.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .code(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        plan = Plan.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .cases(new int[]{1, 2})
                .build();
        testCase = Case.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .type(1)
                .build();
        new ProjectAdapter().createNewProject(project);
        new CaseAdapter().createNewCase(project.getCode(), testCase);
        new CaseAdapter().createNewCase(project.getCode(), testCase);
    }

    @AfterClass
    public void deleteProject() {
        new ProjectAdapter().deleteProjectByCode(project.getCode());
    }

    @Test(description = "New plan creation", priority = 1, retryAnalyzer = Retry.class)
    public void checkNewPlanCreation() {
        int expectedPlanId = 1;
        int actualPlanId = new PlanAdapter().createNewPlan(project.getCode(), plan).body().path("result.id");
        Assert.assertEquals(actualPlanId, expectedPlanId, "Plan id doesn't match expected");
    }

    @Test(description = "Plan without title creation validation", priority = 2)
    public void checkPlanWithoutTitleCreationValidation() {
        String expectedValidationMessage = "The title field is required.";
        Plan planWithoutTitle = Plan.builder()
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        String actualValidationMessage = new PlanAdapter().createNewPlan(project.getCode(), planWithoutTitle).body()
                .path("errorFields[0].error");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't" +
                "doesn't match expected");
    }

    @Test(description = "Plan without cases validation", priority = 3)
    public void checkPlanWithoutCasesValidation() {
        String expectedValidationMessage = "Data is invalid.";
        Plan planWithoutCases = Plan.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .build();
        String actualValidationMessage = new PlanAdapter().createNewPlan(project.getCode(), planWithoutCases).body()
                .path("errorMessage");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't" +
                "doesn't match expected");
    }

    @Test(description = "Get plan by id", priority = 4)
    public void checkGetPlanById() {
        String createdPlanTitle = new PlanAdapter().getPlanById(project.getCode(), PLAN_ID).body().path("result." +
                "title");
        Assert.assertEquals(createdPlanTitle, plan.getTitle(), "Created plan title doesn't match expected");
    }

    @Test(description = "Get plan by wrong id", priority = 5)
    public void checkGetPlanByWrongId() {
        String wrongPlanId = "/5";
        String expectedValidationMessage = "Plan not found";
        String actualValidationMessage = new PlanAdapter().getPlanById(project.getCode(), wrongPlanId).body().path(
                "errorMessage");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Get all plans", priority = 6)
    public void checkGetAllPlans() {
        int expectedPlanQuantity = 1;
        int actualPlansQuantity = new PlanAdapter().getAllPlansByProjectCode(project.getCode()).body().path(
                "result.total");
        Assert.assertEquals(actualPlansQuantity, expectedPlanQuantity, "Plans quantity doesn't match expected");
    }

    @Test(description = "Plan updating", priority = 7)
    public void checkPlanUpdating() {
        Plan updatePlan = Plan.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        int statusCode = new PlanAdapter().updatePlan(project.getCode(), PLAN_ID, updatePlan).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match expected");
    }

    @Test(description = "Plan deletion by id", priority = 8)
    public void checkPlanDeletionById() {
        int statusCode = new PlanAdapter().deletePlanById(project.getCode(), PLAN_ID).getStatusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match");
    }
}
