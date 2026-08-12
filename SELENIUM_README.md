# Digital Clock Selenium Automation Project

This project combines unit testing with Selenium automation testing using Cucumber BDD framework.

## Project Structure

```
testproject1/
├── src/
│   ├── test/
│   │   ├── java/
│   │   │   └── com/digitalclock/
│   │   │       ├── stepdefinitions/      # Cucumber step definitions
│   │   │       ├── runners/              # Test runners
│   │   │       ├── tests/                # Unit tests
│   │   │       └── utils/                # Utilities (WebDriver, Reports, etc.)
│   │   └── resources/
│   │       ├── features/                 # Cucumber feature files (.feature)
│   │       └── config.properties         # Configuration properties
├── bin/                                   # Compiled binaries
├── target/                               # Maven build output
├── pom.xml                               # Maven dependencies and plugins
└── README.md                             # This file
```

## Features

### Unit Testing
- **TimeZoneManager Tests** - Tests for timezone management functionality
- JUnit 4 framework
- Comprehensive assertions and validation

### Selenium Automation Testing
- **Cucumber BDD** - Behavior-Driven Development using Gherkin syntax
- **Selenium WebDriver** - Browser automation
- **Chrome Driver** - WebDriverManager for automatic driver management
- **Extent Reports** - Beautiful HTML test reports
- **Maven** - Build automation and dependency management

## Prerequisites

- Java 11 or higher
- Maven 3.6+ 
- Chrome browser installed
- Git

## Dependencies

Key dependencies included in pom.xml:
- Selenium WebDriver 4.15.0
- Cucumber Java 7.14.0
- JUnit 4.13.2
- Extent Reports 5.1.1
- WebDriverManager 5.6.3
- SLF4J Logging

## Installation & Setup

### 1. Clone/Download Project
```bash
cd testproject1
```

### 2. Install Dependencies
```bash
mvn clean install
```

## Running Tests

### Run All Tests (Unit + Automation)
```bash
mvn test
```

### Run Only Selenium Tests
```bash
mvn test -Dtest=TestRunner
```

### Run Specific Feature
```bash
mvn test -Dtest=TestRunner#GoogleNavigation
```

### Run with Maven Surefire
```bash
mvn clean test verify
```

## Test Reports

### Extent Reports
After running tests, access the HTML report at:
```
target/extent-reports/ExtentReport_[timestamp].html
```

### Cucumber Reports
HTML Cucumber report location:
```
target/cucumber-reports/index.html
```

### JUnit Reports
XML JUnit reports location:
```
target/cucumber-reports/cucumber.xml
```

## Project Components

### 1. Feature Files
**File:** `src/test/resources/features/GoogleNavigation.feature`

Defines BDD scenarios in Gherkin syntax:
- User launches Chrome browser
- Navigates to Google.com
- Verifies page title
- Closes browser

### 2. Step Definitions
**File:** `src/test/java/com/digitalclock/stepdefinitions/GoogleNavigationSteps.java`

Implements Gherkin steps:
- Browser launch
- URL navigation
- Page validation
- Browser closure
- Logging to Extent Reports

### 3. WebDriver Manager
**File:** `src/test/java/com/digitalclock/utils/DriverManager.java`

Handles:
- Browser initialization
- Chrome driver setup
- Window management
- Navigation methods
- Driver cleanup

### 4. Report Manager
**File:** `src/test/java/com/digitalclock/utils/ExtentReportManager.java`

Provides:
- Report initialization
- Test logging
- Pass/Fail/Info/Warning logs
- Report generation

### 5. Test Runner
**File:** `src/test/java/com/digitalclock/runners/TestRunner.java`

JUnit runner for Cucumber:
- Scans feature files
- Maps step definitions
- Generates reports
- Executes tests

### 6. Unit Tests
**File:** `src/test/java/com/digitalclock/tests/TimeZoneManagerTest.java`

Tests for:
- TimeZoneManager functionality
- TimeZoneData model
- Add/Remove timezone operations
- Console output printing

## Configuration

Edit `src/test/resources/config.properties` to customize:
- Browser type
- Application URLs
- Timeout values
- Report paths
- Logging levels

## Test Execution Workflow

1. **Feature File** (GoogleNavigation.feature)
   ↓
2. **Test Runner** (TestRunner.java)
   ↓
3. **Step Definitions** (GoogleNavigationSteps.java)
   ↓
4. **WebDriver Manager** (DriverManager.java)
   ↓
5. **Extent Reports** (ExtentReportManager.java)
   ↓
6. **HTML Report** (target/extent-reports/)

## Adding New Tests

### 1. Create Feature File
```gherkin
# src/test/resources/features/NewFeature.feature
Feature: New Test Scenario

  Scenario: Perform some action
    Given User is on some page
    When User performs an action
    Then Verify expected result
```

### 2. Implement Step Definitions
```java
// Add methods to GoogleNavigationSteps.java or create new class
@Given("User is on some page")
public void userIsOnSomePage() {
    // Implementation
}
```

### 3. Run Tests
```bash
mvn test
```

## Troubleshooting

### Chrome Driver Issues
- Ensure Chrome browser is installed
- WebDriverManager will auto-download compatible driver

### Maven Build Fails
```bash
mvn clean install -U
```

### Report Not Generated
- Check `target/extent-reports/` directory
- Ensure write permissions

### Step Definitions Not Found
- Verify package path matches glue path in TestRunner
- Check @Before and @After annotations

## Contributing

1. Create feature files for new scenarios
2. Implement step definitions
3. Run tests and verify reports
4. Commit changes to Git

## Git Push

```bash
git add .
git commit -m "Add new test scenarios"
git push origin main
```

## Project URL

Repository: https://github.com/maheshingale2026/testproject1

## Contact & Support

For issues or questions, please create an issue in the GitHub repository.

## License

This project is available for educational and testing purposes.

---

**Last Updated:** August 12, 2026
**Version:** 1.0.0
