package tests;

import io.qameta.allure.Description;
import model.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import service.LoginPageService;

public class ProjectsPageTest extends BaseTest {

    private LoginPageService loginPageService;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
    }

//    @Test(description = "Verify successful project creation", priority = 1)
//    @Description("Successful project creation")
//    public void verifySuccessfulProjectCreation() {
//        loginPageService.login(Constants.USER_WITH_VALID_CREDENTIALS)
//
//        String expectedValidationMessage = Constants.SUCCESSFUL_PASSWORD_RESET_VALIDATION_MESSAGE;
//        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
//                "match expected");
//    }
}
