package utils;

import driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Waiter {
    public final static int WAIT_10_SECONDS = 10;
    public final static int WAIT_15_SECONDS = 10;

    private Waiter() {
    }

    public static WebElement waitElementToBeDisplayedByLocator(By locator) {
        new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_15_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        return DriverSingleton.getInstance().getDriver().findElement(locator);
    }

    public static WebElement waitElementToBeDisplayed(WebElement element) {
        new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_15_SECONDS))
                .until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    public static List<WebElement> waitElementsToBeDisplayed(List<WebElement> elementsList) {
        new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_15_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(elementsList));
        return elementsList;
    }
}
