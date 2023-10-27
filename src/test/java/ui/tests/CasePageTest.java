package ui.tests;

import ui.driver.UiDriverActions;
import io.qameta.allure.Description;
import ui.model.Case;
import ui.model.Project;
import ui.model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ui.service.CasePageService;
import ui.service.LoginPageService;
import ui.service.ProjectPageService;
import utils.TestDataGenerator;

public class CasePageTest extends BaseTest {

    private LoginPageService loginPageService;
    private ProjectPageService projectPageService;
    private CasePageService casePageService;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
        projectPageService = new ProjectPageService();
        casePageService = new CasePageService();
    }

    @Test(description = "Verify successful case with steps creation", priority = 1)
    @Description("Successful case with steps creation")
    public void verifySuccessfulCaseWithStepsCreation() {
        Case testCase = new Case();
        loginPageService.login(new User())
                .createNewProject(new Project())
                .clickCreateCaseButton()
                .createTestCase(testCase)
                .clickOnTestCaseByTitle(testCase.getTitle());
        Assert.assertTrue(projectPageService.isCaseWithStepsCreatedInProject(testCase), "New case with " +
                "steps not created");
    }

    @Test(description = "Verify successful case with title only creation", priority = 2)
    @Description("Successful case with title only creation")
    public void verifySuccessfulCaseWithTitleOnlyCreation() {
        Case testCase = new Case();
        projectPageService.clickCreateCaseButton()
                .fillTitleField(testCase.getTitle())
                .clickSaveButton();
        Assert.assertTrue(projectPageService.isCaseCreatedInProject(testCase), "New case with title only " +
                "not created");
    }

    @Test(description = "Verify successful case with attachment creation ", priority = 3)
    @Description("Successful case with attachment creation")
    public void verifySuccessfulCaseWithAttachmentCreation() {
        String attachmentName = "testAttachment";
        Case testCase = new Case();
        projectPageService.clickCreateCaseButton()
                .fillTitleField(testCase.getTitle())
                .addAttachment(attachmentName)
                .clickSaveButton()
                .clickOnTestCaseByTitle(testCase.getTitle());
        Assert.assertTrue(projectPageService.isCaseWithAttachmentCreatedInProject(attachmentName), "New " +
                "case with attachment not created");
    }

    @Test(description = "Verify title format validation", priority = 4, dataProvider = "Valid case titles list")
    @Description("Title format validation")
    public void verifyTitleFormatValidation(String caseTitle) {
        Case testCase = new Case(caseTitle);
        projectPageService.clickCreateCaseButton()
                .fillTitleField(testCase.getTitle())
                .clickSaveButton();
        Assert.assertTrue(projectPageService.isCaseCreatedInProject(testCase), "New case not created");
    }

    @Test(description = "Verify empty title validation", priority = 5)
    @Description("Empty title validation")
    public void verifyEmptyTitleValidation() {
        SoftAssert softAssert = new SoftAssert();
        Case testCase = new Case("");
        projectPageService.clickCreateCaseButton()
                .fillTitleField(testCase.getTitle())
                .clickSaveButton();
        softAssert.assertTrue(projectPageService.isProjectPageNotOpened(), "Empty title validation failed");
        casePageService.clickBackToProjectButton();
        softAssert.assertAll();
    }

    @Test(description = "Verify more than limit title validation", priority = 6)
    @Description("More than limit title validation")
    public void verifyMoreThanLimitTitleValidation() {
        SoftAssert softAssert = new SoftAssert();
        Case testCase = new Case(TestDataGenerator.generateRandomString(256, 256));
        projectPageService.clickCreateCaseButton()
                .fillTitleField(testCase.getTitle())
                .clickSaveButton();
        softAssert.assertTrue(casePageService.isMoreThanLimitTitleValidationMessageDisplayed(), "More than " +
                "limit title validation message not displayed");
        casePageService.clickBackToProjectButton();
        UiDriverActions.acceptAlert();
        softAssert.assertAll();
    }

    @DataProvider(name = "Valid case titles list")
    public Object[][] validCaseTitlesList() {
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
