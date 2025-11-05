package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.InventoryPage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void loginSuccessTest() {
        report("🔹 Executing: loginSuccessTest");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginValid(); // Uses valid credentials

        InventoryPage inventoryPage = new InventoryPage(driver);
        report("✅ Login should redirect to Inventory page");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"), "Inventory page not loaded!");
        report("🎯 Login Success Verified");
    }

    @Test
    public void loginFailureTest() {
        report("🔹 Executing: loginFailureTest");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("locked_out_user", "secret_sauce");

        String error = loginPage.getErrorText();
        report("Captured Error: " + error);

        Assert.assertTrue(error.contains("locked"), "Expected locked account message!");
        report("🚫 Login Failure Verified");
    }
}
