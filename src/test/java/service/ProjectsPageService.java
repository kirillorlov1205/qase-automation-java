package service;

import page.ProjectsPage;

public class ProjectsPageService {

    private ProjectsPage projectsPage;

    public boolean isProjectsListDisplayed() {
        projectsPage = new ProjectsPage();
        return projectsPage.isProjectsListDisplayed();
    }
}
