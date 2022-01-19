package com.fjsimon.nexus.store.bdd;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports/cucumber-reports.html", "json:target/cucumber-reports/cucumber-reports.json"},
        features = "src/test/resources/features",
        glue = {"com.fjsimon.nexus.store.bdd.stepdefs", "com.fjsimon.nexus.store.bdd"}
)
public class CucumberTest {

}