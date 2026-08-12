@echo off
REM Selenium Automation Test Runner Script for Windows

echo.
echo ================================
echo Digital Clock Automation Tests
echo ================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo Maven is not installed or not in PATH.
    echo Please install Maven and add it to system PATH.
    pause
    exit /b 1
)

echo Maven found successfully.
echo.

echo Cleaning previous build...
call mvn clean

echo.
echo Installing dependencies...
call mvn install -DskipTests

echo.
echo Running Selenium Automation Tests...
call mvn test

echo.
echo.
echo ================================
echo Test Execution Completed!
echo ================================
echo.
echo Reports generated at:
echo - HTML Report: target\extent-reports\
echo - Cucumber Report: target\cucumber-reports\index.html
echo - JUnit Report: target\cucumber-reports\
echo.
pause
