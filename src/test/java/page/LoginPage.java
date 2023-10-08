package page;

import driver.UiDriverActions;
import elementsWrappers.Button;
import elementsWrappers.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

public class LoginPage extends BasePage {

    private static final String LOGIN_PAGE_URL = "https://app.qase.io/login";
    private static final By EMAIL_FIELD_LOCATOR = By.xpath("//input[@name='email']");
    private static final By PASSWORD_FIELD_LOCATOR = By.xpath("//input[@name='password']");
    private static final By LOGIN_BUTTON_LOCATOR = By.xpath("//button[@type='submit']");
    private static final String EMPTY_FIELD_VALIDATION_MESSAGE_LOCATOR = "//input[@name='%s']" +
            "//ancestor::div[@class='tdishH']//small";
    private static final String WRONG_EMAIL_FORMAT_VALIDATION_MESSAGE_LOCATOR = "//span[@class='ic9QAx']";
    private static final String FORGOT_PASSWORD_BUTTON_LOCATOR = "//a[contains(text(),'Forgot password?')]";

    @FindBy(xpath = "//span[@class='ic9QAx']")
    private WebElement loginValidationMessage;

    public LoginPage fillEmail(String userName) {
        new Input(EMAIL_FIELD_LOCATOR).writeText(userName);
        return this;
    }

    public LoginPage fillPassword(String password) {
        new Input(PASSWORD_FIELD_LOCATOR).writeText(password);
        return this;
    }

    public void clickLoginButton() {
        new Button(LOGIN_BUTTON_LOCATOR).click();
    }

    public String getLoginValidationMessage() {
        return loginValidationMessage.getText();
    }

    public boolean isEmptyFieldValidationMessageDisplayed(String type) {
        return Waiter.waitElementToBeDisplayedByLocator(By.xpath(String.format(EMPTY_FIELD_VALIDATION_MESSAGE_LOCATOR,
                type))).isDisplayed();
    }

    public String getWrongEmailFormatValidationMessage() {
        return Waiter.waitElementToBeDisplayedByLocator(By.xpath(WRONG_EMAIL_FORMAT_VALIDATION_MESSAGE_LOCATOR))
                .getText();
    }

    public PasswordResetPage clickForgotPasswordButton() {
        new Button(By.xpath(FORGOT_PASSWORD_BUTTON_LOCATOR)).click();
        return new PasswordResetPage();
    }

    public LoginPage openLoginPage() {
        UiDriverActions.openPage(LOGIN_PAGE_URL);
        return this;
    }
}
