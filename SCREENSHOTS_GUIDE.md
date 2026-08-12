# Screenshots in Extent Reports

## 📸 Screenshot Feature Added

Your automation framework now captures screenshots at each step and embeds them in the Extent Reports!

---

## ✨ Features

### Automatic Screenshot Capture

The framework now captures screenshots:

| When | Purpose | Screenshot Name |
|------|---------|-----------------|
| ✅ Browser Launch | Verify browser opened | `Browser_Launch_[timestamp].png` |
| ✅ URL Navigation | Document page load | `Navigation_to_[url]_[timestamp].png` |
| ✅ Homepage Verification | Show loaded page | `Google_Homepage_[timestamp].png` |
| ✅ Title Verification | Capture page title area | `Page_Title_Verification_[timestamp].png` |
| ✅ Before Close | Final state before closing | `Before_Browser_Close_[timestamp].png` |
| ✅ After Tests | Final state of browser | `Final_State_[timestamp].png` |
| ❌ On Failure | Capture failure state | `FAILURE_[step_name]_[timestamp].png` |

---

## 📁 Screenshot Storage

All screenshots are saved to:
```
target/screenshots/
```

Example structure:
```
target/screenshots/
├── Browser_Launch_2026-08-12_22-53-39-100.png
├── Navigation_to_google_com_2026-08-12_22-53-39-250.png
├── Google_Homepage_2026-08-12_22-53-39-400.png
├── Page_Title_Verification_2026-08-12_22-53-39-550.png
├── Before_Browser_Close_2026-08-12_22-53-39-700.png
└── Final_State_2026-08-12_22-53-39-850.png
```

---

## 📊 Viewing Screenshots in Reports

### In Extent HTML Report

1. **Open the report:**
   ```
   target/extent-reports/ExtentReport_[timestamp].html
   ```

2. **View screenshots:**
   - Click on each test step in the report
   - Screenshots appear inline with step details
   - Each screenshot shows timestamp and filename

### Report Layout
```
┌─────────────────────────────────────────────┐
│ Test: Google Navigation Test          PASS  │
├─────────────────────────────────────────────┤
│ Step 1: Launch Chrome Browser              │
│ ├─ Status: PASS ✓                          │
│ ├─ Screenshot: Browser_Launch_...png       │
│ └─ [Screenshot Preview]                    │
│                                             │
│ Step 2: Navigate to google.com             │
│ ├─ Status: PASS ✓                          │
│ ├─ Screenshot: Navigation_to_...png        │
│ └─ [Screenshot Preview]                    │
│                                             │
│ Step 3: Verify Homepage                    │
│ ├─ Status: PASS ✓                          │
│ ├─ Screenshot: Google_Homepage_...png      │
│ └─ [Screenshot Preview]                    │
│                                             │
│ ... (more steps with screenshots)          │
│                                             │
└─────────────────────────────────────────────┘
```

---

## 🔧 Code Implementation

### Screenshot Utility Class
**File:** `ScreenshotUtil.java`

Methods:
```java
// Capture screenshot with custom name
ScreenshotUtil.captureScreenshot(driver, "My_Screenshot");

// Capture on failure
ScreenshotUtil.captureScreenshotOnFailure(driver, "test_name");

// Capture on success
ScreenshotUtil.captureScreenshotOnSuccess(driver, "test_name");
```

### Integration with Reports
**In Step Definitions:**
```java
@Then("Some step")
public void someStep() {
    try {
        // ... test code ...
        
        // Capture screenshot
        String screenshot = ScreenshotUtil.captureScreenshot(driver, "Step_Name");
        
        // Attach to Extent Report
        if (screenshot != null) {
            ExtentReportManager.attachScreenshot(screenshot);
        }
        
        ExtentReportManager.logPass("Step completed");
    } catch (Exception e) {
        ExtentReportManager.logFail("Step failed");
        captureFailureScreenshot("Step_Name");
        throw e;
    }
}
```

---

## 🖥️ Running Tests with Screenshots

### Command Line
```bash
mvn clean test
```

### Eclipse
1. Right-click `TestRunner.java`
2. Select **Run As** → **JUnit Test**
3. Wait for execution to complete

### Batch Script
```bash
run_tests.bat
```

---

## 📈 Console Output

When tests run, you'll see:
```
Browser initialized successfully
✓ Chrome browser launched
✓ Screenshot captured: Browser_Launch_2026-08-12_22-53-39-100.png
✓ Navigated to: https://www.google.com
✓ Screenshot captured: Navigation_to_google_com_2026-08-12_22-53-39-250.png
✓ Google homepage loaded
  Current URL: https://www.google.com/
✓ Screenshot captured: Google_Homepage_2026-08-12_22-53-39-400.png
✓ Page title verified: Google
✓ Screenshot captured: Page_Title_Verification_2026-08-12_22-53-39-550.png
✓ Browser closed successfully
✓ Screenshot captured: Final_State_2026-08-12_22-53-39-850.png
Browser closed successfully
```

---

## 📁 Report File Structure

### After Running Tests

```
testproject1/
├── target/
│   ├── extent-reports/
│   │   ├── ExtentReport_2026-08-12_22-53-38.html    ← Main Report
│   │   ├── index.html                                (Cucumber report)
│   │   └── cucumber.json
│   │
│   └── screenshots/                                  ← All Screenshots
│       ├── Browser_Launch_2026-08-12_22-53-39-100.png
│       ├── Navigation_to_google_com_...png
│       ├── Google_Homepage_...png
│       ├── Page_Title_Verification_...png
│       ├── Before_Browser_Close_...png
│       └── Final_State_...png
│
└── src/test/java/com/digitalclock/utils/
    └── ScreenshotUtil.java                           ← Screenshot Utility
```

---

## 🔍 Examining Failure Screenshots

If a test fails:

1. **Check Console** for failure message
2. **Look in `target/screenshots/`** for `FAILURE_*.png` files
3. **Open Extent Report** to see failure screenshot embedded

Example failure flow:
```
✗ Step failed: Google homepage not loaded
✗ Failure screenshot captured: target/screenshots/FAILURE_Homepage_Verification_Failed_2026-08-12_22-53-40-123.png
✓ Screenshot attached to report
```

---

## 🎯 Best Practices

### When Adding New Tests

1. **Capture at key points:**
   ```java
   // After navigation
   ScreenshotUtil.captureScreenshot(driver, "After_Login");
   
   // After data entry
   ScreenshotUtil.captureScreenshot(driver, "Form_Filled");
   
   // Before assertion
   ScreenshotUtil.captureScreenshot(driver, "Before_Verification");
   ```

2. **Use descriptive names:**
   - ✅ `Google_Homepage` (clear)
   - ❌ `screen1` (unclear)

3. **Attach to report:**
   ```java
   String screenshot = ScreenshotUtil.captureScreenshot(driver, "Step_Name");
   if (screenshot != null) {
       ExtentReportManager.attachScreenshot(screenshot);
   }
   ```

4. **Handle failures:**
   ```java
   try {
       // test code
   } catch (Exception e) {
       captureFailureScreenshot("Step_Name");
       throw e;
   }
   ```

---

## 🧹 Cleanup

### Clear Old Screenshots
```bash
# Windows
rmdir /s /q target\screenshots
mkdir target\screenshots

# Linux/Mac
rm -rf target/screenshots
mkdir target/screenshots
```

### Clean Build with Screenshots
```bash
mvn clean test
```

---

## 📊 Report Screenshot Example

**Dashboard Shows:**
- ✅ Total screenshots captured
- ✅ Screenshot timeline
- ✅ Device information
- ✅ Browser details

**Test Details Show:**
- ✅ Screenshot for each step
- ✅ Timestamp of screenshot
- ✅ Step status (PASS/FAIL)
- ✅ Associated log messages

---

## ✅ Verification Checklist

After running tests:
- ✅ Extent report generated: `target/extent-reports/ExtentReport_[timestamp].html`
- ✅ Screenshots captured: `target/screenshots/` directory populated
- ✅ Screenshots visible in HTML report
- ✅ Console shows screenshot capture messages
- ✅ Failure screenshots captured on errors

---

## 📝 File Locations

| Component | Location |
|-----------|----------|
| **Screenshot Utility** | `src/test/java/com/digitalclock/utils/ScreenshotUtil.java` |
| **Updated Step Definitions** | `src/test/java/com/digitalclock/stepdefinitions/GoogleNavigationSteps.java` |
| **Updated Report Manager** | `src/test/java/com/digitalclock/utils/ExtentReportManager.java` |
| **Screenshots** | `target/screenshots/` |
| **Extent Report** | `target/extent-reports/ExtentReport_[timestamp].html` |

---

## 🎉 You're Ready!

Run your tests now to see screenshots in action:

```bash
mvn test
```

Open the report and check out your screenshots! 📸

---

**Screenshot Feature Added:** August 12, 2026  
**Status:** ✅ Complete and Integrated
