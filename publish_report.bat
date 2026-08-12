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

REM Create single destination folder (not separated)
if not exist reports mkdir reports

echo Copying extent reports and screenshots into reports folder...
REM Copy extent reports (HTML, CSS, JS, JSON)
robocopy target\extent-reports reports /E /NFL /NDL /NJH /NJS >nul 2>&1

REM Copy screenshots into the same reports folder (if any)
if exist target\screenshots (
    robocopy target\screenshots reports /E /NFL /NDL /NJH /NJS >nul 2>&1
)

echo Adding files to git...
"%PROGRAMFILES%\Git\cmd\git.exe" add reports 2>nul || git add reports

set timestamp=%DATE%_%TIME%
set timestamp=%timestamp::=_%
set timestamp=%timestamp:/=_%
set timestamp=%timestamp:.=_%

echo Committing report...
"%PROGRAMFILES%\Git\cmd\git.exe" commit -m "Add latest Extent report with screenshots - %timestamp%" 2>nul || (
    echo Nothing to commit or git commit failed
)

echo Pushing to origin main...
"%PROGRAMFILES%\Git\cmd\git.exe" push origin main 2>nul || git push origin main

echo.
echo =============================
echo Done! Reports copied to reports\ folder
echo Open: reports\ExtentReport_*.html
echo =============================
pause
