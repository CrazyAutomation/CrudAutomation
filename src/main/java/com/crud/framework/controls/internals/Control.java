package com.crud.framework.controls.internals;

import com.crud.framework.controls.api.ImplementedBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;

/**
 * Created by Ibi on 28/07/2018.
 */

@ImplementedBy(ControlBase.class)
public interface Control extends WebElement, WrapsElement, Locatable {
    //todo: Custom Controls
//    ControlBase WaitForPage();

//    ControlBase WaitForVisible();

//    ControlBase Click();

//    ControlBase ScrollToElement();
}