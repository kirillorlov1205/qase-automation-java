package ui.tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ui.driver.UiDriverActions;
import ui.model.Case;
import ui.model.Project;
import ui.model.Step;
import ui.model.User;
import ui.service.CasePageService;
import ui.service.LoginPageService;
import ui.service.ProjectPageService;
import ui.service.ProjectsListPageService;
import utils.Enums;
import utils.TestDataGenerator;

import java.util.List;

import static utils.TestDataGenerator.generateRandomAlphabeticString;
import static utils.TestDataGenerator.generateRandomString;

public class CasePageTest extends BaseTest {
    private LoginPageService loginPageService;
    private ProjectPageService projectPageService;
    private CasePageService casePageService;
    private Project project;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
        projectPageService = new ProjectPageService();
        casePageService = new CasePageService();
        project = Project.builder()
                .projectName(generateRandomAlphabeticString(2, 10))
                .projectCode("")
                .projectDescription(generateRandomString(2, 10))
                .projectAccessType(Enums.ProjectAccessTypes.Public)
                .build();
    }

    @AfterClass
    public void clean() {
        ProjectsListPageService projectsListPageService = new ProjectsListPageService();
        projectsListPageService.deleteProjectByCode(project.getProjectCode());
    }

    @Test(description = "Check successful case with steps creation", priority = 1)
    @Description("Successful case with steps creation")
    public void checkSuccessfulCaseWithStepsCreation() {
        Case testCase = Case.builder()
                .title("caseWithStep")
                .steps(List.of(new Step())).build();
        loginPageService.login(new User())
                .createNewProject(project)
                .clickCreateCaseButton()
                .createTestCase(testCase)
                .clickOnTestCaseByTitle(testCase.getTitle());
        Assert.assertTrue(projectPageService.isCaseWithStepsCreatedInProject(testCase), "New case with " +
                "steps not created");
    }

    @Test(description = "Check successful case with attachment creation ", priority = 2)
    @Description("Successful case with attachment creation")
    public void checkSuccessfulCaseWithAttachmentCreation() {
        String attachmentName = "testAttachment";
        Case testCase = Case.builder()
                .title("caseWithAttachment").build();
        projectPageService.clickCreateCaseButton()
                .fillTitleField(testCase.getTitle())
                .addAttachment(attachmentName)
                .clickSaveButton()
                .clickOnTestCaseByTitle(testCase.getTitle());
        Assert.assertTrue(projectPageService.isCaseWithAttachmentCreatedInProject(attachmentName), "New " +
                "case with attachment not created");
    }

    @Test(description = "Check successful case with title only creation", priority = 3)
    @Description("Successful case with title only creation")
    public void checkSuccessfulCaseWithTitleOnlyCreation() {
        Case testCase = Case.builder()
                .title(generateRandomString(2, 10)).build();
        projectPageService.clickCreateCaseButton()
                .fillTitleField(testCase.getTitle())
                .clickSaveButton();
        Assert.assertTrue(projectPageService.isCaseCreatedInProject(testCase), "New case with title only " +
                "not created");
    }

    @Test(description = "Check title format validation", priority = 4, dataProvider = "getValidCaseTitlesList")
    @Description("Title format validation")
    public void checkTitleFormatValidation(String caseTitle) {
        Case testCase = Case.builder()
                .title(caseTitle).build();
        projectPageService.clickCreateCaseButton()
                .fillTitleField(testCase.getTitle())
                .clickSaveButton();
        Assert.assertTrue(projectPageService.isCaseCreatedInProject(testCase), "New case not created");
    }

    @Test(description = "Check empty title validation", priority = 5)
    @Description("Empty title validation")
    public void checkEmptyTitleValidation() {
        Case testCase = Case.builder()
                .title("").build();
        projectPageService.clickCreateCaseButton()
                .fillTitleField(testCase.getTitle())
                .clickSaveButton();
        boolean isProjectPageNotOpened = projectPageService.isProjectPageNotOpened();
        casePageService.clickBackToProjectButton();
        Assert.assertTrue(isProjectPageNotOpened, "Empty title validation failed");
    }

    @Test(description = "Check more than limit title validation", priority = 6)
    @Description("More than limit title validation")
    public void checkMoreThanLimitTitleValidation() {
        Case testCase = Case.builder()
                .title(generateRandomString(256, 256))
                .build();
        projectPageService.clickCreateCaseButton()
                .fillTitleField(testCase.getTitle())
                .clickSaveButton();
        boolean isMoreThanLimitTitleValidationMessageDisplayed = casePageService
                .isMoreThanLimitTitleValidationMessageDisplayed();
        casePageService.clickBackToProjectButton();
        UiDriverActions.acceptAlert();
        Assert.assertTrue(isMoreThanLimitTitleValidationMessageDisplayed, "More than limit title validation " +
                "message not displayed");
    }

    @DataProvider(name = "Valid case titles list")
    private Object[][] getValidCaseTitlesList() {
        return new Object[][]{
                {TestDataGenerator.generateRandomNumericString(2, 10)},
                {TestDataGenerator.generateRandomAlphabeticString(2, 10)},
                {"!@#$%^&*()_/|<>~+="},
                {"A"},
                {"<h1>Testing</h1>"},
                {"<script>alert(123)</script>"},
        };
    }
}
