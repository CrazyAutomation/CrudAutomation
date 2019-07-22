package com.crud.framework.base;


import com.crud.framework.config.Settings;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Created by Ibi on 25/07/2018.
 */
public class DriverContext {

    //public static WebDriver Driver;//try
    //public static Browser Browser;//try
    private static Select select;
    private static WebElement element;

    public static void GoToUrl(String url) {  //try to remove

        LocalDriverContext.getRemoteWebDriver().get(url);
    }

    public static void Maximize() {

        //driver.manage().window().maximize();
        LocalDriverContext.getRemoteWebDriver().manage().window().maximize();
    }

    /**
     * @ImplicitlyWait
     */
    public static void ImplicitlyWait() {

        //driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
       // LocalDriverContext.getRemoteWebDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        LocalDriverContext.getRemoteWebDriver().manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
    }

    //remove

    /**
     * @param //driver
     * @setDriver
     */
    //public static void setDriver(WebDriver driver) {//try

    //    Driver = driver;
    //}

    //todo: handling uquie element ONLY
    public static boolean IsElementPresent(WebElement Locator) {
        try {
            return Locator.getSize() != null;   //Locator.equals(1);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @param Locator
     * @return
     */
    public static WebElement GetElement(WebElement Locator) {
        if (IsElementPresent(Locator))
            return Locator;
        else
            throw new NoSuchElementException("Element Not Found : " + Locator.toString());
    }

    /**
     * @param locator
     */
    public static void CheckedCheckBox(WebElement locator) {
        element = GetElement(locator);
        element.click();
    }

    /**
     * @param locator
     * @return
     */
    public static boolean IsRadioBtnChecked(WebElement locator) {
        element = GetElement(locator);
        String flag = element.getAttribute("data-val");

        if (flag == null)
            return false;
        else
            return flag.equalsIgnoreCase("true") || flag.equalsIgnoreCase("checked");
    }


    public static boolean IsRadioBtnChecked2(WebElement locator) {
        element = GetElement(locator);
        String flag = element.getAttribute("aria-invalid");

        if (flag == null)
            return false;
        else
            return flag.equalsIgnoreCase("false") || flag.equalsIgnoreCase("checked");
    }


    public static boolean IsCheckedCheckBox(WebElement locator) {
        element = GetElement(locator);
        String flag = element.getAttribute("aria-expanded");

        if (flag == null)
            return false;
        else
            return flag.equalsIgnoreCase("true") || flag.equalsIgnoreCase("checked");
    }

    public static boolean IsSubmittedCheckByDefault(WebElement locator) {
        element = GetElement(locator);
        String flag = element.getAttribute("class");

        if (flag == null)
            return false;
        else
            return flag.equalsIgnoreCase("govuk-tabs__tab govuk-tabs__tab--selected") || flag.equalsIgnoreCase("checked") || flag.equalsIgnoreCase("selected");
    }

    /**
     * @WaitForPageToLoad
     */
    public static void WaitForPageToLoad() {

        //todo: replace "Driver" to "LocalDriverContext.getRemoteWebDriver()" and replace "WebDriverWait" to var
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), 120);
        //WebDriverWait wait = new WebDriverWait(Driver, 120);
        JavascriptExecutor jsExecutor = LocalDriverContext.getRemoteWebDriver();
        //JavascriptExecutor jsExecutor = (JavascriptExecutor) Driver;

        //ExpectedCondition<Boolean> jsLoad = webDriver -> ((JavascriptExecutor) Driver)
        ExpectedCondition<Boolean> jsLoad = webDriver -> LocalDriverContext.getRemoteWebDriver()
                .executeScript("return document.readyState").toString().equals("complete"); //ToDo: Tp check if page is fully loaded

        //Get JS Ready
        boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");

        if (!jsReady)
            wait.until(jsLoad);
        else
            Settings.logs.Write("Page is ready !");
    }

    public static void WaitForTextToBeVisible() {
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(., 'Student Application')]")));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Application')]")));
        Settings.logs.Write("Wait for Header text to be visible");
    }



    /**
     * @param elementFindBy
     */
    public static void WaitForElementVisible(final WebElement elementFindBy) {

        //WebDriverWait wait = new WebDriverWait(Driver, 120);
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), 120);
        wait.until(ExpectedConditions.visibilityOf(elementFindBy));
    }

    /**
     * @param elementFindBy
     * @param text
     */
    public static void WaitForElementTextVisible(final WebElement elementFindBy, String text) {

        //WebDriverWait wait = new WebDriverWait(Driver, 120);
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), 120);
        wait.until(ExpectedConditions.textToBePresentInElement(elementFindBy, text));
    }

    /**
     * @param element
     * @param text
     */
    public static void WaitUntilTextDisplayed(final By element, String text) {

        //WebDriverWait wait = new WebDriverWait(Driver, 120);
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), 120);
        wait.until(textDisplayed(element, text));
    }

    /**
     *
     */
    private static ExpectedCondition<Boolean> textDisplayed(final By elementFindBy, final String text) {

        return webDriver -> webDriver.findElement(elementFindBy).getText().contains(text);
    }

    /**
     * @param elementFindBy
     */
    public static void WaitElementEnabled(final By elementFindBy) {

        //WebDriverWait wait = new WebDriverWait(Driver, 120);
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), 120);
        wait.until(webDriver -> webDriver.findElement(elementFindBy).isEnabled());
    }

    /**
     * @param locator
     */
    public static void WaitForElementToBeSelected(WebElement locator) {
        //Wait<WebDriver> wait = new FluentWait<WebDriver>(Driver)
        Wait<WebDriver> wait = new FluentWait<WebDriver>(LocalDriverContext.getRemoteWebDriver())
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
        try {
            wait.until(ExpectedConditions.elementToBeSelected(locator));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Expected element is not selected: " + locator);
        }
    }

    /**
     * @param table
     * @return
     */
    public static boolean CheckSectionsExist(List<String> table) {
        //String source = Driver.getPageSource();
        String source = LocalDriverContext.getRemoteWebDriver().getPageSource();
        for (String section : table) {
            if (!source.equals(section))
                return false;
        }
        return true;
    }

    public static boolean CheckSectionsExist2(List<String> table) {
        //String source = Driver.getPageSource();
        String source = LocalDriverContext.getRemoteWebDriver().getPageSource();
        for (String section : table) {
            if (!source.contains(section))
                return false;
        }
        return true;
    }

    public static boolean checkLinksAndURLs(Map<String, String> data) {

        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (!LocalDriverContext.getRemoteWebDriver().findElement(By.linkText(entry.getKey())).getAttribute("href").contains(entry.getValue()))
                return false;
        }
        return true;
    }

    /**
     * Wait for element to be clickable
     *
     * @param locator
     */
    public static void WaitForElementToBeClickable(WebElement locator) {
        // WebDriverWait wait = new WebDriverWait(Driver, 120);
        WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), 60);
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        WaitForPageToLoad();
        Settings.logs.Write("Wait for element to be clickable");
    }

    /**
     * @param locator
     * @param index
     */
    public static void SelectElement(WebElement locator, int index) {
        select = new Select(locator);
        select.selectByIndex(index);
        Settings.logs.Write("select index " + index + " from dropdown");
    }

    /**
     * @param locator
     * @param visibletext
     */
    public static void SelectElement(WebElement locator, String visibletext) {
        select = new Select(locator);
        select.selectByVisibleText(visibletext);
        Settings.logs.Write("select " + visibletext + " from dropdown");
    }

    public static String getSelectElementText(WebElement locator) {
       Select selectDropdown = new Select(locator);
       String selectedOption = selectDropdown.getFirstSelectedOption().getAttribute("value");
       Settings.logs.Write("selected option " + selectedOption + " from dropdown");
       return selectedOption;
    }

    public static void SaveAndContinueBtn() {
        WaitElementEnabled(By.className("button")); ////a[contains(text(),'Schools (')]
        //Driver.findElement(By.className("button")).click();
        LocalDriverContext.getRemoteWebDriver().findElement(By.className("button")).click();
        Settings.logs.Write("Clicking Save And Continue Button");
    }

    public static String getRandomnumber(int n) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());
            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }
        return sb.toString();
    }

    public static void BackBtn() {

        WaitElementEnabled(By.partialLinkText("Back"));
        //Driver.findElement(By.partialLinkText("Back")).click();
        LocalDriverContext.getRemoteWebDriver().findElement(By.partialLinkText("Back")).click();
        Settings.logs.Write("Clicking Back Button");
    }



    /**
     *
     */
    public static void ContinueBtn() {

//        WaitElementEnabled(By.cssSelector("body > div.site-wrapper > div.content > div > div > form > div > div:nth-child(2) > input"));
        WaitElementEnabled(By.xpath("//input[@value='Continue']"));
        //Driver.findElement(By.cssSelector("body > div.site-wrapper > div.content > div > div > form > div > div:nth-child(2) > input")).click();
//        LocalDriverContext.getRemoteWebDriver().findElement(By.cssSelector("body > div.site-wrapper > div.content > div > div > form > div > div:nth-child(2) > input")).click();
        LocalDriverContext.getRemoteWebDriver().findElement(By.xpath("//input[@value='Continue']")).click();
        Settings.logs.Write("Clicking Continue Button");
    }

    public static void AcceptingAlert() {

        // Switching to Alert
        Alert alert = LocalDriverContext.getRemoteWebDriver().switchTo().alert();
        // Accepting alert
        alert.accept();
    }

    public static void DismissingAlert() {

        // Switching to Alert
        Alert alert = LocalDriverContext.getRemoteWebDriver().switchTo().alert();
        // dismissing alert
        alert.dismiss();
    }

    public static void SwitchingToAlert() {

        // Switching to Alert
        Alert alert = LocalDriverContext.getRemoteWebDriver().switchTo().alert();

        System.out.println(alert.getText());
        //alert.getText().contains("");
    }


    public static void enterText(WebElement webElement, String string) {
        try {
            WebDriverWait wait = new WebDriverWait(LocalDriverContext.getRemoteWebDriver(), 15);
            wait.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(string);
        } catch (Exception t) {
            System.out.println("unable to enterText on the element using enterText(WebElement webElement,String string) : " + webElement);
            throw t;
        }
    }

    private static boolean AttributeExists(WebElement element, String attribute){
        String value = element.getAttribute(attribute);
        return value != null && !value.isEmpty();
    }


    //Method to get attribute of a web element
    public static String getWebElementAttribute(WebElement element) {
        String attributeValue;
        String[] attributes = new String[] { "id", "name", "class", "title", "src", "alt", "href", "h4" };
        for (String attribute : attributes) {
            if (AttributeExists(element, attribute)) {
                attributeValue = element.getAttribute(attribute);
                return attributeValue;
            }
        }
        return "No Attributes available";
    }

    // Method to check for a WebElement Active on  Web page
    public static boolean isWebelementActive(WebElement element) {
        boolean status;
        if (element.isEnabled()) {
            status = true;
            System.out.println("\'" + getWebElementAttribute(element) + "\' is Active on the Web page and the status is: " + status);
        } else {
            status = false;
            System.out.println("\'" + getWebElementAttribute(element) + "\' isn't Active on the Web page and the status is: " + status);
        }
        return status;
    }



}

