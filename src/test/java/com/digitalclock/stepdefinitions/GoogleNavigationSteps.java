package com.digitalclock.stepdefinitions;

import com.digitalclock.utils.DriverManager;
import com.digitalclock.utils.ExtentReportManager;
import com.digitalclock.utils.ScreenshotUtil;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class GoogleNavigationSteps {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverManager.initializeDriver();
        ExtentReportManager.initializeReport();
        ExtentReportManager.createTest("Google Navigation Test", "Test for opening Google.com");
        System.out.println("Browser initialized successfully");
    }

    @After
    public void tearDown() {
        // Take final screenshot before closing
        if (driver != null) {
            String screenshot = ScreenshotUtil.captureScreenshot(driver, "Final_State");
            if (screenshot != null) {
                ExtentReportManager.attachScreenshot(screenshot);
            }
        }
        DriverManager.quitDriver();
        ExtentReportManager.flushReport();
        System.out.println("Browser closed successfully");
    }

    @Given("User launches the Chrome browser")
    public void userLaunchesChromeBrowser() {
        try {
            driver = DriverManager.getDriver();
            assertNotNull("Driver should not be null", driver);
            
            String screenshot = ScreenshotUtil.captureScreenshot(driver, "Browser_Launch");
            if (screenshot != null) {
                ExtentReportManager.attachScreenshot(screenshot);
            }
            
            ExtentReportManager.logPass("Chrome browser launched successfully");
            System.out.println("✓ Chrome browser launched");
        } catch (Exception e) {
            ExtentReportManager.logFail("Failed to launch Chrome browser: " + e.getMessage());
            captureFailureScreenshot("Browser_Launch_Failed");
            throw e;
        }
    }

    @When("User navigates to {string}")
    public void userNavigatesToUrl(String url) {
        try {
            DriverManager.navigateToUrl(url);
            
            // Wait a moment for page to load
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            String screenshot = ScreenshotUtil.captureScreenshot(driver, "Navigation_to_" + url.replace("https://", "").replace("http://", "").replace("/", "_"));
            if (screenshot != null) {
                ExtentReportManager.attachScreenshot(screenshot);
            }
            
            ExtentReportManager.logPass("Navigated to: " + url);
            System.out.println("✓ Navigated to: " + url);
        } catch (Exception e) {
            ExtentReportManager.logFail("Failed to navigate to URL: " + e.getMessage());
            captureFailureScreenshot("Navigation_Failed");
            throw e;
        }
    }

    @Then("User should see the Google homepage")
    public void userShouldSeeGoogleHomepage() {
        try {
            String currentUrl = DriverManager.getCurrentUrl();
            assertTrue("URL should contain google", currentUrl.contains("google.com"));
            
            // Capture full page screenshot for homepage verification
            String screenshot = ScreenshotUtil.captureFullPageScreenshot(driver, "Google_Homepage_Full_Page");
            if (screenshot != null) {
                ExtentReportManager.attachScreenshot(screenshot);
            }
            
            ExtentReportManager.logPass("Google homepage loaded successfully");
            ExtentReportManager.logInfo("Current URL: " + currentUrl);
            ExtentReportManager.logInfo("Full page screenshot captured for homepage verification");
            System.out.println("✓ Google homepage loaded");
            System.out.println("  Current URL: " + currentUrl);
            System.out.println("  Full page screenshot captured");
        } catch (Exception e) {
            ExtentReportManager.logFail("Google homepage not loaded: " + e.getMessage());
            captureFailureScreenshot("Homepage_Verification_Failed");
            throw e;
        }
    }

    @And("User should verify the page title contains {string}")
    public void userShouldVerifyPageTitleContains(String expectedTitle) {
        try {
            String pageTitle = DriverManager.getPageTitle();
            assertTrue("Page title should contain: " + expectedTitle, pageTitle.contains(expectedTitle));
            
            String screenshot = ScreenshotUtil.captureScreenshot(driver, "Page_Title_Verification");
            if (screenshot != null) {
                ExtentReportManager.attachScreenshot(screenshot);
            }
            
            ExtentReportManager.logPass("Page title verified successfully");
            ExtentReportManager.logInfo("Page title: " + pageTitle);
            System.out.println("✓ Page title verified: " + pageTitle);
        } catch (Exception e) {
            ExtentReportManager.logFail("Page title verification failed: " + e.getMessage());
            captureFailureScreenshot("Title_Verification_Failed");
            throw e;
        }
    }

    @And("User closes the browser")
    public void userClosesBrowser() {
        try {
            String screenshot = ScreenshotUtil.captureScreenshot(driver, "Before_Browser_Close");
            if (screenshot != null) {
                ExtentReportManager.attachScreenshot(screenshot);
            }
            
            DriverManager.quitDriver();
            ExtentReportManager.logPass("Browser closed successfully");
            System.out.println("✓ Browser closed successfully");
        } catch (Exception e) {
            ExtentReportManager.logFail("Failed to close browser: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Helper method to capture screenshot on failure
     */
    private void captureFailureScreenshot(String stepName) {
        try {
            if (driver != null) {
                String screenshot = ScreenshotUtil.captureScreenshotOnFailure(driver, stepName);
                if (screenshot != null) {
                    ExtentReportManager.attachScreenshot(screenshot);
                    System.out.println("✗ Failure screenshot captured: " + screenshot);
                }
            }
        } catch (Exception ex) {
            System.out.println("✗ Could not capture failure screenshot: " + ex.getMessage());
        }
    }
}
