package service;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import model.User;
import page.LoginPage;

@Log4j2
public class LoginPageService {

    private LoginPage loginPage;

    @Step("log in to the system")
    public ProjectsListPageService login(User user) {
        log.info("Log in to the system");
        loginPage = new LoginPage();
        loginPage.openLoginPage()
                .fillEmail(user.getEmail())
                .fillPassword(user.getPassword())
                .clickLoginButton();
        return new ProjectsListPageService();
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

    @Step("Clicking on additional link")
    public LoginPageService clickOnAdditionalLinkByName(String linkName) {
        loginPage = new LoginPage();
        loginPage.clickOnAdditionalLinkByName(linkName);
        return this;
    }

    @Step("Opening login page")
    public LoginPageService openLoginPage() {
        loginPage = new LoginPage();
        loginPage.openLoginPage();
        return this;
    }

    @Step("Getting login page url")
    public String getLoginPageUrl() {
        loginPage = new LoginPage();
        return loginPage.getLoginPageUrl();
    }

    @Step("Opening live chat")
    public LoginPageService openLiveChat() {
        loginPage = new LoginPage();
        loginPage.openLiveChat();
        return this;
    }

    @Step("Verifying if live chat opened")
    public boolean isLiveChatOpened() {
        loginPage = new LoginPage();
        return loginPage.isLiveChatOpened();
    }
}
