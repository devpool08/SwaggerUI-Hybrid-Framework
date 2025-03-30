package org.swagger.tests;

import lombok.extern.log4j.Log4j2;
import org.swagger.base.BaseTest;
import org.swagger.pages.FinalPage;
import org.swagger.pages.OverviewPage;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

@Log4j2
@SuppressWarnings({"FieldCanBeLocal", "unused",})
public class TestOrderConfirmation extends BaseTest {
    private static OverviewPage overviewPage;
    private static FinalPage finalPage;

    @Test(groups = {"Regression", "Main"})
    public void testOrderOverview() {
        overviewPage = new OverviewPage(driver);
        assert overviewPage.isOverviewPagePresent():"Error while overview page";
        assert overviewPage.clickOnFinishButton():"Error while clicking finish button";
        log.info("Over view message");
    }
    @Test(groups = {"Main","Sanitty"},dependsOnMethods = "testOrderOverview")
    public void testOrderConfirmationMessage() {
        finalPage = new FinalPage(driver);
        String orderConfirmationMessage = finalPage.getThankYouMessage();
        assertEquals("THANK YOU FOR YOUR ORDER", orderConfirmationMessage);
        log.info("Order Confirmation Message: {}", orderConfirmationMessage);
    }
    @Test(dependsOnMethods = "testOrderConfirmationMessage",groups = {"Regression","Main"})
    public void testCloser() {
        factory.quitDriver();
        log.info("Driver closed");
    }
}
