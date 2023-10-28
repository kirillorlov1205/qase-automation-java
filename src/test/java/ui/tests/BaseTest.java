package ui.tests;

import ui.driver.DriverSingleton;
import ui.model.User;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import ui.service.LoginPageService;
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

//    @AfterSuite
//    public void clean() {
//        driver = DriverSingleton.getInstance().getDriver();
//        LoginPageService loginPageService = new LoginPageService();
//        loginPageService.login(new User())
//                .removeCreatedProjects();
//        driver.quit();
//    }
}
