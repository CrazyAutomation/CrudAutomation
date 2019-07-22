package com.crud.framework.base;

import com.crud.framework.controls.api.ControlFactory;

/**
 * @Created by Ibi on 28/07/2018.
 * @Base
 * Base libraries are considered as the heart and soul of our framework,
 * since there exist all libraries required by our automation test to execute.
 * For instance, the object for Selenium Webdriver is created only here
 */
public class Base {

    /**
     * @A property of BasePage type of Base class
     */
    
    public static BasePage CurrentPage;


    /**
     * @Generics- Page navigation
     * create an instance of object of type page using the method GetInstance
     * @param page
     * @param <TPage>
     * @return
     */
    public <TPage extends BasePage> TPage GetInstance(Class<TPage> page){
        /**Single instance driver*/
        // Object obj = PageFactory.initElements(DriverContext.Driver,page);

        /**custom control page factory initialization, grid and parallel test*/
        Object obj = ControlFactory.initElements(LocalDriverContext.getRemoteWebDriver(), page);

        /**selenium grid ONLY*/
        //Object obj = PageFactory.initElements(LocalDriverContext.getRemoteWebDriver(),page);
        return page.cast(obj);
    }
}
