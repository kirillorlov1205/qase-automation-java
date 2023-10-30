package api.tests;

import api.adapters.DefectAdapter;
import api.adapters.ProjectAdapter;
import api.models.Defect;
import api.models.Project;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static java.net.HttpURLConnection.HTTP_OK;
import static utils.TestDataGenerator.generateRandomAlphabeticString;

public class DefectTest {
    private static final String DEFECT_ID = "/1";
    private Project project;
    private Defect defect;

    @BeforeClass
    public void setUp() {
        project = Project.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .code(generateRandomAlphabeticString(2, 10))
                .description(generateRandomAlphabeticString(2, 10))
                .build();
        defect = Defect.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .severity(1)
                .actualResult(generateRandomAlphabeticString(2, 10))
                .build();
        new ProjectAdapter().createNewProject(project);
    }

    @AfterClass
    public void deleteProject() {
        new ProjectAdapter().deleteProjectByCode(project.getCode());
    }

    @Test(description = "New Defect creation", priority = 1)
    public void checkNewDefectCreation() {
        int expectedDefectId = 1;
        int actualDefectId = new DefectAdapter().createNewDefect(project.getCode(), defect).body().path(
                "result.id");
        Assert.assertEquals(actualDefectId, expectedDefectId, "Defect id doesn't match expected");
    }

    @Test(description = "Defect without title creation validation", priority = 2)
    public void checkDefectWithoutTitleCreationValidation() {
        String expectedValidationMessage = "The title field is required.";
        Defect defectWithoutTitle = Defect.builder()
                .actualResult(generateRandomAlphabeticString(2, 10))
                .severity(1)
                .build();
        String actualValidationMessage = new DefectAdapter().createNewDefect(project.getCode(),
                defectWithoutTitle).body().path("errorFields[0].error");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't" +
                " match expected");
    }

    @Test(description = "Defect without 'actual result' creation validation", priority = 3)
    public void checkDefectWithoutActualResultCreationValidation() {
        String expectedValidationMessage = "The actual result field is required.";
        Defect defectWithoutActualResult = Defect.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .severity(1)
                .build();
        String actualValidationMessage = new DefectAdapter().createNewDefect(project.getCode(),
                defectWithoutActualResult).body().path("errorFields[0].error");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't" +
                " match expected");
    }

    @Test(description = "Defect with zero 'severity' creation", priority = 4)
    public void checkDefectWithZeroSeverityCreation() {
        Defect defectWithZeroSeverity = Defect.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .actualResult(generateRandomAlphabeticString(2, 10))
                .severity(0)
                .build();
        int statusCode = new DefectAdapter().createNewDefect(project.getCode(), defectWithZeroSeverity).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Validation message doesn't match expected");
    }

    @Test(description = "Defect without 'title' and 'actual result' creation", priority = 5)
    public void checkDefectWithoutTitleAndActualResultCreation() {
        String expectedEmptyActualResultValidationMessage = "The actual result field is required.";
        String expectedEmptyTitleValidationMessage = "The title field is required.";
        Defect defectWithoutActualResultAndTitle = Defect.builder()
                .severity(1)
                .build();
        String actualEmptyTitleValidationMessage = new DefectAdapter().createNewDefect(project.getCode(),
                defectWithoutActualResultAndTitle).body().path("errorFields[0].error");
        String actualEmptyActualResultValidationMessage = new DefectAdapter().createNewDefect(project.getCode(),
                defectWithoutActualResultAndTitle).body().path("errorFields[1].error");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(actualEmptyActualResultValidationMessage, expectedEmptyActualResultValidationMessage,
                "empty actual result validation message doesn't match expected");
        softAssert.assertEquals(actualEmptyTitleValidationMessage, expectedEmptyTitleValidationMessage,
                "empty actual result validation message doesn't match expected");
        softAssert.assertAll();
    }

    @Test(description = "Get defect by id", priority = 6)
    public void checkGetDefectById() {
        String createdDefectTitle = new DefectAdapter().getDefectById(project.getCode(), DEFECT_ID).body().path(
                "result.title");
        Assert.assertEquals(createdDefectTitle, defect.getTitle(), "Created defect title doesn't match " +
                "expected");
    }

    @Test(description = "Get defect by wrong id", priority = 7)
    public void checkGetDefectByWrongId() {
        String wrongDefectId = "/5";
        String expectedValidationMessage = "Defect not found";
        String actualValidationMessage = new DefectAdapter().getDefectById(project.getCode(), wrongDefectId).body()
                .path("errorMessage");
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Get all defects", priority = 8)
    public void checkGetAllDefects() {
        int expectedDefectsQuantity = 2;
        int actualDefectQuantity = new DefectAdapter().getAllDefectsByProjectCode(project.getCode()).body().path(
                "result.total");
        Assert.assertEquals(actualDefectQuantity, expectedDefectsQuantity, "Defects quantity doesn't match " +
                "expected");
    }

    @Test(description = "Defect updating", priority = 9)
    public void checkDefectUpdating() {
        Defect updateDefect = Defect.builder()
                .title(generateRandomAlphabeticString(2, 10))
                .actualResult(generateRandomAlphabeticString(2, 10))
                .severity(1)
                .build();
        int statusCode = new DefectAdapter().updateDefect(project.getCode(), DEFECT_ID, updateDefect).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match expected");
    }

    @Test(description = "Defect specific status updating", priority = 10)
    public void checkDefectSpecificStatusUpdating() {
        Defect defectWithStatus = Defect.builder()
                .status("in_progress")
                .build();
        int statusCode = new DefectAdapter().updateDefectSpecificStatus(project.getCode(), DEFECT_ID, defectWithStatus)
                .statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match expected");
    }

    @Test(description = "Specific defect resolving", priority = 11)
    public void checkSpecificDefectResolving() {
        int statusCode = new DefectAdapter().resolveSpecificDefect(project.getCode(), DEFECT_ID).statusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match expected");
    }

    @Test(description = "Defect deletion by id", priority = 12)
    public void checkDefectDeletionById() {
        int statusCode = new DefectAdapter().deleteDefectById(project.getCode(), DEFECT_ID).getStatusCode();
        Assert.assertEquals(statusCode, HTTP_OK, "Status code doesn't match");
    }
}
