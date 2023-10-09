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
}
