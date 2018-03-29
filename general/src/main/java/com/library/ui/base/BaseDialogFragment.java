package com.library.ui.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Window;

import com.library.ui.listener.DialogListener;

/**
 * Created on 8/16/2017.
 */

public abstract class BaseDialogFragment extends DialogFragment {

    @Nullable
    private DialogListener mDialogListener;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        if (window != null) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
        }
        return dialog;
    }

    @Override
    public void onDestroyView() {
        mDialogListener = null;
        super.onDestroyView();
    }

    @Nullable
    public DialogListener getDialogListener() {
        return mDialogListener;
    }

    public void setDialogListener(@Nullable DialogListener dialogListener) {
        mDialogListener = dialogListener;
    }

}
