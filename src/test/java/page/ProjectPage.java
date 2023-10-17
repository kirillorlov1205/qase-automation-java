package page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

@Log4j2
public class ProjectPage extends BasePage {

    private static final String CASE = "//div[contains(@class,'orFHna')]/div[contains(text(),'%s')]";
    private static final String STEP = "//p[contains(text(),'%s')]";
    private static final String CASE_ATTACHMENT = "//a[contains(@href,'%s')] ";

    @FindBy(xpath = "//h1[@class='fGDnu0']")
    private WebElement projectTitle;

    @FindBy(xpath = "//a[@id='create-case-button']")
    private WebElement createCaseButton;

    public String getProjectTitle() {
        return Waiter.waitElementToBeDisplayed(projectTitle).getText();
    }

    public boolean isProjectPageNotOpened() {
        return isElementNotDisplayed(projectTitle);
    }

    public CasePage clickCreateCaseButton() {
        log.info("Click 'Create case' button");
        Waiter.waitElementToBeDisplayed(createCaseButton).click();
        return new CasePage();
    }

    public boolean isCaseCreatedInProject(String caseTitle) {
        return Waiter.waitElementToBeDisplayedByLocator(By.xpath(String.format(CASE, caseTitle)))
                .isDisplayed();
    }

    public ProjectPage clickOnTestCase(String caseTitle) {
        log.info(String.format("Click on test case with title '%s'", caseTitle));
        Waiter.waitElementToBeDisplayedByLocator(By.xpath(String.format(CASE, caseTitle))).click();
        return this;
    }

    public boolean isCaseWithStepsCreatedInProject(String caseAction) {
        return Waiter.waitElementToBeDisplayedByLocator(By.xpath(String.format(STEP, caseAction)))
                .isDisplayed();
    }

    public boolean isCaseWithAttachmentCreatedInProject(String attachmentName) {
        return Waiter.waitElementToBeDisplayedByLocator(By.xpath(String.format(CASE_ATTACHMENT, attachmentName)))
                .isDisplayed();
    }
}
