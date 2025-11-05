package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest {

    @Test
    public void addBackpackToCartTest() {
        report("🔹 Executing: addBackpackToCartTest");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginValid();
        report("✅ Login success");

        InventoryPage inventoryPage = new InventoryPage(driver);
        inventoryPage.addBackpackToCart();
        report("🎒 Backpack added to cart");

        int count = inventoryPage.getCartCount();
        report("🛒 Cart Count = " + count);

        Assert.assertEquals(count, 1, "Cart count should be 1!");
        report("🎯 Add to Cart Verified");
    }
}
