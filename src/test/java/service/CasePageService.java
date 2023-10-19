package service;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import model.Case;
import page.CasePage;

@Log4j2
public class CasePageService {

    private CasePage casePage;

    @Step("Filling 'Title' field")
    public CasePageService fillTitleField(String caseTitle) {
        casePage = new CasePage();
        casePage.fillTitleField(caseTitle);
        return this;
    }

    @Step("Clicking 'Save' button")
    public ProjectPageService clickSaveButton() {
        casePage = new CasePage();
        casePage.clickSaveButton();
        return new ProjectPageService();
    }

    @Step("Verifying if more than limit title validation message displayed")
    public boolean isMoreThanLimitTitleValidationMessageDisplayed() {
        casePage = new CasePage();
        return casePage.isMoreThanLimitTitleValidationMessageDisplayed();
    }

    @Step("Clicking 'Back to project' button")
    public ProjectPageService clickBackToProjectButton() {
        casePage = new CasePage();
        casePage.clickBackToProjectButton();
        return new ProjectPageService();
    }

    @Step("Creating test case")
    public ProjectPageService createTestCase(Case testCase) {
        log.info("Create a new test case");
        casePage = new CasePage();
        casePage.fillTitleField(testCase.getTitle())
                .clickAddStepButton()
                .fillStepsFieldByFieldName("Step Action", testCase.getStepByIndex(0).getStepAction())
                .fillStepsFieldByFieldName("Data", testCase.getStepByIndex(0).getData())
                .fillStepsFieldByFieldName("Expected result", testCase.getStepByIndex(0).getExpectedResult())
                .clickSaveButton();
        return new ProjectPageService();
    }

    @Step("Adding an attachment")
    public CasePageService addAttachment(String attachmentName) {
        log.info("Add an attachment to test case");
        casePage = new CasePage();
        casePage.clickAddAttachmentButton()
                .clickBrowseNavigationButton()
                .selectAttachment(attachmentName);
        return this;
    }
}
