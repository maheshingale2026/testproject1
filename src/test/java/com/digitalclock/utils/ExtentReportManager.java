package com.digitalclock.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static ExtentTest test;

    /**
     * Initialize Extent Reports
     */
    public static void initializeReport() {
        String reportPath = getReportPath();
        ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setDocumentTitle("Selenium Automation Test Report");
        reporter.config().setReportName("Digital Clock Automation Tests");

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester Name", "Automation Team");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }

    /**
     * Get the report file path
     */
    private static String getReportPath() {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        String reportDir = "target/extent-reports/";
        new File(reportDir).mkdirs();
        return reportDir + "ExtentReport_" + timestamp + ".html";
    }

    /**
     * Create a new test entry
     */
    public static void createTest(String testName, String description) {
        test = extent.createTest(testName, description);
    }

    /**
     * Get the current test object
     */
    public static ExtentTest getTest() {
        if (test == null) {
            createTest("Test", "Default Test");
        }
        return test;
    }

    /**
     * Log test pass
     */
    public static void logPass(String message) {
        getTest().pass(message);
    }

    /**
     * Log test fail
     */
    public static void logFail(String message) {
        getTest().fail(message);
    }

    /**
     * Log test info
     */
    public static void logInfo(String message) {
        getTest().info(message);
    }

    /**
     * Log test warning
     */
    public static void logWarning(String message) {
        getTest().warning(message);
    }

    /**
     * Flush and close the report
     */
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
