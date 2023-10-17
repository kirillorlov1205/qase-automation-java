package tests;

import driver.UiDriverActions;
import io.qameta.allure.Description;
import model.Constants;
import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.LoginPageService;
import service.ProjectsListPageService;

public class LoginPageTest extends BaseTest {

    private LoginPageService loginPageService;

    @BeforeClass
    public void setUp() {
        loginPageService = new LoginPageService();
    }

    @Test(description = "Verify successful login with valid credentials", priority = 1)
    @Description("Login with valid credentials")
    public void verifySuccessfulLoginWithValidCredentials() {
        ProjectsListPageService projectsListPageService = loginPageService.login(new User());
        Assert.assertTrue(projectsListPageService.isProjectsPageDisplayed(), "Login failed");
    }

    @Test(description = "Verify wrong email validation", priority = 2)
    @Description("Wrong email validation")
    public void verifyWrongEmailValidation() {
        User userWithWrongEmail = new User("wrongEmail@gmail.com", "TestingPass1!");
        loginPageService.login(userWithWrongEmail);
        String actualValidationMessage = loginPageService.getLoginValidationMessage();
        String expectedValidationMessage = Constants.INVALID_CREDENTIALS_VALIDATION_MESSAGE;
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Verify wrong password validation", priority = 3, dataProvider = "Wrong passwords list")
    @Description("Wrong password validation")
    public void verifyWrongPasswordValidation(String wrongPassword) {
        User userWithWrongPassword = new User("test12051@mail.ru", wrongPassword);
        loginPageService.login(userWithWrongPassword);
        String actualValidationMessage = loginPageService.getLoginValidationMessage();
        String expectedValidationMessage = Constants.INVALID_CREDENTIALS_VALIDATION_MESSAGE;
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Verify empty email validation", priority = 4)
    @Description("Empty email validation")
    public void verifyEmptyEmailValidation() {
        User userWithEmptyEmail = new User("", "TestingPass1!");
        loginPageService.login(userWithEmptyEmail);
        Assert.assertTrue(loginPageService.isEmptyEmailValidationMessageDisplayed(), "Empty email " +
                "validation message hasn't been shown");
    }

    @Test(description = "Verify empty password validation", priority = 5)
    @Description("Empty password validation")
    public void verifyEmptyPasswordValidation() {
        User userWithEmptyPassword = new User("test12051@mail.ru", "");
        loginPageService.login(userWithEmptyPassword);
        Assert.assertTrue(loginPageService.isEmptyPasswordValidationMessageDisplayed(), "Empty password " +
                "validation message hasn't been shown");
    }

    @Test(description = "Verify wrong email format validation", priority = 6, dataProvider = "Wrong format emails list")
    @Description("Wrong email format validation")
    public void verifyWrongEmailFormatValidation(String email) {
        User userWithWrongFormatEmail = new User(email);
        loginPageService.login(userWithWrongFormatEmail);
        String actualValidationMessage = loginPageService.getWrongEmailFormatValidationMessage();
        String expectedValidationMessage = String.format(Constants.INVALID_EMAIL_FORMAT_VALIDATION_MESSAGE,
                userWithWrongFormatEmail.getEmail());
        Assert.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message doesn't " +
                "match expected");
    }

    @Test(description = "Verify additional link transferring", priority = 7, dataProvider = "Additional links list")
    @Description("Additional link transferring")
    public void verifyAdditionalLinkTransferring(String linkName, String expectedPageUrl) {
        loginPageService.openLoginPage()
                .clickOnAdditionalLinkByName(linkName);
        String actualPageUrl = UiDriverActions.getSecondOpenedTabUrl();
        Assert.assertTrue(actualPageUrl.contains(expectedPageUrl), "Page url doesn't match expected");
    }

    @Test(description = "Verify live chat opening", priority = 8)
    @Description("Live chat opening")
    public void verifyLiveChatOpening() {
        loginPageService.openLoginPage()
                .openLiveChat();
        Assert.assertTrue(loginPageService.isLiveChatOpened(), "Live chat not opened");
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
                {"<h1>Testing</h1>"},
                {"<script>alert(123)</script>"},
                {"xxx@xxx.xxx' OR 1 = 1 LIMIT 1 -- ' ]"},
        };
    }

    @DataProvider(name = "Wrong passwords list")
    public Object[][] wrongPasswordsList() {
        return new Object[][]{
                {"WrongTestingPass1!"},
                {"<script>alert(123)</script>"},
                {"xxx@xxx.xxx' OR 1 = 1 LIMIT 1 -- ' ]"},
        };
    }

    @DataProvider(name = "Additional links list")
    public Object[][] additionalLinksList() {
        return new Object[][]
                {
                        {"YouTube", "https://www.youtube.com/playlist?list=PLt75o-m3IfmzbfsuO6Ey-mZgvEtLkWJnD"},
                        {"blog", "https://qase.io/blog/"},
                        {"Twitter", "https://twitter.com/qase_io"},
                        {"LinkedIn", "https://www.linkedin.com/company/qaseio/"},
                };
    }
}
