package ui.service;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import ui.model.Project;
import ui.page.ProjectsListPage;
import utils.Enums;

@Log4j2
public class ProjectsListPageService {

    private ProjectsListPage projectsListPage;

    @Step("Verify if projects ui.page displayed")
    public boolean isProjectsPageDisplayed() {
        projectsListPage = new ProjectsListPage();
        return projectsListPage.isProjectsPageDisplayed();
    }

    @Step("Creating a new project")
    public ProjectPageService createNewProject(Project project) {
        log.info("Create a new project");
        projectsListPage = new ProjectsListPage();
        projectsListPage.clickCreateNewProjectButton()
                .fillProjectName(project.getProjectName())
                .fillProjectCode(project.getProjectCode())
                .fillProjectDescription(project.getProjectDescription())
                .selectProjectAccessType(project.getProjectAccessType())
                .submitProjectCreation();
        return new ProjectPageService();
    }

    @Step("Selecting project access type")
    public ProjectPageService selectProjectAccessType(Enums.ProjectAccessTypes projectAccessType) {
        projectsListPage = new ProjectsListPage();
        projectsListPage.selectProjectAccessType(projectAccessType);
        return new ProjectPageService();
    }

    @Step("Removing created project")
    public ProjectsListPageService removeCreatedProjects() {
        projectsListPage = new ProjectsListPage();
        projectsListPage.removeCreatedProjects();
        return this;
    }

    @Step("Opening 'Projects list' ui.page")
    public ProjectsListPageService openProjectsListPage() {
        projectsListPage = new ProjectsListPage();
        projectsListPage.openProjectsListPage();
        return this;
    }

    @Step("Getting invalid code validation message")
    public String getInvalidCodeValidationMessage() {
        projectsListPage = new ProjectsListPage();
        return projectsListPage.getInvalidCodeValidationMessage();
    }

    @Step("Verifying if project code field empty")
    public boolean isProjectCodeFieldEmpty() {
        projectsListPage = new ProjectsListPage();
        return projectsListPage.isProjectCodeFieldEmpty();
    }

    @Step("Verifying if private member access displayed")
    public boolean isPrivateMemberAccessDisplayed() {
        projectsListPage = new ProjectsListPage();
        return projectsListPage.isPrivateMemberAccessDisplayed();
    }

    @Step("Clicking 'create new project' button")
    public ProjectsListPageService clickCreateNewProjectButton() {
        projectsListPage = new ProjectsListPage();
        projectsListPage.clickCreateNewProjectButton();
        return this;
    }

    @Step("Filling 'project name' field")
    public ProjectsListPageService fillProjectNameField(String projectName) {
        projectsListPage = new ProjectsListPage();
        projectsListPage.fillProjectName(projectName);
        return this;
    }
}
