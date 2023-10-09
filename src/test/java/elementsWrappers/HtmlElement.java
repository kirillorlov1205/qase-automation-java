package elementsWrappers;

import org.openqa.selenium.By;
import utils.Waiter;

public class HtmlElement {

    protected By locator;

    public HtmlElement(By locator) {
        this.locator = locator;
    }

    public boolean isDisplayed() {
        return Waiter.waitElementToBeDisplayedByLocator(locator).isDisplayed();
    }
}
