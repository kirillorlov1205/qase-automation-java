package elementsWrappers;

import org.openqa.selenium.By;
import utils.Waiter;

public class Input extends HtmlElement {

    public Input(By locator) {
        super(locator);
    }

    public Input writeText(String text) {
        Waiter.waitElementToBeDisplayedByLocator(locator).sendKeys(text);
        return this;
    }

    public Input clear() {
        Waiter.waitElementToBeDisplayedByLocator(locator).clear();
        return this;
    }
}
