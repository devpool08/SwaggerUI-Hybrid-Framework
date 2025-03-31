package org.swagger.tests;

import lombok.extern.log4j.Log4j2;
import org.swagger.base.BaseTest;
import org.swagger.pages.CheckOutPage;
import org.swagger.pages.InventoryPage;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Log4j2
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class TestCheckOut extends BaseTest {
    private  static InventoryPage inventoryPage;
    private static CheckOutPage checkOutPage;

    @Test(groups = {"Regression","Main"})
    public void testSortByPrice(){
        inventoryPage = new InventoryPage(driver);
        assertTrue(inventoryPage.sortByPriceLowToHigh(), "Error While sorting by price");
        log.info("Sorted by price completed");
    }
    @Test(dependsOnMethods = "testSortByPrice",groups = {"Regression","Main"})
    public void testAddToCart(){
        assertTrue(inventoryPage.addProductToCart(), "Error adding product to cart");
        log.info("Product added to cart completed");
    }
    @Test(dependsOnMethods = "testAddToCart",groups = {"Regression","Main"})
    public void validateCartItems() {
        assertTrue(inventoryPage.verifyProductCart(), "Product is not in cart");
        log.info("Product added to cart verify completed");

    }
    @Test(dependsOnMethods = "validateCartItems",groups = {"Regression","Main"})
    public void checkOutItem() {
        assertTrue(inventoryPage.checkoutCart(), "Error While Checking Out Item");
        log.info("Checkout item completed");
    }

    @Test(dependsOnMethods = "checkOutItem",groups = {"Regression","Main"})
    public void completeCheckout() {
        checkOutPage = new CheckOutPage(driver);
        assertEquals(checkOutPage.isItemPresent(), "Sauce Labs Onesie");
        if (log.isInfoEnabled()) {
            log.info("Checkout page displayed");
        }
    }
    @Test(dependsOnMethods = "completeCheckout",groups = {"Regression","Main"})
    public void clickCheckOutButton() {
        assertTrue(checkOutPage.clickOnCheckoutButton(), "Error while clicking check out button");
        log.info("Checkout button clicked");
    }

}
