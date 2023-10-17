package driver;

import java.util.ArrayList;

public class UiDriverActions {

    private UiDriverActions() {
    }

    public static String getCurrentUrl() {
        return DriverSingleton.getInstance().getDriver().getCurrentUrl();
    }

    public static void openPage(String url) {
        DriverSingleton.getInstance().getDriver().get(url);
    }

    public static String getSecondOpenedTabUrl() {
        ArrayList<String> tabs = new ArrayList(DriverSingleton.getInstance().getDriver().getWindowHandles());
        DriverSingleton.getInstance().getDriver().switchTo().window(tabs.get(1));
        String pageUrl = UiDriverActions.getCurrentUrl();
        DriverSingleton.getInstance().getDriver().close();
        DriverSingleton.getInstance().getDriver().switchTo().window(tabs.get(0));
        return pageUrl;
    }

    public static void acceptAlert() {
        DriverSingleton.getInstance().getDriver().switchTo().alert().accept();
    }
}
