package com.crud.test.steps;


import com.crud.framework.base.DriverContext;
import com.crud.framework.base.FrameworkInitialize;
import com.crud.framework.base.LocalDriverContext;
import com.crud.framework.config.ConfigReader;
import com.crud.framework.config.Settings;
import com.crud.framework.utilities.*;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;



public class TestInitialize extends FrameworkInitialize {


    @Before
    public void Initialize(Scenario scenario) throws IOException {

        //todo: insert the scenario name
        //ExtendReport.startScenario(scenario.getName());

      System.out.println("\n---------------------------------------------------------");
      System.out.println("Current Scenario running is : " + scenario.getName()+"\n");


        //ToDo: Initialize Config
        ConfigReader.PopulateSettings();

        //ToDo: Logging
        Settings.logs = new LogUtil();
        Settings.logs.CreateLogFile();
        Settings.logs.Write("Framework Initialize");


       /*  //todo: Create Test Cycle for Reporting
         Settings.ReportingConnection = DatabaseUtil.Open(Settings.ReportingConnectionString);
         ReportingUtil.CreateTestCycle(Settings.ReportingConnection);
         Settings.logs.Write("Test Cycle Created");*/

        //ToDo: Browser Initialized
        initializeBrowser(Settings.EnvironmentType, Settings.Os,Settings.BrowserType);
        Settings.logs.Write("Browser Initialized");

        //ToDo: Navigate to url
        //DriverContext.Browser.GoToUrl(Settings.AUT);
        DriverContext.GoToUrl(Settings.AUT);
        Settings.logs.Write("Navigated to URL " + Settings.AUT);

        //ToDo: Maximize url
        //DriverContext.Browser.Maximize();
//        DriverContext.Maximize();

        //ToDo: Implicitly Wait
        DriverContext.ImplicitlyWait();

        try {
            ExcelUtil util = new ExcelUtil(Settings.ExcelSheetPath);
        } catch (Exception e) {
        }
    }

    @After
    public void TearDownTest(Scenario scenario) {

        //ToDo: Logging
        Settings.logs = new LogUtil();

        if (scenario.isFailed()) {
            Settings.logs.Write(scenario.getName());
            Settings.logs.Write("ie. scenario Not KO : --> capture !");

            //ToDo: Take screenshot
            Settings.logs.Write("Take screenshot");
            try {
                //final byte[] screenshot = ((TakesScreenshot) Driver)
                final byte[] screenshot = ((TakesScreenshot) LocalDriverContext.getRemoteWebDriver())
                        .getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (scenario.getStatus().equals("pass")) {

            Settings.logs.Write(scenario.getName());
            Settings.logs.Write("ie. scenario KO : --> capture !");
        }


        //ToDo: Closing browser
        Settings.logs.Write("Closing browser");
        //if (Driver != null) {
        if (LocalDriverContext.getRemoteWebDriver() != null) {
            //Driver.close();
            LocalDriverContext.getRemoteWebDriver().close();
            LocalDriverContext.getRemoteWebDriver().quit();
        }
    }
}
