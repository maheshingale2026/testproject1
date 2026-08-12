package com.digitalclock.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.digitalclock.stepdefinitions"},
    plugin = {
        "pretty",
        "html:target/cucumber-reports/index.html",
        "json:target/cucumber-reports/cucumber.json",
        "junit:target/cucumber-reports/cucumber.xml"
    },
    monochrome = true,
    stepNotifications = true
)
public class TestRunner {
}
