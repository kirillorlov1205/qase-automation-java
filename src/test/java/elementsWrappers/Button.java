package elementsWrappers;

import org.openqa.selenium.By;
import utils.Waiter;

public class Button extends HtmlElement {

    public Button(By locator) {
        super(locator);
    }

    public void click() {
        Waiter.waitElementToBeDisplayedByLocator(locator).click();
    }
}
