package api.tests;

import api.adapters.CaseAdapter;
import api.adapters.PlanAdapter;
import api.adapters.ProjectAdapter;
import api.models.Case;
import api.models.Plan;
import api.models.Project;
import io.qameta.allure.Description;
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
        Case testCase = Case.builder()
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

    @Test(description = "Check new plan creation", priority = 1, retryAnalyzer = Retry.class)
    @Description("New plan creation")
    public void checkNewPlanCreation() {
        int expectedPlanId = 1;
        int actualPlanId = new PlanAdapter().createNewPlan(project.getCode(), plan).body().path("result.id");
        Assert.assertEquals(actualPlanId, expectedPlanId, "Plan id doesn't match expected");
    }

    @Test(description = "Check plan without title creation validation", priority = 2)
    @Description("Plan without title creation validation")
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

    @Test(description = "Check plan without cases validation", priority = 3)
    @Description("Plan without cases validation")
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

    @Test(description = "Check get plan by id", priority = 4)
    @Description("Get plan by id")
    public void checkGetPlanById() {
        String createdPlanTitle = new PlanAdapter().getPlanById(project.getCode(), PLAN_ID).body().path("result." +
                "title");
        Assert.assertEquals(createdPlanTitle, plan.getTitle(), "Created plan title doesn't match expected");
    }

    @Test(description = "Check get plan by wrong id", priority = 5)
    @Description("Get plan by wrong id")
    public void checkGetPlanByWrongId() {
        String wrongPlanId = "/5";
        String expectedValidationMessage = "Plan not found";
        String actualValidationMessage = new PlanAdapter().getPlanById(project.getCode(), wrongPlanId).body().path(
                "errorMessage");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Check get all plans", priority = 6)
    @Description("Get all plans")
    public void checkGetAllPlans() {
        int expectedPlanQuantity = 1;
        int actualPlansQuantity = new PlanAdapter().getAllPlansByProjectCode(project.getCode()).body().path(
                "result.total");
        Assert.assertEquals(actualPlansQuantity, expectedPlanQuantity, "Plans quantity doesn't match expected");
    }

    @Test(description = "Check plan updating", priority = 7)
    @Description("Plan updating")
    public void checkPlanUpdating() {
        Plan updatePlan = Plan.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        int statusCode = new PlanAdapter().updatePlan(project.getCode(), PLAN_ID, updatePlan).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match expected");
    }

    @Test(description = "Check plan deletion by id", priority = 8)
    @Description("Plan deletion by id")
    public void checkPlanDeletionById() {
        int statusCode = new PlanAdapter().deletePlanById(project.getCode(), PLAN_ID).getStatusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match");
    }
}
