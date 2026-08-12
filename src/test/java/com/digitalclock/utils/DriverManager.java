package com.digitalclock.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private static WebDriver driver;

    /**
     * Initialize Chrome WebDriver
     */
    public static WebDriver initializeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        
        return driver;
    }

    /**
     * Get the current driver instance
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            initializeDriver();
        }
        return driver;
    }

    /**
     * Close the browser
     */
    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    /**
     * Navigate to URL
     */
    public static void navigateToUrl(String url) {
        getDriver().navigate().to(url);
    }

    /**
     * Get current URL
     */
    public static String getCurrentUrl() {
        return getDriver().getCurrentUrl();
    }

    /**
     * Get page title
     */
    public static String getPageTitle() {
        return getDriver().getTitle();
    }
}
