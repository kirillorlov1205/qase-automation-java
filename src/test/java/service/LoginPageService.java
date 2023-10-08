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
                .fillUsername(user.getEmail())
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

    public String getWrongEmailFormatValidationMessage() {
        loginPage = new LoginPage();
        return loginPage.getWrongEmailFormatValidationMessage();
    }

    public PasswordResetPageService clickForgotPasswordButton() {
        loginPage = new LoginPage();
        loginPage.openLoginPage()
                .clickForgotPasswordButton();
        return new PasswordResetPageService();
    }

    //input[@name='email']//ancestor::div[@class='tdishH']//small
//    @Step("Opening Login page")
//    public LoginPageService openLoginPage() {
//        loginPage = new LoginPage();
//        loginPage.openLoginPage();
//        return this;
//    }

//    @Step("Getting login page url")
//    public String getLoginPageUrl() {
//        return loginPage.getLoginPageUrl();
//    }
//

//    @Step("Getting login validation error text")
//    public String getLoginValidationErrorText() {
//        return loginPage.getLoginValidationErrorText();
//    }
}
