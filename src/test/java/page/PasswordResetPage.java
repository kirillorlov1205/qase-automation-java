package page;

import elementsWrappers.Button;
import elementsWrappers.Input;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class PasswordResetPage extends BasePage {

    //    We have e-mailed your password reset link!
    private static final By EMAIL_FIELD_LOCATOR = By.xpath("//input[@name='email']");
    private static final By SEND_RESET_LINK_BUTTON_LOCATOR = By.xpath("//input[@name='email']");
    private static final By PASSWORD_RESET_VALIDATION_MESSAGE = By.xpath("//span[@class='ic9QAx']");

    public PasswordResetPage fillEmail(String email) {
        new Input(EMAIL_FIELD_LOCATOR).writeText(email);
        return this;
    }

    public PasswordResetPage clickSendResetLinkButton() {
        new Button(SEND_RESET_LINK_BUTTON_LOCATOR).click();
        return this;
    }

    public boolean isSuccessfulPasswordResetMessageDisplayed() {

    }


}
