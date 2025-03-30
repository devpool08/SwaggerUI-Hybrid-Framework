package org.swagger.tests;

import lombok.extern.log4j.Log4j2;
import org.swagger.base.BaseTest;
import org.swagger.pages.CheckOutPage;
import org.swagger.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

@Log4j2
@SuppressWarnings({"FieldCanBeLocal", "unused"})
public class TestCheckOut extends BaseTest {
    private  static InventoryPage inventoryPage;
    private static CheckOutPage checkOutPage;

    @Test(groups = {"Regression","Main"})
    public void testSortByPrice(){
        inventoryPage = new InventoryPage(driver);
        Assert.assertTrue(inventoryPage.sortByPriceLowToHigh(), "Error While sorting by price");
        log.info("Sorted by price completed");
    }
    @Test(dependsOnMethods = "testSortByPrice",groups = {"Regression","Main"})
    public void testAddToCart(){
        Assert.assertTrue(inventoryPage.addProductToCart(), "Error adding product to cart");
        log.info("Product added to cart completed");
    }
    @Test(dependsOnMethods = "testAddToCart",groups = {"Regression","Main"})
    public void validateCartItems() {
        Assert.assertTrue(inventoryPage.verifyProductCart(), "Product is not in cart");
        log.info("Product added to cart verify completed");

    }
    @Test(dependsOnMethods = "validateCartItems",groups = {"Regression","Main"})
    public void checkOutItem() {
        Assert.assertTrue(inventoryPage.checkoutCart(), "Error While Checking Out Item");
        log.info("Checkout item completed");
    }

    @Test(dependsOnMethods = "checkOutItem",groups = {"Regression","Main"})
    public void completeCheckout() {
        checkOutPage = new CheckOutPage(driver);
        Assert.assertEquals(checkOutPage.isItemPresent(), "Sauce Labs Onesie");
        if (log.isInfoEnabled()) {
            log.info("Checkout page displayed");
        }
    }
    @Test(dependsOnMethods = "completeCheckout",groups = {"Regression","Main"})
    public void clickCheckOutButton() {
        Assert.assertTrue(checkOutPage.clickOnCheckoutButton(), "Error while clicking check out button");
        log.info("Checkout button clicked");
    }

}
