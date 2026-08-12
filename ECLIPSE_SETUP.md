# Eclipse Configuration - Fix Import Errors

## What Was Done

The project has been configured as a Maven project in Eclipse by:

1. ✅ Updated `.project` file - Added Maven2Nature to Eclipse project configuration
2. ✅ Updated `.classpath` file - Configured Maven classpath container (M2_REPO)
3. ✅ Created `pom.xml` - Maven configuration with all required dependencies

## Required Actions in Eclipse

### Step 1: Refresh Maven Project Configuration

1. Right-click on the **testproject1** project in Eclipse
2. Select **Maven** → **Update Project** (or press **Alt+F5**)
3. Click **OK** to update
4. Wait for Eclipse to download dependencies (may take 1-2 minutes)

### Step 2: Clean and Rebuild

1. Select **testproject1** project
2. Go to **Project** → **Clean** → Select **testproject1** → **Clean**
3. Wait for rebuild to complete

### Step 3: Check Project Properties (Optional)

1. Right-click **testproject1** → **Properties**
2. Look for "Java Build Path" or "Maven"
3. Verify "Classpath is set up correctly"

---

## Expected Results

After these steps, Eclipse should:
- ✅ Recognize all Maven dependencies
- ✅ Show no "unresolved import" errors
- ✅ Auto-complete Selenium, Cucumber, and Extent Reports classes
- ✅ Allow you to run tests

---

## If Errors Still Appear

### Option A: Force Maven Index Update
1. Window → Preferences → Maven → User Settings
2. Click "Update Settings"
3. Go to Maven → Repositories
4. Click "Rebuild Index" for central repository

### Option B: Delete .m2 Cache and Re-download
```bash
# Windows Command Prompt:
rmdir /s %USERPROFILE%\.m2\repository
```
Then repeat Step 1 above to re-download all dependencies.

### Option C: Check Maven Installation
```bash
where mvn
mvn -version
```

If Maven is not found in PATH:
1. Download Maven from: https://maven.apache.org/
2. Extract to: `C:\Program Files\maven`
3. Add to system PATH: `C:\Program Files\maven\bin`
4. Restart Eclipse

---

## Command Line Build (Alternative)

If you want to build from command line instead of Eclipse:

```bash
cd C:\Users\MaheshIngale\git\testproject1

# Build project
mvn clean install

# Run tests
mvn test

# Create Eclipse files (if needed)
mvn eclipse:eclipse
```

---

## Verification

To verify everything is correctly set up, check:

1. **No Error Markers** on project files:
   - ✅ DriverManager.java
   - ✅ GoogleNavigationSteps.java
   - ✅ TestRunner.java
   - ✅ ExtentReportManager.java

2. **Dependencies Available** - You should see:
   - org.openqa.selenium.*
   - io.cucumber.*
   - com.aventstack.extentreports.*

3. **Build Success** - No red X marks on project

---

## Still Having Issues?

Try these troubleshooting steps:

1. **Restart Eclipse** - Sometimes helps refresh Maven metadata
2. **Delete .classpath and let Eclipse regenerate** - Right-click → Maven → Update Project
3. **Check firewall** - Maven needs to download from internet
4. **Use Maven command line** to verify: `mvn compile`

---

## Next Steps

Once errors are resolved:

1. Open `GoogleNavigation.feature` file
2. Right-click `TestRunner.java` → **Run As** → **JUnit Test**
3. Watch Console tab for test execution
4. Check `target/extent-reports/` for HTML report

---

**After following these steps, all import errors should be resolved!** ✅
