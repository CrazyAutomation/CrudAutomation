package com.crud.framework.controls.elements;

import com.crud.framework.controls.api.ImplementedBy;
import com.crud.framework.controls.internals.Control;

/**
 * Created by Ibi on 28/07/2018.
 */
@ImplementedBy(ButtonBase.class)
public interface Button extends Control{

    void PerformClick();
    String GetButtonTest();
    void PerformSubmit();


}
