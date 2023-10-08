package driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Waiter;

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

//    public static WebElement findElement(By locator){
//        return Waiter.waitElementToBeDisplayedByLocator(locator);
//    }
//
//    public static WebElement findElements(By locator){
//        return Waiter.waitElementToBeDisplayedByLocator(locator);
//    }
}
