package com.crud.test.runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/main/resources/com/crud/test/features"},
        format = {"json:target/cucumber-json-report.json", "html:target/cucumber-report-html"},
        glue = {"com/crud/test/steps"}
        , tags = "@Test01"
        )
public class CucumberRunner {

}
