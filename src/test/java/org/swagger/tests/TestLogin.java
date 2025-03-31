package org.swagger.tests;

import lombok.extern.log4j.Log4j2;
import org.swagger.base.BaseTest;
import org.swagger.pages.InventoryPage;
import org.swagger.pages.LoginPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
@SuppressWarnings("all")
public class TestLogin extends BaseTest {
    @Test(groups = {"Sanitty","Main"})
    public void testOpenPage() {
        openPage(properties.getProperty("SWAGGER_LOGIN_URL"));
        System.out.println("Inside testOpenPage");
    }

    @Test(dependsOnMethods = "testOpenPage",groups = {"Sanitty","Main"})
    public void testInputCredentials() {
        System.out.println("Inside testInputCredential");
        log.info("input credential section");
        LoginPage loginPage = new LoginPage(driver);
        assertTrue(loginPage.inputUserName(properties.getProperty("VALID_USER_NAME")), "Error While Entering User Name");
        assertTrue(loginPage.inputPassword(properties.getProperty("VALID_PASSWORD")), "Error While Entering Password");
        assertTrue(loginPage.clickEnter(), "Error while clicking Login Button");
        log.info("Successfully entered credentials");
    }

    @Test(dependsOnMethods = "testInputCredentials",groups = {"Sanitty","Main"})
    public void testVerifyLogin() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        assertEquals(inventoryPage.getLabel(), "Products");
        log.info("Successfully logged in and navigated to the inventory page");
    }
    @SuppressWarnings("java.lang.AssertionError")
    @Test(dependsOnMethods = "testVerifyLogin",groups = {"Sanitty","Main"})
    public void testDemoForFailing() {
        InventoryPage inventoryPage = new InventoryPage(driver);
        //noinspection AssertionFailureIgnored
        assertEquals(inventoryPage.getLabel(), "Products ");
        log.error("Test failed due to incorrect label intentionally");
    }
    @Test(dependsOnMethods = "testDemoForFailing",groups = {"Sanitty","Main"})
    public void testDemoForSkip() {
        log.info("This Part Never be Execute part1");
    }
    @Test(dependsOnMethods = "testDemoForFailing",groups = {"Sanitty","Main"})
    public void testDemoForSkip2() {
        log.info("This Part Never be Execute part2");
    }
}
