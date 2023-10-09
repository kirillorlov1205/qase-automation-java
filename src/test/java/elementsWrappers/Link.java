package elementsWrappers;

import org.openqa.selenium.By;
import utils.Waiter;

public class Link extends HtmlElement{

    public Link(By locator) {
        super(locator);
    }

    public void click() {
        Waiter.waitElementToBeDisplayedByLocator(locator).click();
    }
}
