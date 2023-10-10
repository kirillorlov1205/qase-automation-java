package page;

import driver.UiDriverActions;
import elementsWrappers.*;
import org.openqa.selenium.By;
import utils.Waiter;

public class LoginPage extends BasePage {

    private static final String LOGIN_PAGE_URL = "https://app.qase.io/login";
    private static final By EMAIL_FIELD_LOCATOR = By.xpath("//input[@name='email']");
    private static final By PASSWORD_FIELD_LOCATOR = By.xpath("//input[@name='password']");
    private static final By LOGIN_BUTTON_LOCATOR = By.xpath("//button[@type='submit']");
    private static final String EMPTY_FIELD_VALIDATION_MESSAGE_LOCATOR = "//input[@name='%s']" +
            "//ancestor::div[@class='tdishH']//small";
    private static final By WRONG_EMAIL_FORMAT_VALIDATION_MESSAGE_LOCATOR = By.xpath("//span[@class='ic9QAx']");
    private static final By FORGOT_PASSWORD_BUTTON_LOCATOR = By.xpath("//a[contains(text(),'Forgot password?')]");
    private static final By LOGIN_VALIDATION_MESSAGE_LOCATOR = By.xpath("//span[@class='ic9QAx']");
    private static final By LOGIN_SSO__PAGE_HEADING_LOCATOR = By.xpath("//h1[contains(text(),'SSO Login')]");
    private static final String ADDITIONAL_LINK_LOCATOR = "//a[contains(text(),'%s')]";
    private static final By LIVE_CHAT_BUTTON_LOCATOR = By.xpath("//button[contains(text(),'live chat')]");
    private static final By LIVE_CHAT_IFRAME_LOCATOR = By.xpath("//iframe[@name='intercom-messenger-frame']");

    public LoginPage fillEmail(String userName) {
        new Input(EMAIL_FIELD_LOCATOR).writeText(userName);
        return this;
    }

    public LoginPage fillPassword(String password) {
        new Input(PASSWORD_FIELD_LOCATOR).writeText(password);
        return this;
    }

    public ProjectsListPage clickLoginButton() {
        new Button(LOGIN_BUTTON_LOCATOR).click();
        return new ProjectsListPage();
    }

    public String getLoginValidationMessage() {
        return new ValidationMessage(LOGIN_VALIDATION_MESSAGE_LOCATOR).getText();
    }

    public boolean isEmptyFieldValidationMessageDisplayed(String type) {
        return Waiter.waitElementToBeDisplayedByLocator(By.xpath(String.format(EMPTY_FIELD_VALIDATION_MESSAGE_LOCATOR,
                type))).isDisplayed();
    }

    public String getWrongEmailFormatValidationMessage() {
        return new ValidationMessage(WRONG_EMAIL_FORMAT_VALIDATION_MESSAGE_LOCATOR).getText();
    }

    public PasswordResetPage clickForgotPasswordButton() {
        new Button(FORGOT_PASSWORD_BUTTON_LOCATOR).click();
        return new PasswordResetPage();
    }

    public LoginPage openLoginPage() {
        UiDriverActions.openPage(LOGIN_PAGE_URL);
        return this;
    }

    public boolean isLoginPageOpened() {
        return new Button(LOGIN_BUTTON_LOCATOR).isDisplayed();
    }

    public boolean isSsoLoginPageOpened() {
        return new Heading(LOGIN_SSO__PAGE_HEADING_LOCATOR).isDisplayed();
    }

    public LoginPage clickOnAdditionalLinkByName(String name) {
        new Link(By.xpath(String.format(ADDITIONAL_LINK_LOCATOR, name))).click();
        return this;
    }

    public LoginPage openLiveChat() {
        new Button(LIVE_CHAT_BUTTON_LOCATOR).click();
        return this;
    }

    public boolean isLiveChatOpened() {
        return new Iframe(LIVE_CHAT_IFRAME_LOCATOR).isDisplayed();
    }

    public String getLoginPageUrl() {
        return LOGIN_PAGE_URL;
    }
}
