package driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Waiter;

import java.util.ArrayList;
import java.util.List;

public class UiDriverActions {

    private UiDriverActions() {
    }

    public static String getTitle() {
        return DriverSingleton.getInstance().getDriver().getTitle();
    }

    public static String getCurrentUrl() {
        return DriverSingleton.getInstance().getDriver().getCurrentUrl();
    }

    public static void openPage(String url) {
        DriverSingleton.getInstance().getDriver().get(url);
    }

    public static WebElement findElement(By locator) {
        return Waiter.waitElementToBeDisplayedByLocator(locator);
    }

    public static List<WebElement> findElements(By locator) {
        return Waiter.waitElementsToBeDisplayedByLocator(locator);
    }

    public static String getSecondOpenedTabUrl() {
        ArrayList<String> tabs = new ArrayList(DriverSingleton.getInstance().getDriver().getWindowHandles());
        DriverSingleton.getInstance().getDriver().switchTo().window(tabs.get(1));
        String pageUrl = UiDriverActions.getCurrentUrl();
        DriverSingleton.getInstance().getDriver().close();
        DriverSingleton.getInstance().getDriver().switchTo().window(tabs.get(0));
        return pageUrl;
    }
}
