package org.swagger.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.swagger.base.BasePage;

@SuppressWarnings("unused")
public class OverviewPage extends BasePage {
    public OverviewPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//div[@class='subheader']")
    private WebElement subHeader;
    @FindBy(xpath = "//a[normalize-space()='FINISH']")
    private WebElement finishButton;

    public boolean isOverviewPagePresent() {
        try {
            wait.until(ExpectedConditions.visibilityOf(subHeader));
            return subHeader.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
    public boolean clickOnFinishButton() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(finishButton));
            finishButton.click();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
