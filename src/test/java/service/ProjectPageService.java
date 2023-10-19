package service;

import io.qameta.allure.Step;
import model.Case;
import page.ProjectPage;

public class ProjectPageService {

    private ProjectPage projectPage;

    @Step("Getting project title")
    public String getProjectTitle() {
        projectPage = new ProjectPage();
        return projectPage.getProjectTitle();
    }

    @Step("Verifying if 'Project page' not opened")
    public boolean isProjectPageNotOpened() {
        projectPage = new ProjectPage();
        return projectPage.isProjectPageNotOpened();
    }

    @Step("Clicking 'Create case' button")
    public CasePageService clickCreateCaseButton() {
        projectPage = new ProjectPage();
        projectPage.clickCreateCaseButton();
        return new CasePageService();
    }

    @Step("Verifying if case created in project")
    public boolean isCaseCreatedInProject(Case testCase) {
        projectPage = new ProjectPage();
        return projectPage.isCaseCreatedInProject(testCase.getTitle());
    }

    @Step("Clicking on test case")
    public ProjectPageService clickOnTestCaseByTitle(String caseTitle) {
        projectPage = new ProjectPage();
        projectPage.clickOnTestCaseByTitle(caseTitle);
        return this;
    }

    @Step("Verifying if case with steps created in project")
    public boolean isCaseWithStepsCreatedInProject(Case testCase) {
        projectPage = new ProjectPage();
        return projectPage.isCaseWithStepsCreatedInProject(testCase.getStepByIndex(0).getStepAction());
    }

    @Step("Verifying if case with attachment created in project")
    public boolean isCaseWithAttachmentCreatedInProject(String attachmentName) {
        projectPage = new ProjectPage();
        return projectPage.isCaseWithAttachmentCreatedInProject(attachmentName);
    }
}
