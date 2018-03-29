package com.library.di.component;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.library.di.module.ViewModule;
import com.library.di.scope.ViewScope;
import com.library.ui.base.BaseActivity;
import com.library.ui.base.BaseFragment;

import dagger.Component;

/**
 * Created by agile-01 on 6/30/2017.
 * <p>
 * This component injects all view scoped instances.
 * It injects all instances in {@link Activity#onCreate(Bundle)} and {@link Fragment#onViewCreated(View, Bundle)}.
 * So, all the instances will be removed when activity/fragment is destroyed and recreated when activity/fragment are recreated
 * It has dependencies on app component and so all instances created by app component can also be injected in view
 */

@ViewScope
@Component(dependencies = AppComponent.class, modules = ViewModule.class)
public interface ViewComponent {

    void inject(BaseActivity baseActivity);

    void inject(BaseFragment baseFragment);

}
