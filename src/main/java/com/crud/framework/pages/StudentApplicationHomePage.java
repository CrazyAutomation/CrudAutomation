package com.crud.framework.pages;

import com.crud.framework.base.BasePage;
import com.crud.framework.base.DriverContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class StudentApplicationHomePage extends BasePage {

    private String SENDYOURREPORTTEXT2 = "This adverse event report already has a follow-up associated with this worldwide case number. To create another follow-up on this report please go to the submitted reports section.";



    @FindBy(how = How.XPATH, using = "//div[@class='column-one-half']/span[1]")
    private WebElement getWWCaseID;

    @FindBy(how = How.TAG_NAME, using = "h1")
    private WebElement headerText1;

    @FindBy(how = How.TAG_NAME, using = "h2")
    private WebElement headerText2;

    @FindBy(how = How.CSS, using = ".form-label")
    private WebElement sendReportMessage;

    @FindBy(how = How.XPATH, using = "//div[@class='read-report']/div/div/div[2]/span/span[1]")
    private WebElement typeofReportGenerator;

    @FindBy(how = How.XPATH, using = "//div[@class='read-report']/div/div/div[2]/span/span[2]")
    private WebElement typeofReport;

    @FindBy(how = How.XPATH, using = "//a[contains(@href, 'submit')]")
    private WebElement submitBtn;






    public void getWorldWideCaseId() {
        DriverContext.WaitForTextToBeVisible();
        DriverContext.isWebelementActive(getWWCaseID);
        String caseId = getWWCaseID.getText();
        if (!caseId.equals("")) {
            System.out.println("\nCheckout page Case id is : " + caseId);
        }
    }

    public void validateHeaderText(String title) {
        DriverContext.WaitForTextToBeVisible();
        DriverContext.isWebelementActive(headerText1);
        String htext = headerText1.getText();
        System.out.println("Current Page title is : \'" + htext + "\'");
        Assert.assertEquals(htext, title);
    }
}
