# Full-Page Screenshot for Homepage Verification

## 📸 What's New

The homepage verification step (`Then` step) now captures a **full-page screenshot** of the Google homepage!

---

## ✨ Feature Details

### Full-Page Screenshot Method Added

**New Method in ScreenshotUtil:**
```java
/**
 * Take full page screenshot of current browser window
 */
public static String captureFullPageScreenshot(WebDriver driver, String screenshotName) {
    // Captures entire page and saves as FULLPAGE_[name]_[timestamp].png
}
```

### Updated Step Definition

**GoogleNavigationSteps.java - Then Step:**
```java
@Then("User should see the Google homepage")
public void userShouldSeeGoogleHomepage() {
    try {
        // Verify URL contains google.com
        String currentUrl = DriverManager.getCurrentUrl();
        assertTrue("URL should contain google", currentUrl.contains("google.com"));
        
        // Capture FULL PAGE screenshot
        String screenshot = ScreenshotUtil.captureFullPageScreenshot(
            driver, 
            "Google_Homepage_Full_Page"
        );
        
        // Attach to Extent Report
        if (screenshot != null) {
            ExtentReportManager.attachScreenshot(screenshot);
        }
        
        // Log results
        ExtentReportManager.logPass("Google homepage loaded successfully");
        ExtentReportManager.logInfo("Full page screenshot captured for homepage verification");
    } catch (Exception e) {
        // Handle failure
    }
}
```

---

## 📁 Screenshot File Naming

### Full-Page Screenshots

When the "Then" step executes, a full-page screenshot is captured with naming:

```
FULLPAGE_Google_Homepage_Full_Page_2026-08-12_22-53-39-400.png
         └─────────────────────────────┬─────────────────────────┘
                                   Name + Timestamp
```

### File Storage

All full-page screenshots stored in:
```
target/screenshots/FULLPAGE_*.png
```

---

## 📊 Screenshot Flow

```
Then Step: "User should see the Google homepage"
    ↓
Verify URL contains "google.com"
    ↓
CAPTURE FULL PAGE SCREENSHOT
    ↓
Name: FULLPAGE_Google_Homepage_Full_Page_[timestamp].png
    ↓
Save to: target/screenshots/
    ↓
Attach to Extent Report
    ↓
Log as PASS with screenshot
    ↓
Console Output: ✓ Full page screenshot captured
```

---

## 🖥️ Console Output

When tests run, you'll see:

```
✓ Google homepage loaded
  Current URL: https://www.google.com/
  Full page screenshot captured
✓ Full page screenshot captured: FULLPAGE_Google_Homepage_Full_Page_2026-08-12_22-53-39-400.png
```

---

## 📈 Extent Report Display

### In the HTML Report

```
┌─────────────────────────────────────────────────────────────┐
│ Then: User should see the Google homepage            ✓ PASS  │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│ ✓ Google homepage loaded successfully                        │
│ ℹ Current URL: https://www.google.com/                       │
│ ℹ Full page screenshot captured for homepage verification    │
│                                                               │
│ Screenshot: FULLPAGE_Google_Homepage_Full_Page_...png        │
│ ┌─────────────────────────────────────────────────────────┐  │
│ │                                                           │  │
│ │           Full Google Homepage Screenshot               │  │
│ │         [Complete Page from Top to Bottom]              │  │
│ │                                                           │  │
│ │  ┌─────────────────────────────────┐                     │  │
│ │  │         Google Logo              │                     │  │
│ │  │      [Search Box & Buttons]      │                     │  │
│ │  │                                   │                     │  │
│ │  │        Search Results Area        │                     │  │
│ │  │                                   │                     │
│ │  └─────────────────────────────────┘                     │  │
│ │                                                           │  │
│ └─────────────────────────────────────────────────────────┘  │
│                                                               │
│ Timestamp: 2026-08-12 22:53:39                               │
│ Duration: 00:00:00:200                                       │
│                                                               │
└─────────────────────────────────────────────────────────────┘
```

---

## 📊 Advantages of Full-Page Screenshots

| Benefit | Description |
|---------|-------------|
| **Complete Visual Record** | Shows entire page, not just viewport |
| **Better Verification** | Can verify all page elements |
| **Documentation** | Perfect for test reports and documentation |
| **Debugging** | Helps identify page layout issues |
| **Regression Testing** | Visual comparison for regression detection |
| **Quality Assurance** | Professional test evidence |

---

## 🔍 File Organization

After running tests:

```
target/screenshots/
│
├── Browser_Launch_2026-08-12_22-53-39-100.png
│   └─ Screenshot of browser after launch
│
├── Navigation_to_google_com_2026-08-12_22-53-39-250.png
│   └─ Screenshot during navigation
│
├── FULLPAGE_Google_Homepage_Full_Page_2026-08-12_22-53-39-400.png
│   └─ FULL PAGE screenshot of Google homepage (NEW)
│
├── Page_Title_Verification_2026-08-12_22-53-39-550.png
│   └─ Screenshot during title verification
│
├── Before_Browser_Close_2026-08-12_22-53-39-700.png
│   └─ Screenshot before close
│
└── Final_State_2026-08-12_22-53-39-850.png
    └─ Final state screenshot
```

---

## 🎯 How to Use

### Run Tests
```bash
mvn clean test
```

### View Full-Page Screenshot
1. Navigate to: `target/extent-reports/`
2. Open: `ExtentReport_[timestamp].html`
3. Look for the **Then** step: "User should see the Google homepage"
4. See the **FULLPAGE** screenshot embedded below the step

### Access Raw Screenshot File
```bash
target/screenshots/FULLPAGE_Google_Homepage_Full_Page_*.png
```

---

## 📝 Step Definition Code

### Before (Old)
```java
String screenshot = ScreenshotUtil.captureScreenshot(driver, "Google_Homepage");
```

### After (New - Full Page)
```java
String screenshot = ScreenshotUtil.captureFullPageScreenshot(
    driver, 
    "Google_Homepage_Full_Page"
);
```

---

## 🔧 Technical Details

### What Gets Captured

```
Full-Page Screenshot captures:
├─ Page header
├─ All visible content
├─ Scrollable content
└─ Complete visual state
```

### Screenshot Properties

| Property | Value |
|----------|-------|
| **Format** | PNG image |
| **Quality** | Full resolution |
| **Size** | Varies by page |
| **Naming** | FULLPAGE_[name]_[timestamp].png |
| **Storage** | target/screenshots/ |
| **Report** | Embedded in HTML |

---

## ✅ Verification Checklist

After running tests:

- ✅ Full-page screenshot captured for Then step
- ✅ File saved as `FULLPAGE_Google_Homepage_Full_Page_*.png`
- ✅ Screenshot embedded in Extent Report
- ✅ Console shows: "Full page screenshot captured"
- ✅ Report displays full page image inline

---

## 📚 Complete Step Execution

When the Then step runs:

```
Step: "User should see the Google homepage"
│
├─ URL Verification: ✓ PASS
│  └─ https://www.google.com/ contains "google.com"
│
├─ Full-Page Screenshot: ✓ CAPTURED
│  └─ FULLPAGE_Google_Homepage_Full_Page_2026-08-12_22-53-39-400.png
│
├─ Report Attachment: ✓ ATTACHED
│  └─ Screenshot embedded in HTML report
│
├─ Logging: ✓ COMPLETE
│  ├─ "Google homepage loaded successfully"
│  ├─ "Current URL: https://www.google.com/"
│  └─ "Full page screenshot captured for homepage verification"
│
└─ Step Result: ✓ PASSED
```

---

## 🎉 Perfect for

- ✅ **Regression Testing** - Compare full pages over time
- ✅ **Visual Testing** - Detect visual changes
- ✅ **Documentation** - Professional test evidence
- ✅ **Bug Reports** - Complete visual evidence
- ✅ **Client Reports** - Beautiful screenshot reports
- ✅ **Quality Assurance** - Comprehensive verification

---

## 📊 Report Integration

The full-page screenshot is automatically:

1. **Captured** by ScreenshotUtil
2. **Named** with FULLPAGE_ prefix and timestamp
3. **Stored** in target/screenshots/
4. **Embedded** in Extent HTML Report
5. **Displayed** inline with step details
6. **Logged** with info and pass messages

---

## 🚀 Next Steps

1. **Run tests:**
   ```bash
   mvn clean test
   ```

2. **View report:**
   ```
   target/extent-reports/ExtentReport_*.html
   ```

3. **Check full-page screenshot:**
   - Click the Then step in the report
   - Scroll to see FULLPAGE screenshot
   - View complete Google homepage image

---

## ✨ Summary

| Feature | Details |
|---------|---------|
| **Method** | `captureFullPageScreenshot()` |
| **File Naming** | FULLPAGE_[name]_[timestamp].png |
| **Storage** | target/screenshots/ |
| **Report** | Embedded in Extent HTML |
| **Step** | Then: "User should see the Google homepage" |
| **Console Log** | "Full page screenshot captured" |

---

## 📞 Usage Example

For your own tests, you can use:

```java
// In any step definition
String screenshot = ScreenshotUtil.captureFullPageScreenshot(
    driver, 
    "My_Page_Name"
);

if (screenshot != null) {
    ExtentReportManager.attachScreenshot(screenshot);
}

ExtentReportManager.logInfo("Full page screenshot captured");
```

---

**Feature Implemented:** August 12, 2026  
**Status:** ✅ Complete and Ready  
**Deployment:** Committed and Pushed to GitHub

Enjoy your full-page screenshots in the test reports! 📸✨
