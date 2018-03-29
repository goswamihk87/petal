package com.library.di.module;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;

import com.library.di.scope.ViewScope;

import dagger.Module;
import dagger.Provides;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by agile-01 on 6/30/2017.
 */
@Module
public class ViewModule {

    @ViewScope
    @Provides
    CompositeSubscription provideCompositeSubscription() {
        return new CompositeSubscription();
    }

    @ViewScope
    @Provides
    InputMethodManager provideInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

}
