package com.crud.framework.controls.elements;


import com.crud.framework.controls.api.ImplementedBy;
import com.crud.framework.controls.internals.Control;

/**
 * Created by Ibi on 28/07/2018.
 */

@ImplementedBy(HyperLinkBase.class)
public interface HyperLink extends Control {


    void ClickLink();
    String GetUrlText();
    boolean CheckUrlTextContains(String containsText);


}
