package utils;

import driver.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Waiter {
    public final static int WAIT_25_SECONDS = 25;
    public final static int WAIT_40_SECONDS = 40;

    private Waiter() {
    }

    public static WebElement waitElementToBeDisplayedByLocator(By locator) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_40_SECONDS))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitElementToBeDisplayed(WebElement element) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_40_SECONDS))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static boolean waitElementToBeNotDisplayed(WebElement element) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_40_SECONDS))
                .until(ExpectedConditions.invisibilityOf(element));
    }

    public static List<WebElement> waitElementsToBeDisplayed(List<WebElement> elementsList) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_40_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElements(elementsList));
    }

    public static boolean waitElementAttributeToBe(WebElement element, String attribute, String value) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_40_SECONDS))
                .until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    public static List<WebElement> waitElementsToBeDisplayedByLocator(By locator) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofSeconds(WAIT_40_SECONDS))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}
