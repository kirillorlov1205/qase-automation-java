package service;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import model.Project;
import page.ProjectsListPage;

@Log4j2
public class ProjectsListPageService {

    private ProjectsListPage projectsListPage;

    @Step("Verify if projects page is displayed")
    public boolean isProjectsPageDisplayed() {
        projectsListPage = new ProjectsListPage();
        return projectsListPage.isProjectsPageDisplayed();
    }

    @Step("Creating a new project")
    public NewProjectPageService createNewProject(Project project) {
        log.info("Create new project");
        projectsListPage = new ProjectsListPage();
        projectsListPage.clickCreateNewProjectButton()
                .fillProjectName(project.getProjectName())
                .fillProjectCode(project.getProjectCode())
                .fillProjectDescription(project.getProjectDescription())
                .selectProjectAccessType(project.getProjectAccessType())
                .submitProjectCreation();
        return new NewProjectPageService();
    }

    @Step("Removing created project")
    public ProjectsListPageService removeCreatedProjects() {
        projectsListPage = new ProjectsListPage();
        projectsListPage.removeCreatedProjects();
        return this;
    }

    @Step("Opening projects list page")
    public ProjectsListPageService openProjectsListPage() {
        projectsListPage = new ProjectsListPage();
        projectsListPage.openProjectsListPage();
        return this;
    }
}
