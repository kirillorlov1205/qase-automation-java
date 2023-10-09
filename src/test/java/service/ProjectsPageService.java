package service;

import io.qameta.allure.Step;
import model.Project;
import page.ProjectsPage;

public class ProjectsPageService {

    private ProjectsPage projectsPage;

    @Step("Verify if projects page is displayed")
    public boolean isProjectsPageDisplayed() {
        projectsPage = new ProjectsPage();
        return projectsPage.isProjectsPageDisplayed();
    }

    @Step("Creating a new project")
    public NewProjectPageService createNewProject(Project project) {
        projectsPage = new ProjectsPage();
        projectsPage.clickCreateNewProjectButton()
                .fillProjectName(project.getProjectName())
                .fillProjectCode(project.getProjectCode())
                .fillProjectDescription(project.getProjectDescription())
                .selectProjectAccessType(project.getProjectAccessType())
                .submitProjectCreation();
        return new NewProjectPageService();
    }

//    @Step("Removing created project")
//    public ProjectsPageService removeCreatedProject() {
//        projectsPage = new ProjectsPage();
//        projectsPage.openProjectsPage()
//                .clickProjectDropdownButton()
//                .clickRemoveProjectButton()
//                .submitProjectRemoving();
//        return this;
//    }
}
