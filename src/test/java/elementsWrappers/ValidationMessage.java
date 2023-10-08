package elementsWrappers;

import org.openqa.selenium.By;
import utils.Waiter;

public class ValidationMessage extends HtmlElement {

    public ValidationMessage(By locator) {
        super(locator);
    }

    public String getText() {
        return Waiter.waitElementToBeDisplayedByLocator(locator).getText();
    }

    public boolean isValidationMessage() {
        return Waiter.waitElementToBeDisplayedByLocator(locator).isDisplayed();
    }
}
