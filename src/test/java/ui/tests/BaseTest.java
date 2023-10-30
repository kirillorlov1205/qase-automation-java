package ui.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import ui.driver.DriverSingleton;
import utils.TestListener;

@Listeners(TestListener.class)
public class BaseTest {
    private WebDriver driver;

    @BeforeClass
    public void startBrowser() {
        driver = DriverSingleton.getInstance().getDriver();
    }

    @AfterClass(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.getInstance().closeDriver();
    }
}
