package service;

import io.qameta.allure.Step;
import page.NewProjectPage;

public class NewProjectPageService {

    private NewProjectPage newProjectPage;

    @Step("Getting new project title")
    public String getNewProjectTitle() {
        newProjectPage = new NewProjectPage();
        return newProjectPage.getNewProjectTitle();
    }

    @Step("Verifying if new project page opened")
    public boolean isNewProjectPageOpened() {
        newProjectPage = new NewProjectPage();
        return newProjectPage.isNewProjectPageOpened();
    }
}
