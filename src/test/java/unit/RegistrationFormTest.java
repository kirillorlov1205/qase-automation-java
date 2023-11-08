package unit;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import registrationForm.RegistrationForm;
import registrationForm.User;
import utils.TestDataGenerator;

import static utils.StringConstant.*;

public class RegistrationFormTest {
    private static final RegistrationForm registrationForm = new RegistrationForm();
    private static final User USER_WITH_VALID_CREDENTIALS = User.builder()
            .email("testEmail@gmail.com")
            .login("Login12")
            .password("Password12")
            .confirmPassword("Password12")
            .build();

    @Test(testName = "Check successful registration", priority = 1)
    @Description("Successful registration")
    public void checkSuccessfulRegistration() {
        String validationMessage = registrationForm.register(USER_WITH_VALID_CREDENTIALS);
        Assert.assertEquals(validationMessage, SUCCESSFUL_REGISTRATION_MESSAGE, "Validation message doesn't" +
                "match expected");
    }

    @Test(testName = "Check valid email validation", priority = 2, dataProvider = "Valid emails list")
    @Description("Valid email validation")
    public void checkValidEmailValidation(String validEmail) {
        User userWithValidEmail = User.builder()
                .email(validEmail)
                .login(USER_WITH_VALID_CREDENTIALS.getLogin())
                .password(USER_WITH_VALID_CREDENTIALS.getPassword())
                .confirmPassword(USER_WITH_VALID_CREDENTIALS.getConfirmPassword())
                .build();
        String validationMessage = registrationForm.register(userWithValidEmail);
        Assert.assertEquals(validationMessage, SUCCESSFUL_REGISTRATION_MESSAGE, "Validation message doesn't" +
                "match expected");
    }

    @Test(testName = "Check invalid email validation", priority = 3, dataProvider = "Invalid emails list")
    @Description("Invalid email validation")
    public void checkInvalidEmailValidation(String invalidEmail) {
        User userWithInvalidEmail = User.builder()
                .email(invalidEmail)
                .login(USER_WITH_VALID_CREDENTIALS.getLogin())
                .password(USER_WITH_VALID_CREDENTIALS.getPassword())
                .confirmPassword(USER_WITH_VALID_CREDENTIALS.getConfirmPassword())
                .build();
        String validationMessage = registrationForm.register(userWithInvalidEmail);
        Assert.assertEquals(validationMessage, INVALID_MAIL_FORMAT_MESSAGE, "Validation message doesn't" +
                "match expected");
    }

    @Test(testName = "Check valid password validation", priority = 4, dataProvider = "Valid passwords list")
    @Description("Valid password validation")
    public void checkSuccessfulPasswordValidation(String validPassword) {
        User userWithValidPassword = User.builder()
                .email(USER_WITH_VALID_CREDENTIALS.getEmail())
                .login(USER_WITH_VALID_CREDENTIALS.getLogin())
                .password(validPassword)
                .confirmPassword(validPassword)
                .build();
        String validationMessage = registrationForm.register(userWithValidPassword);
        Assert.assertEquals(validationMessage, SUCCESSFUL_REGISTRATION_MESSAGE, "Validation message doesn't" +
                "match expected");
    }

    @Test(testName = "Check invalid password validation", priority = 5, dataProvider = "Invalid passwords list")
    @Description("Invalid password validation")
    public void checkInvalidPasswordValidation(String invalidPassword) {
        User userWithInvalidPassword = User.builder()
                .email(USER_WITH_VALID_CREDENTIALS.getEmail())
                .login(USER_WITH_VALID_CREDENTIALS.getLogin())
                .password(invalidPassword)
                .confirmPassword(USER_WITH_VALID_CREDENTIALS.getConfirmPassword())
                .build();
        String validationMessage = registrationForm.register(userWithInvalidPassword);
        Assert.assertEquals(validationMessage, INVALID_PASSWORD_FORMAT_MESSAGE, "Validation message doesn't" +
                "match expected");
    }

    @Test(testName = "Check valid login validation", priority = 6, dataProvider = "Valid logins list")
    @Description("Valid login validation")
    public void checkValidLoginValidation(String validLogin) {
        User userWithValidLogin = User.builder()
                .email(USER_WITH_VALID_CREDENTIALS.getEmail())
                .login(validLogin)
                .password(USER_WITH_VALID_CREDENTIALS.getPassword())
                .confirmPassword(USER_WITH_VALID_CREDENTIALS.getConfirmPassword())
                .build();
        String validationMessage = registrationForm.register(userWithValidLogin);
        Assert.assertEquals(validationMessage, SUCCESSFUL_REGISTRATION_MESSAGE, "Validation message doesn't" +
                "match expected");
    }

    @Test(testName = "Check invalid login validation", priority = 7, dataProvider = "Invalid logins list")
    @Description("Invalid login validation")
    public void checkInvalidLoginValidation(String invalidLogin) {
        User userWithInvalidLogin = User.builder()
                .email(USER_WITH_VALID_CREDENTIALS.getEmail())
                .login(invalidLogin)
                .password(USER_WITH_VALID_CREDENTIALS.getPassword())
                .confirmPassword(USER_WITH_VALID_CREDENTIALS.getConfirmPassword())
                .build();
        String validationMessage = registrationForm.register(userWithInvalidLogin);
        Assert.assertEquals(validationMessage, INVALID_LOGIN_FORMAT_MESSAGE, "Validation message doesn't" +
                "match expected");
    }

    @Test(testName = "Check wrong confirm password validation", priority = 8)
    @Description("Wrong confirm password validation")
    public void checkWrongConfirmPasswordValidation() {
        User userWithWrongConfirmPassword = User.builder()
                .email(USER_WITH_VALID_CREDENTIALS.getEmail())
                .login(USER_WITH_VALID_CREDENTIALS.getLogin())
                .password(USER_WITH_VALID_CREDENTIALS.getPassword())
                .confirmPassword(TestDataGenerator.generateRandomString(8, 15))
                .build();
        String validationMessage = registrationForm.register(userWithWrongConfirmPassword);
        Assert.assertEquals(validationMessage, CONFIRM_PASSWORD_DOES_NOT_MATCH_MESSAGE, "Validation message " +
                "doesn't match expected");
    }

    @DataProvider(name = "Valid emails list")
    private Object[][] validEmailsList() {
        return new Object[][]{
                {"abc-d@mail.com"},
                {"abc.def@mail.com"},
                {"abc_def@mail.com"},
                {"abcdef@mail.cc"},
                {"abc.def@mail-archive.com"},
        };
    }

    @DataProvider(name = "Invalid emails list")
    private Object[][] invalidEmailsList() {
        return new Object[][]{
                {"abc-@mail.com"},
                {"abc..def@mail.com"},
                {"abc#def@mail.com"},
                {"abc.def@mail.c"},
                {"abc.def@mail#archive.com"},
                {"abc.def@mail"},
                {"abc.def@mail..com"},
        };
    }

    @DataProvider(name = "Valid passwords list")
    private Object[][] validPasswordsList() {
        return new Object[][]{
                {"testPass1234"},
                {"testPas1"},
        };
    }

    @DataProvider(name = "Invalid passwords list")
    private Object[][] invalidPasswordsList() {
        return new Object[][]{
                {"testpass1"},
                {"TESTPASS1"},
                {"testPass"},
                {"testPass12345"},
                {"TestPa1"},
        };
    }

    @DataProvider(name = "Valid logins list")
    private Object[][] validLoginsList() {
        return new Object[][]{
                {"testLo"},
                {"testLogin123"},
        };
    }

    @DataProvider(name = "Invalid logins list")
    private Object[][] invalidLoginsList() {
        return new Object[][]{
                {"testlogin"},
                {"TESTLOGIN"},
                {"testLogin1234"},
                {"Test1"},
        };
    }
}
