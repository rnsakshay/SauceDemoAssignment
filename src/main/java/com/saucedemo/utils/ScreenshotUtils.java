package com.saucedemo.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String methodName) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String screenshotDir = System.getProperty("user.dir") + "/reports/screenshots/";
        String screenshotPath = screenshotDir + methodName + "_" + timestamp + ".png";

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File dest = new File(screenshotPath);

            dest.getParentFile().mkdirs(); // Ensure directory exists

            FileUtils.copyFile(src, dest);

            // Use forward slashes (Extent prefers this especially on Windows)
            return dest.getAbsolutePath().replace("\\", "/");

        } catch (Exception e) {
            System.out.println("⚠ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
