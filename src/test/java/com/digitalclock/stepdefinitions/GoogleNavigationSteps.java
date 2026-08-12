package com.digitalclock.stepdefinitions;

import com.digitalclock.utils.DriverManager;
import com.digitalclock.utils.ExtentReportManager;
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
        DriverManager.quitDriver();
        ExtentReportManager.flushReport();
        System.out.println("Browser closed successfully");
    }

    @Given("User launches the Chrome browser")
    public void userLaunchesChromeBrowser() {
        try {
            driver = DriverManager.getDriver();
            assertNotNull("Driver should not be null", driver);
            ExtentReportManager.logPass("Chrome browser launched successfully");
            System.out.println("✓ Chrome browser launched");
        } catch (Exception e) {
            ExtentReportManager.logFail("Failed to launch Chrome browser: " + e.getMessage());
            throw e;
        }
    }

    @When("User navigates to {string}")
    public void userNavigatesToUrl(String url) {
        try {
            DriverManager.navigateToUrl(url);
            ExtentReportManager.logPass("Navigated to: " + url);
            System.out.println("✓ Navigated to: " + url);
        } catch (Exception e) {
            ExtentReportManager.logFail("Failed to navigate to URL: " + e.getMessage());
            throw e;
        }
    }

    @Then("User should see the Google homepage")
    public void userShouldSeeGoogleHomepage() {
        try {
            String currentUrl = DriverManager.getCurrentUrl();
            assertTrue("URL should contain google", currentUrl.contains("google.com"));
            ExtentReportManager.logPass("Google homepage loaded successfully");
            ExtentReportManager.logInfo("Current URL: " + currentUrl);
            System.out.println("✓ Google homepage loaded");
            System.out.println("  Current URL: " + currentUrl);
        } catch (Exception e) {
            ExtentReportManager.logFail("Google homepage not loaded: " + e.getMessage());
            throw e;
        }
    }

    @And("User should verify the page title contains {string}")
    public void userShouldVerifyPageTitleContains(String expectedTitle) {
        try {
            String pageTitle = DriverManager.getPageTitle();
            assertTrue("Page title should contain: " + expectedTitle, pageTitle.contains(expectedTitle));
            ExtentReportManager.logPass("Page title verified successfully");
            ExtentReportManager.logInfo("Page title: " + pageTitle);
            System.out.println("✓ Page title verified: " + pageTitle);
        } catch (Exception e) {
            ExtentReportManager.logFail("Page title verification failed: " + e.getMessage());
            throw e;
        }
    }

    @And("User closes the browser")
    public void userClosesBrowser() {
        try {
            DriverManager.quitDriver();
            ExtentReportManager.logPass("Browser closed successfully");
            System.out.println("✓ Browser closed successfully");
        } catch (Exception e) {
            ExtentReportManager.logFail("Failed to close browser: " + e.getMessage());
            throw e;
        }
    }
}
