package com.crud.framework.pages;

import com.crud.framework.base.BasePage;
import com.crud.framework.base.DriverContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class CommonFunctions extends BasePage {
    public static String STUDENTAPPLICATIONTITLE = "Student Application";

    @FindBy(how = How.CLASS_NAME, using = "navbar-brand")
    private WebElement ptitle;

    public CommonFunctions validateTitle(String title) {
        DriverContext.WaitForTextToBeVisible();
        String pTitle = ptitle.getText();
        System.out.println("\nStudent Application titile is : " + pTitle);
        Assert.assertEquals(pTitle, title);
        return GetInstance(CommonFunctions.class);
    }

}



