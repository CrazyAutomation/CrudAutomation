package com.crud.test.steps;

import com.crud.framework.base.Base;
import com.crud.framework.base.DriverContext;
import com.crud.framework.base.LocalDriverContext;
import com.crud.framework.config.Settings;
import com.crud.framework.pages.CommonFunctions;
import com.crud.framework.pages.StudentApplicationHomePage;
import com.crud.framework.pages.StudentModelPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import static com.crud.framework.pages.CommonFunctions.STUDENTAPPLICATIONTITLE;

public class StudentSteps extends Base {

    String studentTitle = STUDENTAPPLICATIONTITLE;
    @cucumber.api.java.en.Given("^CRUD application$")
    public void crudApplication() {
//        CurrentPage = GetInstance(StudentApplicationHomePage.class);
        CurrentPage = GetInstance(CommonFunctions.class);
        CurrentPage.As(CommonFunctions.class).validateTitle(studentTitle);
//        DriverContext.WaitForTextToBeVisible();
//        CurrentPage.As(StudentApplicationHomePage.class);
        Settings.logs.Write("Student Application launched");
    }

    @When("^I validated Student Application title$")
    public void iValidatedStudentApplicationTitle() {
        CurrentPage = GetInstance(CommonFunctions.class);
        Assert.assertTrue(studentTitle.equalsIgnoreCase(LocalDriverContext.getRemoteWebDriver().getTitle()));
        System.out.println("\n"+LocalDriverContext.getRemoteWebDriver().getTitle());
        Settings.logs.Write("Student Application title validation logs");
    }

    @When("^I click on Create new link$")
    public void iClickOnCreateNewLink() {
        CurrentPage = GetInstance(StudentModelPage.class);
        CurrentPage.As(StudentModelPage.class).clickCreateNewLink();
        Settings.logs.Write("Student Application create new link");
    }

    @Then("^I should be on the \"([^\"]*)\" page$")
    public void iShouldBeOnThePage(String arg0) throws Throwable {
        CurrentPage.As(StudentModelPage.class).validateHeaderText();
        Settings.logs.Write("Student Application create new link");
    }

    @When("^I submit \"([^\"]*)\" page$")
    public void iSubmitPage(String arg0) throws Throwable {
        CurrentPage.As(StudentModelPage.class).submitStudentData();
        Settings.logs.Write("Student Application create new link");

    }

    @Then("^I should see \"([^\"]*)\" text on the page$")
    public void iShouldSeeTextOnThePage(String msg) throws Throwable {
        CurrentPage.As(StudentModelPage.class).validateSuccessText(msg);
        Settings.logs.Write("Student Application create new link");

    }
}
