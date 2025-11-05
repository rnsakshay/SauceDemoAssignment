package com.saucedemo.pages;

import com.saucedemo.utils.ConfigReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.slf4j.helpers.Util.report;

public class LoginPage {

    private WebDriver driver;
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        report("🔹 LoginPage loaded");
    }

    public void login(String username, String password) {
        report("Typing username: " + username);
        usernameInput.clear();
        usernameInput.sendKeys(username);

        report("Typing password");
        passwordInput.clear();
        passwordInput.sendKeys(password);

        report("Clicking Login button");
        loginButton.click();
    }

    public void loginValid() {
        report("Logging in with valid credentials");
        login(ConfigReader.get("username"), ConfigReader.get("password"));
    }

    public String getErrorText() {
        String text = errorMessage.getText();
        report("Captured Login Error Message: " + text);
        return text;
    }
}
