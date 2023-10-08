package service;

import io.qameta.allure.Step;
import page.ProjectsPage;

public class ProjectsPageService {

    private ProjectsPage projectsPage;

    @Step("Verify if projects list is displayed")
    public boolean isProjectsListDisplayed() {
        projectsPage = new ProjectsPage();
        return projectsPage.isProjectsListDisplayed();
    }

    @Step("Clicking create new project button")
    public ProjectsPageService clickCreateNewProjectButton() {
        projectsPage = new ProjectsPage();
        projectsPage.clickCreateNewProjectButton();
        return this;
    }
}
