package ui.tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ui.model.User;
import ui.service.LoginPageService;
import utils.DataProviders;

public class PasswordResetPageTest extends BaseTest {
    private static final String SUCCESSFUL_PASSWORD_RESET_VALIDATION_MESSAGE = "We have e-mailed your password " +
            "reset link!";
    private static final String USER_NOT_FOUND_VALIDATION_MESSAGE = "User not found.";
    private static final String INVALID_EMAIL_FORMAT_VALIDATION_MESSAGE = "Value '%s' does not match format email " +
            "of type string";
    private LoginPageService loginPageService;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
    }

    @Test(description = "Check successful password reset", priority = 1)
    @Description("Successful password reset")
    public void checkSuccessfulPasswordReset() {
        String actualValidationMessage = loginPageService
                .clickForgotPasswordButton()
                .fillEmail(new User().getEmail())
                .clickSendResetLinkButton()
                .getValidationMessage();
        String expectedValidationMessage = SUCCESSFUL_PASSWORD_RESET_VALIDATION_MESSAGE;
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Check wrong reset password email validation", priority = 2)
    @Description("Wrong reset password email validation")
    public void checkWrongResetPasswordEmailValidation() {
        User userWithWrongEmail = new User("wrongEmail@gmail.com", "TestingPass1!");
        String actualValidationMessage = loginPageService
                .clickForgotPasswordButton()
                .fillEmail(userWithWrongEmail.getEmail())
                .clickSendResetLinkButton()
                .getValidationMessage();
        String expectedValidationMessage = USER_NOT_FOUND_VALIDATION_MESSAGE;
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Check wrong reset password email format validation", priority = 3, dataProvider = "Wrong " +
            "format emails list", dataProviderClass = DataProviders.class)
    @Description("Wrong reset password email format validation")
    public void checkWrongResetPasswordEmailFormatValidation(String email) {
        String actualValidationMessage = loginPageService
                .clickForgotPasswordButton()
                .fillEmail(email)
                .clickSendResetLinkButton()
                .getValidationMessage();
        String expectedValidationMessage = String.format(INVALID_EMAIL_FORMAT_VALIDATION_MESSAGE, email);
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Check navigation to 'Login page'", priority = 4)
    @Description("Navigation to 'Login page'")
    public void checkNavigationToLoginPage() {
        loginPageService.clickForgotPasswordButton()
                .clickNavigateToLoginPageButton();
        Assert.assertTrue(loginPageService.isLoginPageOpened(), "Login page not opened");
    }

    @Test(description = "Check navigation to SSO Login page", priority = 4)
    @Description("Navigation to 'SSO Login page'")
    public void checkNavigationToSsoLoginPage() {
        loginPageService.clickForgotPasswordButton()
                .clickNavigateToSsoLoginPageButton();
        Assert.assertTrue(loginPageService.isSsoLoginPageOpened(), "Login page hasn't been opened");
    }
}
