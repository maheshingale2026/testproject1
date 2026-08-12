package com.digitalclock.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    private static final String SCREENSHOT_DIR = "target/screenshots/";

    static {
        // Create screenshots directory if it doesn't exist
        File dir = new File(SCREENSHOT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * Take screenshot of current browser window
     */
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());
            String fileName = screenshotName.replaceAll(" ", "_") + "_" + timestamp + ".png";
            String destinationPath = SCREENSHOT_DIR + fileName;
            
            Files.copy(srcFile.toPath(), Paths.get(destinationPath));
            
            System.out.println("✓ Screenshot captured: " + fileName);
            return destinationPath;
        } catch (IOException e) {
            System.out.println("✗ Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }

    /**
     * Take screenshot on test failure
     */
    public static String captureScreenshotOnFailure(WebDriver driver, String testName) {
        return captureScreenshot(driver, "FAILURE_" + testName);
    }

    /**
     * Take screenshot on test success
     */
    public static String captureScreenshotOnSuccess(WebDriver driver, String testName) {
        return captureScreenshot(driver, "SUCCESS_" + testName);
    }

    /**
     * Get screenshot directory path
     */
    public static String getScreenshotDir() {
        return SCREENSHOT_DIR;
    }

    /**
     * Convert screenshot path to absolute path for report embedding
     */
    public static String getScreenshotAbsolutePath(String relativePath) {
        try {
            return new File(relativePath).getAbsolutePath();
        } catch (Exception e) {
            return relativePath;
        }
    }
}
