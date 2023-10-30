package api.tests;

import api.adapters.MilestoneAdapter;
import api.adapters.ProjectAdapter;
import api.models.Milestone;
import api.models.Project;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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

    @Test(description = "New Milestone creation", priority = 1)
    public void checkNewMilestoneCreation() {
        int expectedMilestoneId = 1;
        int actualMilestoneId = new MilestoneAdapter().createNewMilestone(project.getCode(), milestone).body().path(
                "result.id");
        Assert.assertEquals(actualMilestoneId, expectedMilestoneId, "Milestone id doesn't match expected");
    }

    @Test(description = "Milestone without title creation validation", priority = 2)
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

    @Test(description = "Get milestone by id", priority = 3)
    public void checkGetMilestoneById() {
        String createdMilestoneTitle = new MilestoneAdapter().getMilestoneById(project.getCode(), MILESTONE_ID).body()
                .path("result.title");
        Assert.assertEquals(createdMilestoneTitle, milestone.getTitle(), "Created milestone title doesn't " +
                "match expected");
    }

    @Test(description = "Get milestone by wrong id", priority = 4)
    public void checkGetMilestoneByWrongId() {
        String wrongMilestoneId = "/5";
        String expectedValidationMessage = "Milestone not found";
        String actualValidationMessage = new MilestoneAdapter().getMilestoneById(project.getCode(), wrongMilestoneId)
                .body().path("errorMessage");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Get all milestones", priority = 5)
    public void checkGetAllMilestones() {
        int expectedMilestonesQuantity = 1;
        int actualMilestonesQuantity = new MilestoneAdapter().getAllMilestonesByProjectCode(project.getCode()).body()
                .path("result.total");
        Assert.assertEquals(actualMilestonesQuantity, expectedMilestonesQuantity, "Milestones quantity " +
                "doesn't match expected");
    }

    @Test(description = "Milestone updating", priority = 6)
    public void checkMilestoneUpdating() {
        Milestone updateMilestone = Milestone.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        int statusCode = new MilestoneAdapter().updateMilestone(project.getCode(), MILESTONE_ID, updateMilestone)
                .statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match expected");
    }

    @Test(description = "Milestone deletion by id", priority = 7)
    public void checkMilestoneDeletionById() {
        int statusCode = new MilestoneAdapter().deleteMilestoneById(project.getCode(), MILESTONE_ID).getStatusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match");
    }
}
