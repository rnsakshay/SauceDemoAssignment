package com.saucedemo.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.saucedemo.utils.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class BaseTest {

    protected WebDriver driver;
    protected Logger log = LogManager.getLogger(this.getClass());
    private static ExtentReports extent;

    @BeforeSuite(alwaysRun = true)
    public void startReport() {
        extent = ExtentReportManager.getReportInstance();
        log.info("📄 Extent Report Initialized");
    }

    @BeforeMethod(alwaysRun = true)
    public void setup(Method method) {
        ExtentTest test = extent.createTest(method.getName());
        ExtentTestManager.setTest(test);

        report("===== Starting Test: " + method.getName() + " =====");

        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.get(ConfigReader.get("url"));

        report("🌍 Navigated to URL: " + ConfigReader.get("url"));
    }

    protected void report(String message) {
        log.info(message);
        if (ExtentTestManager.getTest() != null) {
            ExtentTestManager.getTest().info(message);
        }
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (result.getStatus() == ITestResult.FAILURE) {
            report("❌ Test Failed: " + result.getThrowable().getMessage());

            String screenshotPath = com.saucedemo.utils.ScreenshotUtils
                    .captureScreenshot(driver, result.getName());

            System.out.println("📍 Screenshot stored at: " + screenshotPath); // ✅ ADD THIS

            ExtentTestManager.getTest()
                    .fail("📸 Screenshot on Failure (" + result.getName() + ")")
                    .addScreenCaptureFromPath(screenshotPath);

            ExtentTestManager.getTest().fail(result.getThrowable());
        }
        else if (result.getStatus() == ITestResult.SUCCESS) {
            report("✅ Test Passed");
            ExtentTestManager.getTest().pass("Test Passed");

        } else if (result.getStatus() == ITestResult.SKIP) {
            report("⚠ Test Skipped");
            ExtentTestManager.getTest().skip("Test Skipped");
        }

        DriverFactory.quitDriver();
        report("🛑 Browser Closed");

        ExtentTestManager.removeTest();
    }


    @AfterSuite(alwaysRun = true)
    public void flushReport() {
        log.info("📁 Flushing Extent Report...");
        extent.flush();
    }

}
