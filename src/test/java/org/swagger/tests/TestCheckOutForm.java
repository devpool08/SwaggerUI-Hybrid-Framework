package org.swagger.tests;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.swagger.base.BaseTest;
import org.swagger.pages.CheckoutInfoFillFormPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;


@Log4j2
@SuppressWarnings({"FieldCanBeLocal", "unused", "all"})
public class TestCheckOutForm extends BaseTest {
    private static CheckoutInfoFillFormPage checkoutInfoFillFormPage;

    @Test(groups = {"Regression", "Main"})
    public void verifyCheckoutPage() {
        checkoutInfoFillFormPage = new CheckoutInfoFillFormPage(driver);
        assertEquals(checkoutInfoFillFormPage.isCheckoutInfoPresent(), "Checkout: Your Information");
        log.info("Checkout page displayed");
    }

    @SuppressWarnings("deprecation")
    @Test(dependsOnMethods = "verifyCheckoutPage", groups = {"Regression", "Main"})
    public void validateInputCredentials() {
        assertTrue(checkoutInfoFillFormPage.
                inputFirstName(RandomStringUtils.randomAlphabetic(6)), "Error in Filling first name");
        assertTrue(checkoutInfoFillFormPage.
                inputLastName(RandomStringUtils.randomAlphabetic(6)), "Error in Filling last name");
        assertTrue(checkoutInfoFillFormPage.
                inputPostalCode(RandomStringUtils.randomNumeric(5)), "Error in Filling postal code");
        log.info("Checkout credentials validated successfully");
    }

    @SuppressWarnings("java.lang.AssertionError")
    @Test(dependsOnMethods = "validateInputCredentials", groups = {"Regression", "Main"})
    public void demoTestForFailing() {
        assertTrue(checkoutInfoFillFormPage.
                inputFirstName(null), "Error intentionally");
        log.info("Form submit");
    }

    @Test(dependsOnMethods = "demoTestForFailing", groups = {"Regression", "Main"})
    public void demoTestForSkip() {
        assertTrue(checkoutInfoFillFormPage.
                inputFirstName("John"), "Skip intentionally");
        log.info("Will Never Execute 1");
    }

    @Test(dependsOnMethods = "demoTestForFailing", groups = {"Regression", "Main"})
    public void demoTestForSkip2() {
        assertTrue(checkoutInfoFillFormPage.
                inputFirstName("John"), "Skip intentionally");
        log.info("Will Never Execute 2");
    }

}
