package com.crud.framework.pages;

import com.crud.framework.base.BasePage;
import com.crud.framework.base.DriverContext;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class StudentModelPage extends BasePage {

   // Student Details Added Successfully


    @FindBy(how = How.LINK_TEXT, using = "Create New")
    private WebElement createNewLink;

 @FindBy(how = How.TAG_NAME, using = "h4")
    private WebElement studentmodelText;

    @FindBy(how = How.ID, using = "Name")
    private WebElement name;

    @FindBy(how = How.ID, using = "City")
    private WebElement city;

    @FindBy(how = How.ID, using = "Address")
    private WebElement address;

    @FindBy(how = How.ID, using = "Postcode")
    private WebElement postcode;

    @FindBy(how = How.XPATH, using = "//input[contains(@value,'Create')]")
    private WebElement createBtn;

    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-success']")
    private WebElement sucessfullMsg;

    public StudentModelPage clickCreateNewLink()
    {
        DriverContext.WaitForTextToBeVisible();
        DriverContext.isWebelementActive(createNewLink);
        createNewLink.click();
        return GetInstance(StudentModelPage.class);
    }

    public void validateHeaderText()
    {
        DriverContext.WaitForTextToBeVisible();
        String text = studentmodelText.getText();
        DriverContext.isWebelementActive(studentmodelText);
        System.out.println("\nPage header text is : "+text);
        Assert.assertTrue(text.equalsIgnoreCase("StudentModel"));
    }

    public StudentModelPage submitStudentData()
    {
        DriverContext.isWebelementActive(name);
        DriverContext.isWebelementActive(city);
        DriverContext.isWebelementActive(address);
        DriverContext.isWebelementActive(postcode);
        DriverContext.isWebelementActive(createBtn);
        DriverContext.enterText(name,"Venkaiah Pasyavula");
        DriverContext.enterText(city, "London");
        DriverContext.enterText(address, "Hounslow");
        DriverContext.enterText(postcode,"TW3 3TY");
        createBtn.click();
        return GetInstance(StudentModelPage.class);
    }


    public void validateSuccessText(String msg)
    {
        DriverContext.WaitForTextToBeVisible();
        DriverContext.isWebelementActive(sucessfullMsg);
        String text = sucessfullMsg.getText();
        System.out.println("\nSuccess message text is : "+text);
        Assert.assertTrue(text.equalsIgnoreCase(msg));
    }

}