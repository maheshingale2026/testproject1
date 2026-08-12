# Test Failure - Diagnosis Guide

I've created comprehensive diagnostic tools to help identify why tests are failing.

## ⚡ Quick Diagnosis (5 Minutes)

### Step 1: Run the Diagnostic Script
```powershell
cd C:\Users\MaheshIngale\git\testproject1
.\run_tests_with_diagnostics.bat
```

This script will:
- ✅ Check Java installation
- ✅ Check Maven installation  
- ✅ Check Chrome installation
- ✅ Run tests with full debug output
- ✅ Save detailed log to: `logs/test_run_*.log`
- ✅ Show if tests pass or fail

### Step 2: If Tests Fail - Check the Log
```powershell
# View the error log
type logs\test_run_*.log | more

# Or in PowerShell:
Get-Content logs\test_run_*.log -Tail 100
```

### Step 3: Share the Error
Copy the error message and share it with me. I'll provide the exact fix.

---

## 🔍 Most Common Failure Causes

### 1. Maven Not Installed
```
Error: mvn : The term 'mvn' is not recognized
```
**Fix:** Install Maven from https://maven.apache.org/download.cgi and add to PATH

### 2. Java Not Found
```
Error: java : The term 'java' is not recognized  
```
**Fix:** Install Java 11+ from https://www.oracle.com/java/technologies/downloads/

### 3. Chrome Not Installed
```
Error: ChromeDriver unable to discover chrome binary
```
**Fix:** Install Chrome from https://google.com/chrome

### 4. WebDriver Download Failed
```
Error: Failed to download ChromeDriver
```
**Fix:** Check internet connection; WebDriverManager auto-downloads on first run

### 5. Screenshot Directory Not Writable
```
Error: Failed to capture screenshot / NoSuchFileException
```
**Fix:** Create folder manually:
```powershell
mkdir target\screenshots
mkdir target\extent-reports
```

### 6. Test Timeout
```
Error: TimeoutException / The operation timed out
```
**Fix:** Check internet, Google may be slow; retry test

### 7. Step Definition Not Found
```
Error: UndefinedStepException
```
**Fix:** Ensure `GoogleNavigationSteps.java` exists in correct package: 
`src/test/java/com/digitalclock/stepdefinitions/`

---

## 📋 Prerequisites Checklist

Before running tests, verify:

```powershell
# Check Java (need 11+)
java -version

# Check Maven (need 3.6+)
mvn -version

# Check Chrome installed
dir "C:\Program Files\Google\Chrome\Application\chrome.exe"
```

If any show errors, install the missing component.

---

## 🎯 What to Do Now

### Option A: Quick Diagnosis
1. Run: `.\run_tests_with_diagnostics.bat`
2. Wait for completion
3. Check output - it will say **PASS** or **FAIL**
4. If **FAIL**, share the error from `logs/test_run_*.log`

### Option B: Detailed Logging
```powershell
# Run with maximum verbosity
mvn clean test -X > test_output.log 2>&1

# View errors:
type test_output.log | findstr /I "error exception fail"
```

### Option C: Check Individual Prerequisites
```powershell
# Test Java
java -version

# Test Maven  
mvn -version

# Test Chrome
"C:\Program Files\Google\Chrome\Application\chrome.exe" --version

# Test folders
mkdir target\screenshots
mkdir target\extent-reports
```

---

## 📞 When You Have the Error

Once you run `.\run_tests_with_diagnostics.bat` and see an error:

1. **Copy the error message** from the log file
2. **Note which step failed** (given/when/then)
3. **Share with me:**
   - Java version: `java -version`
   - Maven version: `mvn -version`  
   - The error message (last 20 lines of log)

I'll provide the exact fix immediately.

---

## ✅ Expected Success Output

When tests **PASS**, you'll see:
```
✓✓✓ TESTS PASSED ✓✓✓

Reports generated in: target\extent-reports\
Screenshots saved in: target\screenshots\

To publish reports to repository:
  .\publish_report.bat
```

Then run:
```powershell
.\publish_report.bat
start reports\ExtentReport_*.html
```

---

## 📚 Documentation Available

I've created comprehensive guides:

| Document | Purpose |
|----------|---------|
| **TROUBLESHOOTING.md** | Detailed troubleshooting for all issues |
| **run_tests_with_diagnostics.bat** | Auto-diagnostic script |
| **QUICK_START.md** | Fast setup reference |
| **UNIFIED_REPORTS.md** | Reports workflow guide |
| **SELENIUM_README.md** | Full framework documentation |

---

## 🚀 Next Action

**RUN THIS NOW:**

```powershell
cd C:\Users\MaheshIngale\git\testproject1
.\run_tests_with_diagnostics.bat
```

Then share:
1. Whether it says **PASS** or **FAIL**
2. If **FAIL**, copy the error message from `logs/test_run_*.log`

I'll fix it immediately! 💯
