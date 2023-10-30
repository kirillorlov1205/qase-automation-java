package ui.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import ui.driver.DriverSingleton;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver = DriverSingleton.getInstance().getDriver();

    protected BasePage() {
        PageFactory.initElements(driver, this);
    }

    public boolean isElementNotDisplayed(WebElement element) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        boolean result;
        try {
            result = !element.isDisplayed();
        } catch (Exception e) {
            result = true;
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return result;
    }
}
