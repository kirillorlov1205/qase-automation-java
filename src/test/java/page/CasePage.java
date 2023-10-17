package page;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utils.Waiter;

@Log4j2
public class CasePage extends BasePage {

    private static final String CASE_STEPS_FIELD = "//p[@data-placeholder='%s']";
    private static final String ATTACHMENT = "//div[contains(@class,'attachments')]//a[contains(@href,'%s')]";

    @FindBy(xpath = "//input[@name='title']")
    private WebElement titleField;

    @FindBy(xpath = "//button[contains(@class,'mmhY_Q j4xaa7 u0i1tV')]")
    private WebElement addStepButton;

    @FindBy(xpath = "//button[@id='save-case']")
    private WebElement saveButton;

    @FindBy(xpath = "//div[@class='form-control-feedback']")
    private WebElement moreThanLimitTitleValidationMessage;

    @FindBy(xpath = "//a[@title='Repository']")
    private WebElement backToProjectButton;

    @FindBy(xpath = "//button[@class='pMNB6M']")
    private WebElement addAttachmentButton;

    @FindBy(xpath = "//a[contains(@class,'attach-existing')]")
    private WebElement browseNavigationButton;

    public CasePage fillTitleField(String caseTitle) {
        log.info("Fill 'Title' field");
        titleField.sendKeys(caseTitle);
        return this;
    }

    public CasePage fillStepsFieldByFieldName(String fieldName, String text) {
        log.info(String.format("Fill 'Steps' field by Field name '%s'", fieldName));
        Waiter.waitElementToBeDisplayedByLocator(By.xpath(String.format(CASE_STEPS_FIELD, fieldName)))
                .sendKeys(text);
        return this;
    }

    public CasePage clickAddStepButton() {
        log.info("Click 'Add step' button");
        addStepButton.click();
        return this;
    }

    public ProjectPage clickSaveButton() {
        log.info("Click 'Save' button");
        saveButton.click();
        return new ProjectPage();
    }

    public boolean isMoreThanLimitTitleValidationMessageDisplayed() {
        return moreThanLimitTitleValidationMessage.isDisplayed();
    }

    public ProjectPage clickBackToProjectButton() {
        log.info("Click 'Back to project' button");
        backToProjectButton.click();
        return new ProjectPage();
    }

    public CasePage clickAddAttachmentButton() {
        log.info("Click 'Add attachment' button");
        addAttachmentButton.click();
        return this;
    }

    public CasePage clickBrowseNavigationButton() {
        log.info("Click 'Browse' navigation button");
        browseNavigationButton.click();
        return this;
    }

    public CasePage selectAttachment(String attachmentName) {
        log.info(String.format("Select attachment by name '%s'", attachmentName));
        Waiter.waitElementToBeDisplayedByLocator(By.xpath(String.format(ATTACHMENT, attachmentName))).click();
        return this;
    }
}
