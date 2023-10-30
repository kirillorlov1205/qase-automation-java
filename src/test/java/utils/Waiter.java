package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.driver.DriverSingleton;

import java.time.Duration;
import java.util.List;

public class Waiter {
    private final static int WAIT_1_MINUTE = 1;
    private final static int WAIT_2_MINUTES = 2;

    private Waiter() {
    }

    public static WebElement waitElementToBeDisplayedByLocator(By locator) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofMinutes(WAIT_1_MINUTE))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitElementToBeDisplayed(WebElement element) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofMinutes(WAIT_1_MINUTE))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitTwoMinutesElementToBeDisplayed(WebElement element) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofMinutes(WAIT_2_MINUTES))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static List<WebElement> waitElementsToBeDisplayed(List<WebElement> elementsList) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofMinutes(WAIT_1_MINUTE))
                .until(ExpectedConditions.visibilityOfAllElements(elementsList));
    }

    public static boolean waitElementAttributeToBe(WebElement element, String attribute, String value) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofMinutes(WAIT_1_MINUTE))
                .until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    public static List<WebElement> waitElementsToBeDisplayedByLocator(By locator) {
        return new WebDriverWait(DriverSingleton.getInstance().getDriver(), Duration.ofMinutes(WAIT_1_MINUTE))
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
}
