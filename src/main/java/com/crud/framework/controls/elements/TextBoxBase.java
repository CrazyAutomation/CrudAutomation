package com.crud.framework.controls.elements;

import com.crud.framework.controls.internals.ControlBase;
import org.openqa.selenium.WebElement;

/**
 * Created by Ibi on 28/07/2018.
 */

public class TextBoxBase extends ControlBase implements TextBox {

    public TextBoxBase(WebElement element) {
        super(element);
    }



    @Override
    public void EnterText(String text) {

        getWrappedElement().sendKeys(text);
    }

    @Override
    public String GetTextValue() {

        return getWrappedElement().getText();
    }
}
