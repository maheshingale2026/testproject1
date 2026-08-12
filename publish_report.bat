@echo off
setlocal enabledelayedexpansion

echo.
echo =============================
echo Publish Extent Reports to repository
echo =============================

REM Check for extent report folder
if not exist target\extent-reports (
    echo No extent reports found in target\extent-reports
    echo Run the tests first to generate reports.
    pause
    exit /b 1
)

REM Create destination folders
if not exist reports\extent-reports mkdir reports\extent-reports
if not exist reports\screenshots mkdir reports\screenshots

echo Copying extent reports...
robocopy target\extent-reports reports\extent-reports /MIR /NFL /NDL /NJH /NJS >nul
if %ERRORLEVEL% GEQ 8 (
    echo Robocopy failed copying extent reports.
    exit /b 1
)

echo Copying screenshots (if any)...
if exist target\screenshots (
    robocopy target\screenshots reports\screenshots /MIR /NFL /NDL /NJH /NJS >nul
)

echo Adding files to git...
"%PROGRAMFILES%\Git\cmd\git.exe" add reports\extent-reports reports\screenshots 2>nul || git add reports\extent-reports reports\screenshots

set timestamp=%DATE%_%TIME%
set timestamp=%timestamp::=_%
set timestamp=%timestamp:/=_%
set timestamp=%timestamp:.=_%

git commit -m "Add latest Extent report and screenshots - %timestamp%" 2>nul || (
    echo Nothing to commit or git commit failed
)

echo Pushing to origin main...
"%PROGRAMFILES%\Git\cmd\git.exe" push origin main 2>nul || git push origin main

echo Done. Reports are copied to reports\extent-reports and reports\screenshots
pause
