package com.crud.framework.controls.api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;

/**
 * Created by Ibi on 28/07/2018.
 */

public class ControlFactory extends PageFactory {

    public static <T> T initElements(WebDriver driver, Class<T> pageClassToProxy) {
        try {
            T page = pageClassToProxy.newInstance();
            PageFactory.initElements(
                    new ControlFieldDecorator(new DefaultElementLocatorFactory(driver)), page);
            return pageClassToProxy.cast(page);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
}
