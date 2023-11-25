package ui.service;

import io.qameta.allure.Step;
import ui.page.PasswordResetPage;

public class PasswordResetPageService {
    private PasswordResetPage passwordResetPage;

    @Step("Filling 'Email' field")
    public PasswordResetPageService fillEmail(String email) {
        passwordResetPage = new PasswordResetPage();
        passwordResetPage.fillEmail(email);
        return this;
    }

    @Step("Clicking 'Send reset link' button")
    public PasswordResetPageService clickSendResetLinkButton() {
        passwordResetPage = new PasswordResetPage();
        passwordResetPage.clickSendResetLinkButton();
        return this;
    }

    @Step("Getting validation message")
    public String getValidationMessage() {
        passwordResetPage = new PasswordResetPage();
        return passwordResetPage.getValidationMessage();
    }

    @Step("Clicking 'Navigate to Login page' button")
    public PasswordResetPageService clickNavigateToLoginPageButton() {
        passwordResetPage = new PasswordResetPage();
        passwordResetPage.clickNavigateToLoginPageButton();
        return this;
    }

    @Step("Clicking 'Navigate to SSO Login page' button")
    public PasswordResetPageService clickNavigateToSsoLoginPageButton() {
        passwordResetPage = new PasswordResetPage();
        passwordResetPage.clickNavigateToSsoLoginPage();
        return this;
    }
}
