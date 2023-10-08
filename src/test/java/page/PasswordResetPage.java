package page;

import elementsWrappers.Button;
import elementsWrappers.Input;
import elementsWrappers.ValidationMessage;
import org.openqa.selenium.By;

public class PasswordResetPage extends BasePage {

    private static final By EMAIL_FIELD_LOCATOR = By.xpath("//input[@name='email']");
    private static final By SEND_RESET_LINK_BUTTON_LOCATOR = By.xpath("//button[@type='submit']");
    private static final By PASSWORD_RESET_VALIDATION_MESSAGE_LOCATOR = By.xpath("//span[@class='" +
            "ic9QAx']");
    private static final By NAVIGATE_TO_LOGIN_PAGE_BUTTON = By.xpath("//a[contains(text(), 'Log in')]");
    private static final By NAVIGATE_TO_SSO_LOGIN_PAGE_BUTTON = By.xpath("//a[contains(text(), " +
            "'Login with SSO')]");

    public PasswordResetPage fillEmail(String email) {
        new Input(EMAIL_FIELD_LOCATOR).writeText(email);
        return this;
    }

    public PasswordResetPage clickSendResetLinkButton() {
        new Button(SEND_RESET_LINK_BUTTON_LOCATOR).click();
        return this;
    }

    public String getValidationMessage() {
        return new ValidationMessage(PASSWORD_RESET_VALIDATION_MESSAGE_LOCATOR).getText();
    }

    public LoginPage clickNavigateToLoginPageButton() {
        new Button(NAVIGATE_TO_LOGIN_PAGE_BUTTON).click();
        return new LoginPage();
    }

    public LoginPage clickNavigateToSsoLoginPage() {
        new Button(NAVIGATE_TO_SSO_LOGIN_PAGE_BUTTON).click();
        return new LoginPage();
    }
}
