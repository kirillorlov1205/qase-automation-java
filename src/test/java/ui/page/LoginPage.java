package ui.page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ui.driver.UiDriverActions;
import utils.Waiter;

@Log4j2
public class LoginPage extends BasePage {
    private static final String LOGIN_PAGE_URL = "https://app.qase.io/login";
    private static final String EMPTY_FIELD_VALIDATION_MESSAGE = "//input[@name='%s']//ancestor::div[@class='tdishH']" +
            "//small";
    private static final String ADDITIONAL_LINK = "//a[contains(text(),'%s')]";

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//span[@class='ic9QAx']")
    private WebElement wrongEmailFormatValidationMessage;

    @FindBy(xpath = "//a[contains(text(),'Forgot password?')]")
    private WebElement forgotPasswordButton;

    @FindBy(xpath = "//span[@class='ic9QAx']")
    private WebElement loginValidationMessage;

    @FindBy(xpath = "//h1[contains(text(),'SSO Login')]")
    private WebElement loginSsoPageHeading;

    @FindBy(xpath = "//button[contains(text(),'live chat')]")
    private WebElement liveChatButton;

    @FindBy(xpath = "//iframe[@name='intercom-messenger-frame']")
    private WebElement liveChatIframe;

    public LoginPage fillEmail(String userName) {
        log.info("Fill 'Email' field");
        Waiter.waitElementToBeDisplayed(emailField).sendKeys(userName);
        return this;
    }

    public LoginPage fillPassword(String password) {
        log.info("Fill 'Password' field");
        passwordField.sendKeys(password);
        return this;
    }

    public ProjectsListPage clickLoginButton() {
        log.info("Click 'Login' button");
        loginButton.click();
        return new ProjectsListPage();
    }

    public String getLoginValidationMessage() {
        return Waiter.waitElementToBeDisplayed(loginValidationMessage).getText();
    }

    public boolean isEmptyFieldValidationMessageDisplayed(String type) {
        return Waiter.waitElementToBeDisplayedByLocator(By.xpath(String.format(EMPTY_FIELD_VALIDATION_MESSAGE, type)))
                .isDisplayed();
    }

    public String getWrongEmailFormatValidationMessage() {
        return Waiter.waitElementToBeDisplayed(wrongEmailFormatValidationMessage).getText();
    }

    public PasswordResetPage clickForgotPasswordButton() {
        log.info("Click 'Forgot password' button");
        Waiter.waitElementToBeDisplayed(forgotPasswordButton).click();
        return new PasswordResetPage();
    }

    public LoginPage openLoginPage() {
        log.info("Open 'Login' ui.page");
        UiDriverActions.openPage(LOGIN_PAGE_URL);
        return this;
    }

    public boolean isLoginPageOpened() {
        return Waiter.waitElementToBeDisplayed(loginButton).isDisplayed();
    }

    public boolean isSsoLoginPageOpened() {
        return Waiter.waitElementToBeDisplayed(loginSsoPageHeading).isDisplayed();
    }

    public LoginPage clickOnAdditionalLinkByName(String name) {
        log.info(String.format("Click on additional link by name '%s'", name));
        Waiter.waitElementToBeDisplayedByLocator(By.xpath(String.format(ADDITIONAL_LINK, name))).click();
        return this;
    }

    public LoginPage openLiveChat() {
        log.info("Open Live chat");
        Waiter.waitElementToBeDisplayed(liveChatButton).click();
        return this;
    }

    public boolean isLiveChatOpened() {
        return Waiter.waitElementToBeDisplayed(liveChatIframe).isDisplayed();
    }
}
