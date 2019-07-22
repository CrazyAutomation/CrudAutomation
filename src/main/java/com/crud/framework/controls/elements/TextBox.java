package com.crud.framework.controls.elements;

import com.crud.framework.controls.api.ImplementedBy;
import com.crud.framework.controls.internals.Control;

/**
 * Created by Ibi on 28/07/2018.
 */
@ImplementedBy(TextBoxBase.class)
public interface TextBox extends Control {

    void EnterText(String text);
    String GetTextValue();
}




