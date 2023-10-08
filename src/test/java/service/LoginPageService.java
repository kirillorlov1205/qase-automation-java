package service;

import io.qameta.allure.Step;
import model.User;
import page.LoginPage;

public class LoginPageService {

    private LoginPage loginPage;

    @Step("log in to the system")
    public ProjectsPageService login(User user) {
        loginPage = new LoginPage();
        loginPage.openLoginPage()
                .fillEmail(user.getEmail())
                .fillPassword(user.getPassword())
                .clickLoginButton();
        return new ProjectsPageService();
    }

    @Step("Getting log in validation message")
    public String getLoginValidationMessage() {
        loginPage = new LoginPage();
        return loginPage.getLoginValidationMessage();
    }

    @Step("Verifying if empty email validation message displayed")
    public boolean isEmptyEmailValidationMessageDisplayed() {
        loginPage = new LoginPage();
        return loginPage.isEmptyFieldValidationMessageDisplayed("email");
    }

    @Step("Verifying if empty password validation message displayed")
    public boolean isEmptyPasswordValidationMessageDisplayed() {
        loginPage = new LoginPage();
        return loginPage.isEmptyFieldValidationMessageDisplayed("password");
    }

    @Step("Getting wrong email format validation message")
    public String getWrongEmailFormatValidationMessage() {
        loginPage = new LoginPage();
        return loginPage.getWrongEmailFormatValidationMessage();
    }

    @Step("Clicking forgot password button")
    public PasswordResetPageService clickForgotPasswordButton() {
        loginPage = new LoginPage();
        loginPage.openLoginPage()
                .clickForgotPasswordButton();
        return new PasswordResetPageService();
    }

    @Step("Verifying if Login page opened")
    public boolean isLoginPageOpened() {
        loginPage = new LoginPage();
        return loginPage.isLoginPageOpened();
    }

    @Step("Verifying if SSO Login page opened")
    public boolean isSsoLoginPageOpened() {
        loginPage = new LoginPage();
        return loginPage.isSsoLoginPageOpened();
    }
}
