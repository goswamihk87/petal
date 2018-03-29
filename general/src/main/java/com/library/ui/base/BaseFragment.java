package com.library.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.library.General;
import com.library.di.component.DaggerViewComponent;
import com.library.di.component.ViewComponent;
import com.library.di.module.ViewModule;
import com.library.ui.listener.ViewController;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by agile-01 on 6/8/2017.
 * <p>
 * base class for all fragments
 * It is recommended to have a project level another base fragment that would extend this one.
 */

public abstract class BaseFragment extends Fragment implements ViewController {

    @Inject
    protected CompositeSubscription mCompositeSubscription;
    @Inject
    protected Bus mBus;

    private ViewComponent mViewComponent;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewComponent = DaggerViewComponent
                .builder()
                .appComponent(General.getInstance().getAppComponent())
                .viewModule(new ViewModule())
                .build();
        mViewComponent.inject(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mBus.register(this);
    }

    @Override
    public void onStop() {
        mBus.unregister(this);
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        hideLoader();
        mCompositeSubscription.clear();
        super.onDestroyView();
    }


    @Override
    public void showLoader() {
        ((BaseActivity) getActivity()).showLoader();
    }

    @Override
    public void hideLoader() {
        ((BaseActivity) getActivity()).hideLoader();
    }

    @Override
    public void showAlert(String msg) {
        ((BaseActivity) getActivity()).showAlert(msg);
    }

    @Override
    public void showError(String msg) {
        ((BaseActivity) getActivity()).showError(msg);
    }

    protected ViewComponent getViewComponent() {
        return mViewComponent;
    }

    public void hideKeyBoard(View view) {
        ((BaseActivity) getActivity()).hideKeyBoard(view);
    }

    public boolean isClickDisabled() {
        return ((BaseActivity) getActivity()).isClickDisabled();
    }

    /**
     * this method calls {@link BaseActivity#addFragment(Fragment, int, boolean)}.
     * So, it will add fragment in Activity's container
     */
    public void addFragment(Fragment fragment, int containerId, boolean addToBackStack) {
        ((BaseActivity) getActivity()).addFragment(fragment, containerId, addToBackStack);
    }

    /**
     * this method calls {@link BaseActivity#replaceFragment(Fragment, int, boolean)}.
     * So, it will replace fragment in Activity's container
     */
    public void replaceFragment(Fragment fragment, int containerId, boolean addToBackStack) {
        ((BaseActivity) getActivity()).replaceFragment(fragment, containerId, addToBackStack);
    }

    /**
     * this method uses {@link #getChildFragmentManager()} and adds nested fragment inside Fragment's container.
     * using it with activity's container will throw {@link IllegalStateException} or may cause other errors.
     */
    protected void addChildFragment(Fragment fragment, int containerId, boolean addToBackStack) {
        FragmentManager fragmentManager = this.getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerId, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        fragmentTransaction.commit();
    }

    /**
     * this method uses {@link #getChildFragmentManager()} and replaces nested fragment inside Fragment's container
     * using it with activity's container will throw {@link IllegalStateException} or may cause other errors.
     */
    protected void replaceChildFragment(Fragment fragment, int containerId, boolean addToBackStack) {
        FragmentManager fragmentManager = this.getChildFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        fragmentTransaction.commit();
    }

}
