package com.crud.framework.base;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * Created by Ibi on 25/07/2018.
 */
public abstract class BasePage extends Base {

    /**
     * @Factory.initElements Using the page factory method to call all the elements in the page
     */
    protected static String worldwideCaseNumber;

    @FindBy(how = How.CSS, using = ".column-full > h1")
    public WebElement txtTitlePage_H1;

    @FindBy(how = How.CSS, using = ".phase-banner > p")
    public WebElement phaseBanner;

    @FindBy(how = How.LINK_TEXT, using = "help us improve our service")
    public WebElement lnkHelpUsImprove;

    @FindBy(how = How.LINK_TEXT, using = "Manage account")
    public WebElement lnkManageAccount;

    @FindBy(how = How.LINK_TEXT, using = "Sign Out")
    public WebElement lnkSignOut;

    public <TPage extends BasePage> TPage As(Class<TPage> pageInstance) {

        try {
            return (TPage) this;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    protected String getRandomString(){

        int length = 10;
        boolean useLetters = true;
        boolean useNumbers = false;
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }


    // verify common header controls
    public boolean ValidateBetaPhaseBanner(String value){

        return phaseBanner.getText().contains(value);
    }

    public boolean islinkHelpUsImproveExists(){

        return lnkHelpUsImprove.isDisplayed() && lnkHelpUsImprove.isEnabled();
    }

    public boolean islinkManageAccountExists(){

        return lnkManageAccount.isDisplayed() && lnkManageAccount.isEnabled();
    }
    public boolean islinkSignOutExists(){

        return lnkSignOut.isDisplayed() && lnkSignOut.isEnabled();
    }

    public void clickHelpUsImprove(){

        DriverContext.WaitForElementToBeClickable(lnkHelpUsImprove);
    }

    public void clickManageAccount(){

        DriverContext.WaitForElementToBeClickable(lnkManageAccount);
    }

    public void clickSignOut(){

        DriverContext.WaitForElementToBeClickable(lnkSignOut);
    }

    public static String getTitle(){

        String title = LocalDriverContext.getRemoteWebDriver().getTitle();
        return title;
    }

    //todo: Footer MandatoryChecks
    public enum ElementStatus{
        VISIBLE,
        NOTVISIBLE,
        ENABLED,
        NOTENABLED,
        PRESENT,
        NOTPRESENT
    }

    public static ElementStatus isElementVisible(By by, ElementStatus getStatus){
        try{
            if(getStatus.equals(ElementStatus.ENABLED)){
                if(LocalDriverContext.getRemoteWebDriver().findElement(by).isEnabled())
                    return ElementStatus.ENABLED;
                return ElementStatus.NOTENABLED;
            }
            if(getStatus.equals(ElementStatus.VISIBLE)){
                if(LocalDriverContext.getRemoteWebDriver().findElement(by).isDisplayed())
                    return ElementStatus.VISIBLE;
                return ElementStatus.NOTVISIBLE;
            }
            return ElementStatus.PRESENT;
        }catch(org.openqa.selenium.NoSuchElementException nse){
            return ElementStatus.NOTPRESENT;
        }
    }

    public void FooterMandatoryChecks(){

        isElementVisible(By.linkText("Veterinary Medicines Directorate"), ElementStatus.PRESENT);
        isElementVisible(By.linkText("Feedback"), ElementStatus.PRESENT);
        isElementVisible(By.linkText("Contact Support"), ElementStatus.PRESENT);
        isElementVisible(By.linkText("Privacy Notice"), ElementStatus.PRESENT);
        isElementVisible(By.linkText("Terms and Conditions"), ElementStatus.PRESENT);
        isElementVisible(By.linkText("Cookies"), ElementStatus.PRESENT);
    }

    public void HeadersMandatoryChecks(){

        isElementVisible(By.linkText("Veterinary Medicines Directorate"), ElementStatus.PRESENT);
        isElementVisible(By.linkText("Sign Out"), ElementStatus.PRESENT);
        isElementVisible(By.linkText("Manage account"), ElementStatus.PRESENT);
        isElementVisible(By.linkText("help us improve our service"), ElementStatus.PRESENT);
        isElementVisible(By.partialLinkText("GOV.UK"), ElementStatus.PRESENT);
    }

    protected void verifyDate(WebElement day, WebElement month, WebElement year, String expectedDay, String expectedMonth, String expectedYear){

            Assert.assertTrue(day.getAttribute("value").equals(expectedDay));
            Assert.assertTrue(month.getAttribute("value").equals(expectedMonth));
            Assert.assertTrue(year.getAttribute("value").equals(expectedYear));

    }
}
