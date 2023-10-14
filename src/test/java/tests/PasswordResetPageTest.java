package tests;

import io.qameta.allure.Description;
import model.Constants;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.LoginPageService;

public class PasswordResetPageTest extends BaseTest {

    private LoginPageService loginPageService;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
    }

    @Test(description = "Verify successful password reset", priority = 1)
    @Description("Successful password reset")
    public void verifySuccessfulPasswordReset() {
        String actualValidationMessage = loginPageService
                .clickForgotPasswordButton()
                .fillEmail(new User().getEmail())
                .clickSendResetLinkButton()
                .getValidationMessage();
        String expectedValidationMessage = Constants.SUCCESSFUL_PASSWORD_RESET_VALIDATION_MESSAGE;
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Verify wrong reset password email validation", priority = 2)
    @Description("Wrong reset password email validation")
    public void verifyWrongResetPasswordEmailValidation() {
        User userWithWrongEmail = new User("wrongEmail@gmail.com", "TestingPass1!");
        String actualValidationMessage = loginPageService
                .clickForgotPasswordButton()
                .fillEmail(userWithWrongEmail.getEmail())
                .clickSendResetLinkButton()
                .getValidationMessage();
        String expectedValidationMessage = Constants.USER_NOT_FOUND_VALIDATION_MESSAGE;
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Verify wrong reset password email format validation", priority = 3, dataProvider = "Wrong " +
            "format emails list")
    @Description("Wrong reset password email format validation")
    public void verifyWrongResetPasswordEmailFormatValidation(String email) {
        String actualValidationMessage = loginPageService
                .clickForgotPasswordButton()
                .fillEmail(email)
                .clickSendResetLinkButton()
                .getValidationMessage();
        String expectedValidationMessage = String.format(Constants.INVALID_EMAIL_FORMAT_VALIDATION_MESSAGE, email);
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Verify navigation to Login page", priority = 4)
    @Description("Navigation to Login page")
    public void verifyNavigationToLoginPage() {
        loginPageService.clickForgotPasswordButton()
                .clickNavigateToLoginPageButton();
        Assert.assertTrue(loginPageService.isLoginPageOpened(), "Login page hasn't been opened");
    }

    @Test(description = "Verify navigation to SSO Login page", priority = 4)
    @Description("Navigation to SSO Login page")
    public void verifyNavigationToSsoLoginPage() {
        loginPageService.clickForgotPasswordButton()
                .clickNavigateToSsoLoginPageButton();
        Assert.assertTrue(loginPageService.isSsoLoginPageOpened(), "Login page hasn't been opened");
    }

    @DataProvider(name = "Wrong format emails list")
    public Object[][] wrongFormatEmailsList() {
        return new Object[][]{
                {"abc.def@mail#archive.com"},
                {"abc..def@mail.com"},
                {".abc@mail.com"},
                {"abc.def@mail"},
                {"abc.def@mail..com"},
                {"email.domain.com"},
                {"email@domain@domain.com"},
                {"email.@domain.com"},
                {"mail@-domain.com"},
                {"あいうえお@domain.com"},
                {"@domain.com"},
                {"<script>alert(123)</script>"},
                {"xxx@xxx.xxx' OR 1 = 1 LIMIT 1 -- ' ]"},
        };
    }
}
