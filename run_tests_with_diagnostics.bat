@echo off
setlocal enabledelayedexpansion

echo.
echo ========================================
echo Selenium Automation Test Diagnostics
echo ========================================
echo.

REM Check Java
echo [1/6] Checking Java installation...
java -version 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Java not found or not in PATH
    echo Please install Java 11+ and add to system PATH
    exit /b 1
)
echo ✓ Java found
echo.

REM Check Maven
echo [2/6] Checking Maven installation...
where mvn >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Maven not found in PATH
    echo Please install Maven and add to system PATH
    echo Download from: https://maven.apache.org/download.cgi
    exit /b 1
)
echo ✓ Maven found
echo.

REM Check Chrome
echo [3/6] Checking Chrome installation...
where chrome.exe >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo WARNING: Chrome.exe not in PATH
    echo Checking Program Files...
    if exist "C:\Program Files\Google\Chrome\Application\chrome.exe" (
        echo ✓ Chrome found in Program Files
    ) else if exist "C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" (
        echo ✓ Chrome found in Program Files (x86)
    ) else (
        echo ERROR: Chrome not found
        echo Please install Chrome from: https://google.com/chrome
        exit /b 1
    )
) else (
    echo ✓ Chrome found in PATH
)
echo.

REM Create logs directory
echo [4/6] Setting up logging...
if not exist logs mkdir logs
set LOGFILE=logs\test_run_%DATE:~-4%-%DATE:~-10,2%-%DATE:~-7,2%_%TIME:~0,2%-%TIME:~5,2%-%TIME:~10,2%.log
set LOGFILE=%LOGFILE: =0%
echo ✓ Logs will be saved to: %LOGFILE%
echo.

REM Clean previous builds
echo [5/6] Cleaning previous build artifacts...
mvn clean -q 2>nul
echo ✓ Cleaned
echo.

REM Run tests with full output
echo [6/6] Running Cucumber tests...
echo.
echo Starting TestRunner...
echo ============================================
mvn -Dtest=TestRunner test -X > %LOGFILE% 2>&1
set RESULT=%ERRORLEVEL%
echo ============================================
echo.

REM Show results
if %RESULT% EQU 0 (
    echo.
    echo ✓✓✓ TESTS PASSED ✓✓✓
    echo.
    echo Reports generated in: target\extent-reports\
    echo Screenshots saved in: target\screenshots\
    echo.
    echo To publish reports to repository:
    echo   .\publish_report.bat
    echo.
) else (
    echo.
    echo ✗✗✗ TESTS FAILED (Exit Code: %RESULT%) ✗✗✗
    echo.
    echo Full diagnostic log saved to:
    echo   %LOGFILE%
    echo.
    echo Checking for common issues...
    echo.
    
    REM Check for WebDriver issues
    findstr /M "WebDriver" %LOGFILE% >nul 2>&1
    if %ERRORLEVEL% EQU 0 (
        echo ⚠ WebDriver-related error detected
        echo   - Ensure Chrome is installed
        echo   - Check Chrome version matches driver version
        echo   - WebDriverManager will auto-download compatible driver
    )
    
    REM Check for connection issues
    findstr /M "Connection" %LOGFILE% >nul 2>&1
    if %ERRORLEVEL% EQU 0 (
        echo ⚠ Connection-related error detected
        echo   - Check internet connection (needed for WebDriverManager)
        echo   - Check firewall settings
    )
    
    REM Check for screenshot issues
    findstr /M "screenshot" %LOGFILE% >nul 2>&1
    if %ERRORLEVEL% EQU 0 (
        echo ⚠ Screenshot-related error detected
        echo   - Check target\screenshots folder permissions
        echo   - Ensure disk space available
    )
    
    REM Check for report issues
    findstr /M "ExtentReport\|Extent" %LOGFILE% >nul 2>&1
    if %ERRORLEVEL% EQU 0 (
        echo ⚠ Report-related error detected
        echo   - Check target\extent-reports folder permissions
        echo   - Ensure disk space available
    )
    
    echo.
    echo RECOMMENDED ACTIONS:
    echo 1. Review detailed log:
    echo    type %LOGFILE%
    echo.
    echo 2. Copy the error message from log and share it
    echo.
    echo 3. Check:
    echo    - Java: java -version
    echo    - Maven: mvn -version
    echo    - Chrome installed: where chrome
    echo    - Disk space: dir target\
    echo.
)

echo.
pause
exit /b %RESULT%
