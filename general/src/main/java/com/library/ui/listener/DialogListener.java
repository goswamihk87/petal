package com.library.ui.listener;

/**
 * Created on 8/16/2017.
 * <p>
 * common dialog button listener
 * change methods according to your need
 */

public abstract class DialogListener {

    public abstract void onOkBtnClick();

    public void onCancelBtnClick() {
        //optional to override
    }
}
