package com.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.slf4j.helpers.Util.report;

public class InventoryPage {

    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(InventoryPage.class);

    @FindBy(id = "shopping_cart_container")
    private WebElement cartIcon;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addBackpackButton;

    @FindBy(css = ".shopping_cart_badge")
    private WebElement cartBadge;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        report("🔹 InventoryPage loaded");
    }

    public void addBackpackToCart() {
        report("Clicking 'Add to Cart' on Sauce Labs Backpack");
        addBackpackButton.click();
    }

    public int getCartCount() {
        int count = Integer.parseInt(cartBadge.getText());
        report("Cart Badge Count: " + count);
        return count;
    }
}
