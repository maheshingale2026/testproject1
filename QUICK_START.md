# Quick Start Guide - Selenium Automation Setup

## ✅ Project Successfully Created!

Your complete Selenium BDD automation framework with Cucumber, Maven, JUnit, and Extent Reports is ready!

---

## 📋 What's Included

### 1. **Maven Configuration** (pom.xml)
   - Selenium WebDriver 4.15.0
   - Cucumber Java & JUnit 7.14.0
   - JUnit 4.13.2
   - Extent Reports 5.1.1
   - WebDriverManager (auto-driver management)
   - SLF4J Logging

### 2. **BDD Feature Files**
   - `src/test/resources/features/GoogleNavigation.feature`
   - Gherkin scenario to open Google.com

### 3. **Automation Code**
   - **DriverManager.java** - Selenium WebDriver utilities
   - **GoogleNavigationSteps.java** - Cucumber step definitions
   - **TestRunner.java** - JUnit test runner for Cucumber
   - **ExtentReportManager.java** - HTML test reports

### 4. **Configuration**
   - `src/test/resources/config.properties` - Test settings
   - `run_tests.bat` - Windows batch script to run tests

---

## 🚀 Getting Started

### Step 1: Install Maven (if not installed)
Download from: https://maven.apache.org/download.cgi

### Step 2: Navigate to Project Directory
```bash
cd C:\Users\MaheshIngale\git\testproject1
```

### Step 3: Download Dependencies
```bash
mvn clean install
```

### Step 4: Run Tests

**Option A - Command Line (Maven):**
```bash
mvn test
```

**Option B - Windows Batch Script:**
```bash
run_tests.bat
```

**Option C - Eclipse:**
1. Right-click `TestRunner.java`
2. Select `Run As` → `JUnit Test`

---

## 📊 View Test Reports

### After running tests, find reports at:

1. **Extent HTML Report** (Beautiful Dashboard)
   ```
   target/extent-reports/ExtentReport_[timestamp].html
   ```

2. **Cucumber HTML Report** (Step-by-step)
   ```
   target/cucumber-reports/index.html
   ```

3. **JUnit XML Report** (CI/CD integration)
   ```
   target/cucumber-reports/cucumber.xml
   ```

4. **Console Output** (Terminal/Eclipse Console)
   - Detailed step execution logs
   - Test status indicators (✓/✗)

---

## 📝 What The Tests Do

The automation script will:

1. ✅ Launch Chrome browser
2. ✅ Navigate to https://www.google.com
3. ✅ Verify page loaded correctly
4. ✅ Validate page title contains "Google"
5. ✅ Close the browser
6. ✅ Generate detailed HTML reports

---

## 🔧 Modifying Tests

### To Add New Tests:

1. **Create Feature File** (`.feature`)
   ```gherkin
   Feature: New Test
     Scenario: Do something
       Given Some precondition
       When User performs action
       Then Verify result
   ```

2. **Implement Steps** in `GoogleNavigationSteps.java`
   ```java
   @Given("Some precondition")
   public void somePrecondition() {
       // Implementation
   }
   ```

3. **Run Tests**
   ```bash
   mvn test
   ```

---

## 🛠️ Troubleshooting

### Maven not found?
- Install Maven
- Add to system PATH
- Restart terminal

### Chrome driver issues?
- WebDriverManager handles this automatically
- Ensure Chrome is installed

### Reports not generated?
- Check `target/` directory
- Ensure write permissions
- Check Maven execution logs

### Cucumber steps not recognized?
- Verify package name in TestRunner glue path
- Check @Given, @When, @Then annotations

---

## 📁 Project Structure

```
testproject1/
├── src/test/
│   ├── java/com/digitalclock/
│   │   ├── stepdefinitions/
│   │   │   └── GoogleNavigationSteps.java
│   │   ├── runners/
│   │   │   └── TestRunner.java
│   │   ├── tests/
│   │   │   └── TimeZoneManagerTest.java
│   │   └── utils/
│   │       ├── DriverManager.java
│   │       ├── ExtentReportManager.java
│   │       └── TimeZoneManager.java
│   └── resources/
│       ├── features/
│       │   └── GoogleNavigation.feature
│       └── config.properties
├── target/
│   ├── extent-reports/     (HTML reports)
│   ├── cucumber-reports/   (Cucumber reports)
│   └── screenshots/        (Test screenshots)
├── pom.xml                  (Maven config)
├── run_tests.bat           (Run script)
└── SELENIUM_README.md      (Full documentation)
```

---

## 🔗 GitHub Repository

Push your code:
```bash
git add .
git commit -m "Your message"
git push origin main
```

Repository: https://github.com/maheshingale2026/testproject1

---

## 💡 Tips

- **Console Output**: Each test step prints to console with ✓/✗ indicators
- **Extent Reports**: Beautiful HTML with charts, pass/fail analysis, timing
- **Screenshots**: Failures capture browser screenshots automatically
- **WebDriverManager**: Auto-downloads compatible Chrome driver
- **Parallel Execution**: Can run multiple tests using Maven plugins

---

## 📚 Full Documentation

For detailed information, see: `SELENIUM_README.md`

---

## ✨ You're All Set!

Run your first test now:
```bash
mvn test
```

Check reports in: `target/extent-reports/`

Happy Testing! 🎉
