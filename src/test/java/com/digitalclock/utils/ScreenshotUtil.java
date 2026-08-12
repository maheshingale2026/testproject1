package com.digitalclock.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
     * Take full page screenshot of current browser window
     */
    public static String captureFullPageScreenshot(WebDriver driver, String screenshotName) {
        // Try using Chrome DevTools Protocol to capture a full-page screenshot when running on Chrome
        try {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS").format(new Date());
            String fileName = "FULLPAGE_" + screenshotName.replaceAll(" ", "_") + "_" + timestamp + ".png";
            String destinationPath = SCREENSHOT_DIR + fileName;

            if (driver instanceof ChromeDriver) {
                try {
                    ChromeDriver chrome = (ChromeDriver) driver;
                    Map<String, Object> params = new HashMap<>();
                    // Request full page capture
                    params.put("captureBeyondViewport", true);
                    params.put("fromSurface", true);
                    @SuppressWarnings("unchecked")
                    Map<String, Object> result = chrome.executeCdpCommand("Page.captureScreenshot", params);
                    Object dataObj = result.get("data");
                    if (dataObj instanceof String) {
                        byte[] decoded = Base64.getDecoder().decode((String) dataObj);
                        Files.write(Paths.get(destinationPath), decoded);
                        System.out.println("✓ Full page screenshot captured: " + fileName);
                        return destinationPath;
                    }
                } catch (Exception cdpEx) {
                    // CDP attempt failed; fall back to viewport screenshot
                    System.out.println("⚠ CDP full-page capture failed, falling back to normal screenshot: " + cdpEx.getMessage());
                }
            }

            // Fallback: take normal viewport screenshot
            TakesScreenshot screenshot = (TakesScreenshot) driver;
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), Paths.get(destinationPath));
            System.out.println("✓ Full page (viewport fallback) screenshot captured: " + fileName);
            return destinationPath;
        } catch (IOException e) {
            System.out.println("✗ Failed to capture full page screenshot: " + e.getMessage());
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
