package com.crud.test.runner;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format = {"json:target/cucumber-json-report.json", "html:target/cucumber-report-html"},
        glue = {"com/vmd/test/steps"},
        features = {"src/test/java/com/vmd/test/features"}
       // , tags = "@Smoke",
        //monochrome = true
)
public class SmokeTestRunner {

}
