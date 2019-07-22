package com.crud.framework.base;

import org.openqa.selenium.WebDriver;

/**
 * Created by Ibi on 25/07/2018.
 */
public class Browser extends Base{

    private WebDriver _driver;

    /**
     * @Constructor
     * @param driver
     */
    public Browser(WebDriver driver){

        _driver=driver;
    }

    public BrowserTypes Type;

    /**
     * @URL string
     * @param url
     */
    /**public void GoToUrl(String url){   //try

        driver.get(url);
    }*/

    /**
     * @Maximize page
     */
    /*public void Maximize() {

        //driver.manage().window().maximize();
        LocalDriverContext.getRemoteWebDriver().manage().window().maximize();
    }*/

    /**
     * @ImplicitlyWait
     */
    /*public  void ImplicitlyWait() {

        //driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        LocalDriverContext.getRemoteWebDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
    }*/
}


