# Test Failure Diagnostics & Troubleshooting

## 🔍 Why Tests Might Be Failing

Without seeing your actual error output, here are the most common causes:

---

## ✅ Prerequisite Check

Before running tests, verify you have:

### 1. Java 11 or Higher
```powershell
java -version
```
**Expected output:** version 11 or higher

**If missing:**
- Download from: https://www.oracle.com/java/technologies/downloads/
- Install and add to system PATH
- Restart terminal

### 2. Maven 3.6+
```powershell
mvn -version
```
**Expected output:** Apache Maven 3.6.x or higher

**If missing:**
- Download from: https://maven.apache.org/download.cgi
- Extract to: `C:\Program Files\maven` (or similar)
- Add `C:\Program Files\maven\bin` to system PATH
- Restart terminal

### 3. Chrome Browser
- Must be installed for Selenium to automate
- WebDriverManager auto-downloads matching ChromeDriver
- Ensure at least one Chrome version is installed

**Check:**
```powershell
dir "C:\Program Files\Google\Chrome\Application\"
# or
dir "C:\Program Files (x86)\Google\Chrome\Application\"
```

---

## 🚀 Run Tests with Full Diagnostics

Use the provided diagnostic script to identify the issue:

```powershell
cd C:\Users\MaheshIngale\git\testproject1

# Run with full diagnostics and logging
.\run_tests_with_diagnostics.bat
```

This script will:
1. ✅ Check Java installation
2. ✅ Check Maven installation
3. ✅ Check Chrome installation
4. ✅ Clean previous build
5. ✅ Run tests with `-X` (debug mode)
6. ✅ Save **full log** to `logs/test_run_*.log`
7. ✅ Show pass/fail result
8. ✅ Detect common issues

---

## 📋 Most Common Failure Causes

### Issue 1: Maven Not Found
**Error:** `mvn : The term 'mvn' is not recognized`

**Fix:**
```powershell
# Install Maven
# 1. Download from: https://maven.apache.org/
# 2. Extract to: C:\Program Files\maven
# 3. Add to PATH: C:\Program Files\maven\bin
# 4. Restart PowerShell
# 5. Verify:
mvn -version
```

### Issue 2: Java Not Found
**Error:** `java : The term 'java' is not recognized`

**Fix:**
```powershell
# 1. Install Java 11+: https://www.oracle.com/java/technologies/downloads/
# 2. Verify:
java -version
```

### Issue 3: Chrome Not Found
**Error:** `ChromeDriver can't find Chrome executable`

**Fix:**
- Install Chrome: https://google.com/chrome
- Or set CHROME_BIN environment variable:
```powershell
$env:CHROME_BIN = "C:\Program Files\Google\Chrome\Application\chrome.exe"
```

### Issue 4: WebDriver Download Failed
**Error:** `Failed to download ChromeDriver`

**Fix:**
- Check internet connection
- WebDriverManager downloads driver on first run (may take 1-2 min)
- Try clearing cache: `rmdir /s %USERPROFILE%\.wdm\`

### Issue 5: Screenshot Directory Issues
**Error:** `Failed to capture screenshot` or `No such file or directory`

**Fix:**
```powershell
# Ensure target folder exists and is writable
mkdir target\screenshots
mkdir target\extent-reports

# Check permissions:
icacls target
```

### Issue 6: Port Already in Use
**Error:** `Address already in use`

**Fix:**
- Close other Chrome instances
- Wait a few seconds and retry
- Or kill the process:
```powershell
taskkill /F /IM chrome.exe
```

### Issue 7: Timeout Error
**Error:** `TimeoutException` or `The operation timed out`

**Fix:**
- Increase timeout in code (currently 10s implicit, 20s explicit)
- Check internet speed
- Google.com may be slow to load; try again

---

## 🔧 Step-by-Step Troubleshooting

### Step 1: Run Diagnostic Script
```powershell
.\run_tests_with_diagnostics.bat
```

### Step 2: Check Generated Log
```powershell
# Find the most recent log file
dir logs

# Open latest log
type logs\test_run_*.log | more

# Or in PowerShell for better viewing:
Get-Content logs\test_run_*.log -Tail 100
```

### Step 3: Search for Errors in Log
Look for lines with:
- `ERROR`
- `Exception`
- `FAILED`
- `Cannot find`

### Step 4: Match Error to Solution Above

### Step 5: If Still Failing, Provide Log Output
Copy the error lines from `logs/test_run_*.log` and share them.

---

## 📝 Common Error Messages & Fixes

### "Cannot find Chrome executable"
```
ChromeDriver unable to discover chrome binary
```
**Fix:** Install Chrome, ensure it's in standard location

### "Connection timeout"
```
java.net.SocketTimeoutException: connect timed out
```
**Fix:** Check internet, ensure WebDriverManager can download driver

### "Address already in use"
```
java.net.BindException: Address already in use
```
**Fix:** Close other Chrome instances: `taskkill /F /IM chrome.exe`

### "Screenshot file not found"
```
java.nio.file.NoSuchFileException: target/screenshots
```
**Fix:** Create folder manually: `mkdir target\screenshots`

### "Extent report path not found"
```
java.nio.file.NoSuchFileException: target/extent-reports
```
**Fix:** Create folder manually: `mkdir target\extent-reports`

### "Step definition not found"
```
io.cucumber.junit.UndefinedStepException
```
**Fix:** Ensure step definitions exist in `src/test/java/com/digitalclock/stepdefinitions/`

---

## 🛠️ Advanced Troubleshooting

### Run with Maximum Verbosity
```powershell
mvn -X -Dtest=TestRunner test 2>&1 | Tee-Object -FilePath test_debug.log
```

### Clean and Rebuild Everything
```powershell
mvn clean -U install -Dtest=TestRunner test
```
(`-U` forces update of snapshots)

### Check Specific Test Class
```powershell
mvn -Dtest=TestRunner#testGetDefaultTimeZones test
```

### Skip Tests and Just Compile
```powershell
mvn clean compile -DskipTests
```
(Verify code compiles without running tests)

---

## 🔍 Verify Project Structure

The project should have:
```
testproject1/
├── pom.xml                                      ✓
├── src/
│   └── test/
│       ├── java/com/digitalclock/
│       │   ├── stepdefinitions/
│       │   │   └── GoogleNavigationSteps.java  ✓
│       │   ├── runners/
│       │   │   └── TestRunner.java             ✓
│       │   └── utils/
│       │       ├── DriverManager.java          ✓
│       │       ├── ExtentReportManager.java    ✓
│       │       └── ScreenshotUtil.java         ✓
│       └── resources/
│           ├── features/
│           │   └── GoogleNavigation.feature    ✓
│           └── config.properties               ✓
└── .classpath                                   ✓
```

**If any files are missing**, that's likely the cause of the failure.

---

## 📊 Expected Test Output

When tests **PASS**, you should see:
```
[INFO] Running com.digitalclock.runners.TestRunner
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 5.234 s
[INFO] BUILD SUCCESS
```

When tests **FAIL**, you'll see:
```
[INFO] Tests run: 1, Failures: 1, Errors: 0, Skipped: 0
[ERROR] FAILURE
[ERROR] BUILD FAILURE
```

---

## 🎯 Quick Checklist Before Reporting Issue

- [ ] Java 11+ installed: `java -version`
- [ ] Maven 3.6+ installed: `mvn -version`
- [ ] Chrome installed (any version)
- [ ] All source files exist (see project structure above)
- [ ] Run `.\run_tests_with_diagnostics.bat` and save the log
- [ ] Copy error message from log
- [ ] Share both the error and log contents

---

## 📞 How to Get Help

If tests still fail after checking above:

1. **Run the diagnostic script:**
   ```powershell
   .\run_tests_with_diagnostics.bat
   ```

2. **Save the log output:**
   ```powershell
   # Find latest log
   dir logs
   type logs\test_run_*.log
   ```

3. **Share the following:**
   - Java version output: `java -version`
   - Maven version output: `mvn -version`
   - Chrome location: (where it's installed)
   - **Last 50-100 lines of the test log** (with ERROR/Exception keywords)

4. **I'll diagnose and provide specific fix**

---

## ✅ Success Indicators

After running tests successfully, you should see:

1. ✅ Console shows: `[INFO] BUILD SUCCESS`
2. ✅ Folder created: `target/extent-reports/`
3. ✅ Folder created: `target/screenshots/`
4. ✅ File exists: `target/extent-reports/ExtentReport_*.html`
5. ✅ Screenshots exist: `target/screenshots/*.png`
6. ✅ HTML opens in browser with test results
7. ✅ Screenshots visible when clicking test steps

---

## 🚀 Next Steps

### Immediate:
```powershell
# Run diagnostic test
.\run_tests_with_diagnostics.bat

# If PASSED:
.\publish_report.bat
start reports\ExtentReport_*.html

# If FAILED:
# Share the error from the log file
```

---

**Last Updated:** August 13, 2026  
**Status:** Comprehensive troubleshooting guide
