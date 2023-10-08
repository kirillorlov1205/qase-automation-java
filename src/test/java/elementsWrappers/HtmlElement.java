package elementsWrappers;

import driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HtmlElement {

    protected By locator;

    public HtmlElement(By locator) {
        this.locator = locator;
    }

    public WebElement getElement() {
        return DriverSingleton.getInstance().getDriver().findElement(locator);
    }

    public By getLocator() {
        return locator;
    }

    public boolean isDisplayed() {
        return this.getElement().isDisplayed();
    }
}
