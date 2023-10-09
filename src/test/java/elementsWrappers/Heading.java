package elementsWrappers;

import org.openqa.selenium.By;
import utils.Waiter;

public class Heading extends HtmlElement {

    public Heading(By locator) {
        super(locator);
    }

    public String getText() {
        return Waiter.waitElementToBeDisplayedByLocator(locator).getText();
    }
}
