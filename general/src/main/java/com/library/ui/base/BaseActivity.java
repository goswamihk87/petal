package com.library.ui.base;

import android.app.Dialog;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.library.General;
import com.library.di.component.DaggerViewComponent;
import com.library.di.component.ViewComponent;
import com.library.di.module.ViewModule;
import com.library.ui.listener.ViewController;
import com.library.ui.widget.CustomProgressDialog;
import com.library.util.common.Consts;
import com.library.util.network.NetworkChangeReceiver;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by agile-01 on 6/8/2017.
 * <p>
 * base class for all activities.
 * It is recommended to have a project level another base activity that would extend this one.
 */

public abstract class BaseActivity extends AppCompatActivity implements ViewController {

    @Inject
    protected CompositeSubscription mCompositeSubscription;
    @Inject
    protected Bus mBus;
    @Inject
    protected NetworkChangeReceiver mNetworkChangeReceiver;
    @Inject
    protected InputMethodManager mInputMethodManager;

    private Dialog mProgressDialog;
    private long mLastClickTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mProgressDialog = new CustomProgressDialog(this);
        ViewComponent viewComponent = DaggerViewComponent
                .builder()
                .appComponent(General.getInstance().getAppComponent())
                .viewModule(new ViewModule())
                .build();
        viewComponent.inject(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mBus.register(this);
        IntentFilter connectivityChangeFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetworkChangeReceiver, connectivityChangeFilter);
    }

    @Override
    protected void onStop() {
        mBus.unregister(this);
        unregisterReceiver(mNetworkChangeReceiver);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        hideLoader();
        mCompositeSubscription.clear();
        super.onDestroy();
    }


    @Override
    public void showLoader() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoader() {
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * to show common alert dialogs and messages
     * change it with yur own dialogs/views/any preferred method for showing alerts
     */
    @Override
    public void showAlert(String msg) {
        if (msg == null) return;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * to show error messages only
     * change it with yur own dialogs/views/any preferred method for showing errors
     */
    @Override
    public void showError(String msg) {
        if (msg == null) return;
        Snackbar.make(findViewById(android.R.id.content), msg, BaseTransientBottomBar.LENGTH_SHORT).show();
    }

    public void hideKeyBoard(View view) {
        if (view != null) {
            mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * it will return true if consecutive click occurs within {@link Consts.Delays#MIN_TIME_BETWEEN_CLICKS}
     *
     * @return true indicating do not allow any click, false otherwise
     */
    public boolean isClickDisabled() {
        if ((SystemClock.elapsedRealtime() - mLastClickTime) < Consts.Delays.MIN_TIME_BETWEEN_CLICKS)
            return true;
        else {
            mLastClickTime = SystemClock.elapsedRealtime();
            return false;
        }
    }

    /**
     * to add fragment in container
     * tag will be same as class name of fragment
     *
     * @param containerId    id of fragment container
     * @param addToBackStack should be added to back stack?
     */
    public void addFragment(Fragment fragment, int containerId, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerId, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        fragmentTransaction.commit();
    }

    /**
     * to replace fragment in container
     * tag will be same as class name of fragment
     *
     * @param containerId        id of fragment container
     * @param isAddedToBackStack should be added to back stack?
     */
    public void replaceFragment(Fragment fragment, int containerId, boolean isAddedToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment);
        if (isAddedToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        fragmentTransaction.commit();
    }

}
