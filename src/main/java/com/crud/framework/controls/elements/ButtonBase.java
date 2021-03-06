package com.crud.framework.controls.elements;

import com.crud.framework.controls.internals.ControlBase;
import org.openqa.selenium.WebElement;

/**
 * Created by Ibi on 28/07/2018.
 */

public class ButtonBase extends ControlBase implements Button{


    public ButtonBase(WebElement element) {
        super(element);
    }



    @Override
    public void PerformClick() {

        getWrappedElement().click();
    }

    @Override
    public String GetButtonTest() {

        return getWrappedElement().getText();
    }

    @Override
    public void PerformSubmit() {

        getWrappedElement().submit();
    }
}
