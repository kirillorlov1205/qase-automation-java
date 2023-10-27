package ui.page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

@Log4j2
public class PasswordResetPage extends BasePage {

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement sendResetLinkButton;

    @FindBy(xpath = "//span[@class='ic9QAx']")
    private WebElement passwordResetValidationMessage;

    @FindBy(xpath = "//a[contains(text(), 'Log in')]")
    private WebElement navigateToLoginPageButton;

    @FindBy(xpath = "//a[contains(text(),'Login with SSO')]")
    private WebElement navigateToSsoLoginPageButton;

    public PasswordResetPage fillEmail(String email) {
        log.info("Fill 'Email' field");
        Waiter.waitElementToBeDisplayed(emailField).sendKeys(email);
        return this;
    }

    public PasswordResetPage clickSendResetLinkButton() {
        log.info("Click 'Send reset link' button");
        Waiter.waitElementToBeDisplayed(sendResetLinkButton).click();
        return this;
    }

    public String getValidationMessage() {
        return passwordResetValidationMessage.getText();
    }

    public LoginPage clickNavigateToLoginPageButton() {
        log.info("Click 'Navigate to Login ui.page' button");
        Waiter.waitElementToBeDisplayed(navigateToLoginPageButton).click();
        return new LoginPage();
    }

    public LoginPage clickNavigateToSsoLoginPage() {
        log.info("Click 'Navigate to SSO Login ui.page' button");
        Waiter.waitElementToBeDisplayed(navigateToSsoLoginPageButton).click();
        return new LoginPage();
    }
}
