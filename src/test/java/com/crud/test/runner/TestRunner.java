package com.crud.test.runner;


import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.runtime.model.CucumberTagStatement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

//import org.junit.Test;

//@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/com/vmd/test/features"}, glue = {"com/vmd/test/steps"}, format = {"json:target/cucumber-json-report.json",
        "html:target/cucumber-report-html","html:target/test-output/junitreports","junit:target/cucumber-reports/Cucumber.xml"})
//public class TestRunner extends AbstractTestNGCucumberTests{ // "html:target/test-output/junitreports",
public class TestRunner {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {

        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }


    @Test(dataProvider = "features")
    public void runTest(CucumberFeatureWrapper cucumberFeatureWrapper){
        //new TestNGCucumberRunner(getClass()).runCukes();

        List<CucumberTagStatement> elements = cucumberFeatureWrapper.getCucumberFeature().getFeatureElements();
        for (Iterator<CucumberTagStatement> element = elements.iterator(); element.hasNext(); ) {
            //ToDo: Bring the scenario name from excel sheet using the out-of-box library we have in utilities package

            //pass the hardcoded scenario name
            CucumberTagStatement scenarioName = element.next(); //give list of all scenario name
            //if (!scenarioName.getVisualName().equals("Scenario: Check Login with correct username and password")) {
            if (!scenarioName.getVisualName().contains("Scenario:") && !scenarioName.getVisualName().contains("Scenario Outline:")) {
                element.remove();
            }
        }
        //todo: insert the feature name
        //ExtendReport.startFeature("");
        testNGCucumberRunner.runCucumber(cucumberFeatureWrapper.getCucumberFeature());//ToDo: to run all cucumber features
    }

//    @Test(dataProvider = "features")
//    public void SmokeTest(CucumberFeatureWrapper cucumberFeatureWrapper) {
//        //new TestNGCucumberRunner(getClass()).runCukes();
//
//        List<CucumberTagStatement> elements = cucumberFeatureWrapper.getCucumberFeature().getFeatureElements();
//        for (Iterator<CucumberTagStatement> element = elements.iterator(); element.hasNext(); ) {
//            //ToDo: Bring the scenario name from excel sheet using the out-of-box library we have in utilities package
//
//            //pass the hardcoded scenario name
//            CucumberTagStatement scenarioName = element.next(); //give list of all scenario name
//            //if (!scenarioName.getVisualName().equals("Scenario: Check Login with correct username and password")) {
//            if (!scenarioName.getVisualName().contains("Scenario Outline: Submit new report bug 9827 with unique Message Number ONLY ") &&
//                    !scenarioName.getVisualName().contains("Scenario Outline: Submit new report bug 9827 with unique Message Number and Worldwide Case Number") &&
//                    !scenarioName.getVisualName().contains("Scenario: Check Login with correct username and password") &&
//                    !scenarioName.getVisualName().contains("Scenario Outline: Submitted Report Record") &&
//                    !scenarioName.getVisualName().contains("Scenario Outline: Not Submitted Report Record") &&
//                    !scenarioName.getVisualName().contains("Scenario: Check Login with wrong correct username and password")) {
//                element.remove();
//            }
//        }
//
//        //todo: insert the feature name
//        //ExtendReport.extentReports.createTest(Feature.class, "");
//        //ExtendReport.startFeature("");
//        testNGCucumberRunner.runCucumber(cucumberFeatureWrapper.getCucumberFeature());//ToDo: to run all cucumber features
//    }
//
//    @Test(dataProvider = "features")
//    public void SanityTest(CucumberFeatureWrapper cucumberFeatureWrapper) {
//        //new TestNGCucumberRunner(getClass()).runCukes();
//
//        List<CucumberTagStatement> elements = cucumberFeatureWrapper.getCucumberFeature().getFeatureElements();
//        for (Iterator<CucumberTagStatement> element = elements.iterator(); element.hasNext(); ) {
//            //ToDo: Bring the scenario name from excel sheet using the out-of-box library we have in utilities package
//
//            //pass the hardcoded scenario name
//            CucumberTagStatement scenarioName = element.next(); //give list of all scenario name
//            //if (!scenarioName.getVisualName().equals("Scenario: Check Login with correct username and password")) {
//            if (!scenarioName.getVisualName().contains("Scenario Outline:")) {
//                element.remove();
//            }
//        }
//
//        //todo: insert the feature name
//        //ExtendReport.extentReports.createTest(Feature.class, "");
//        //ExtendReport.startFeature("");
//        testNGCucumberRunner.runCucumber(cucumberFeatureWrapper.getCucumberFeature());//ToDo: to run all cucumber features
//    }
//
//    //@Test(dataProvider = "features")
//    public void SanityTest2(CucumberFeatureWrapper cucumberFeatureWrapper) {
//        //new TestNGCucumberRunner(getClass()).runCukes();
//
//        List<CucumberTagStatement> elements = cucumberFeatureWrapper.getCucumberFeature().getFeatureElements();
//        for (Iterator<CucumberTagStatement> element = elements.iterator(); element.hasNext(); ) {
//            //ToDo: Bring the scenario name from excel sheet using the out-of-box library we have in utilities package
//
//            //pass the hardcoded scenario name
//            CucumberTagStatement scenarioName = element.next(); //give list of all scenario name
//            //if (!scenarioName.getVisualName().equals("Scenario: Check Login with correct username and password")) {
//            if (!scenarioName.getVisualName().contains("Scenario: 2: Non MAH User") &&
//                    !scenarioName.getVisualName().contains("Scenario: 3: MAH User") &&
//                    !scenarioName.getVisualName().contains("Scenario: 1.13 Remove previously supplied \"Linked Report\" Details") &&
//                    !scenarioName.getVisualName().contains("Scenario: 1.12 Updating a previously supplied \"Linked Report\" Details") &&
//                    !scenarioName.getVisualName().contains("Scenario: 1.9 Updating a previously supplied \"Suspect Duplicate\" Details") &&
//                    !scenarioName.getVisualName().contains("  Scenario: 1.2 Provision of \"Species\"") &&
//                    !scenarioName.getVisualName().contains("Scenario Outline: 8: Accessing Event Details once MAH saved and continued") &&
//                    !scenarioName.getVisualName().contains("Scenario: 5: MAH has elected to create an Adverse Event report, saves & continues having provided some/all information incorrectly") &&
//                    !scenarioName.getVisualName().contains("Scenario: 3.2: MAH elects to continue with completing the report") &&
//                    !scenarioName.getVisualName().contains("Scenario: 8: Accessing Other Involved Parties once MAH saved and continued") &&
//                    !scenarioName.getVisualName().contains("Scenario: 5: MAH has elected to create an Adverse Event report, saves & continues provided having provided some/all information incorrectly") &&
//                    !scenarioName.getVisualName().contains("Scenario: 3.1: MAH elects to discard report") &&
//                    !scenarioName.getVisualName().contains("Scenario: 5: MAH has elected to provide a Suspect Product Assessment, saves & continues having provided some/all information incorrectly") &&
//                    !scenarioName.getVisualName().contains("Scenario: 4: MAH has elected to provide a Suspect Product Assessment, saves & continues having provided all necessary information correctly") &&
//                    !scenarioName.getVisualName().contains("Scenario: 3.2: MAH elects to continue with completing the report") &&
//                    !scenarioName.getVisualName().contains("Scenario: 4.3: MAH has elected to add or edit a Treatment Product, saves & continues having provided some/all information incorrectly") &&
//                    !scenarioName.getVisualName().contains("Scenario: 4.2: MAH has elected to add a Treatment Product and elects to save having provided all necessary information correctly") &&
//                    !scenarioName.getVisualName().contains("Scenario: 3.3: MAH elects to continue with completing") &&
//                    !scenarioName.getVisualName().contains("Scenario: 8: Provision of Selecting a \"Substance Name\"") &&
//                    !scenarioName.getVisualName().contains("Scenario: 5: MAH has elected to provide a Treatment Substance, saves & continues having provided some/all information incorrectly") &&
//                    !scenarioName.getVisualName().contains("Scenario: 4: MAH has elected to provide a Treatment Substance, saves & continues having provided all necessary information correctly") &&
//                    !scenarioName.getVisualName().contains("Scenario: 1.4 Remove previously supplied \"Literature Reference\" Details") &&
//                    !scenarioName.getVisualName().contains("Scenario: 4.2: MAH has elected to provide a Suspect Substance and elects to save having provided all necessary information correctly") &&
//                    !scenarioName.getVisualName().contains("Scenario: 3.2: MAH elects to continue with completing the report") &&
//                    !scenarioName.getVisualName().contains("Scenario: 1.8 Provision to \"Add a Treatment Substance\"") &&
//                    !scenarioName.getVisualName().contains("Scenario Outline: Restrict input to 1 or 2 characters") &&
//                    !scenarioName.getVisualName().contains("Scenario: 3.1: MAH elects to discard report") &&
//                    !scenarioName.getVisualName().contains("Scenario: 3.1: MAH elects to discard report")) {
//                element.remove();
//            }
//        }
//
//        //todo: insert the feature name
//        //ExtendReport.extentReports.createTest(Feature.class, "");
//        //ExtendReport.startFeature("");
//        testNGCucumberRunner.runCucumber(cucumberFeatureWrapper.getCucumberFeature());//ToDo: to run all cucumber features
//    }


    @DataProvider
    public Object[][] features() {

        return testNGCucumberRunner.provideFeatures();// ToDo: provide all the features available
    }
  /*  @DataProvider
    public Object[] features(ITestContext context){
        var featureName = testNGCucumberRunner.provideFeatures();
        Object[] getFeature = null;
        for(var item : featureName){
            if(item[0].toString().contains(context.getName())){
                getFeature = item;
            }
        }
        return getFeature;
    }*/


    @AfterClass(alwaysRun = true)
    public void afterClass() {

        testNGCucumberRunner.finish();
    }
}
