package api.tests;

import api.adapters.MilestoneAdapter;
import api.adapters.ProjectAdapter;
import api.models.Milestone;
import api.models.Project;
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Retry;

import static java.net.HttpURLConnection.HTTP_OK;
import static utils.TestDataGenerator.generateRandomAlphabeticString;

public class MilestoneTest {
    private static final String MILESTONE_ID = "/1";
    private Project project;
    private Milestone milestone;

    @BeforeClass
    public void setUp() {
        project = Project.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .code(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        milestone = Milestone.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .status("active")
                .dueDate(1732682459)
                .build();
        new ProjectAdapter().createNewProject(project);
    }

    @AfterClass
    public void deleteProject() {
        new ProjectAdapter().deleteProjectByCode(project.getCode());
    }

    @Test(description = "Check new Milestone creation", priority = 1, retryAnalyzer = Retry.class)
    @Description("New Milestone creation")
    public void checkNewMilestoneCreation() {
        int expectedMilestoneId = 1;
        int actualMilestoneId = new MilestoneAdapter().createNewMilestone(project.getCode(), milestone).body().path(
                "result.id");
        Assert.assertEquals(actualMilestoneId, expectedMilestoneId, "Milestone id doesn't match expected");
    }

    @Test(description = "Check milestone without title creation validation", priority = 2)
    @Description("Milestone without title creation validation")
    public void checkMilestoneWithoutTitleCreationValidation() {
        String expectedValidationMessage = "The title field is required.";
        Milestone milestoneWithoutTitle = Milestone.builder()
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        String actualValidationMessage = new MilestoneAdapter().createNewMilestone(project.getCode(),
                milestoneWithoutTitle).body().path("errorFields[0].error");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't" +
                " match expected");
    }

    @Test(description = "Check get milestone by id", priority = 3)
    @Description("Get milestone by id")
    public void checkGetMilestoneById() {
        String createdMilestoneTitle = new MilestoneAdapter().getMilestoneById(project.getCode(), MILESTONE_ID).body()
                .path("result.title");
        Assert.assertEquals(createdMilestoneTitle, milestone.getTitle(), "Created milestone title doesn't " +
                "match expected");
    }

    @Test(description = "Check get milestone by wrong id", priority = 4)
    @Description("Get milestone by wrong id")
    public void checkGetMilestoneByWrongId() {
        String wrongMilestoneId = "/5";
        String expectedValidationMessage = "Milestone not found";
        String actualValidationMessage = new MilestoneAdapter().getMilestoneById(project.getCode(), wrongMilestoneId)
                .body().path("errorMessage");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Check get all milestones", priority = 5)
    @Description("Get all milestones")
    public void checkGetAllMilestones() {
        int expectedMilestonesQuantity = 1;
        int actualMilestonesQuantity = new MilestoneAdapter().getAllMilestonesByProjectCode(project.getCode()).body()
                .path("result.total");
        Assert.assertEquals(actualMilestonesQuantity, expectedMilestonesQuantity, "Milestones quantity " +
                "doesn't match expected");
    }

    @Test(description = "Check milestone updating", priority = 6)
    @Description("Milestone updating")
    public void checkMilestoneUpdating() {
        Milestone updateMilestone = Milestone.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        int statusCode = new MilestoneAdapter().updateMilestone(project.getCode(), MILESTONE_ID, updateMilestone)
                .statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match expected");
    }

    @Test(description = "Check milestone deletion by id", priority = 7)
    @Description("Milestone deletion by id")
    public void checkMilestoneDeletionById() {
        int statusCode = new MilestoneAdapter().deleteMilestoneById(project.getCode(), MILESTONE_ID).getStatusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match");
    }
}
