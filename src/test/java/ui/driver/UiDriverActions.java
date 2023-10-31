package ui.driver;

import java.util.Iterator;
import java.util.Set;

public class UiDriverActions {
    private UiDriverActions() {
    }

    public static String getCurrentUrl() {
        return DriverSingleton.getInstance().getDriver().getCurrentUrl();
    }

    public static void openPage(String url) {
        DriverSingleton.getInstance().getDriver().get(url);
    }

    public static boolean isAdditionalPageOpened(String pageUrl) {
        boolean isPageOpened = false;
        String MainWindow = DriverSingleton.getInstance().getDriver().getWindowHandle();
        Set<String> s1 = DriverSingleton.getInstance().getDriver().getWindowHandles();
        Iterator<String> i1 = s1.iterator();

        while (i1.hasNext()) {
            String ChildWindow = i1.next();
            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
                String currentUrl = DriverSingleton.getInstance().getDriver().switchTo().window(ChildWindow).getCurrentUrl();
                if (currentUrl.contains(pageUrl)) {
                    isPageOpened = true;
                }
                DriverSingleton.getInstance().getDriver().switchTo().window(MainWindow);
//                DriverSingleton.getInstance().getDriver().close();
            }
        }
        return isPageOpened;

//        ArrayList<String> tabs = new ArrayList(DriverSingleton.getInstance().getDriver().getWindowHandles());
//        DriverSingleton.getInstance().getDriver().switchTo().window(tabs.get(1));
//        String pageUrl = UiDriverActions.getCurrentUrl();
//        DriverSingleton.getInstance().getDriver().close();
//        DriverSingleton.getInstance().getDriver().switchTo().window(tabs.get(0));
//        return pageUrl;
    }

    public static void acceptAlert() {
        DriverSingleton.getInstance().getDriver().switchTo().alert().accept();
    }
}
