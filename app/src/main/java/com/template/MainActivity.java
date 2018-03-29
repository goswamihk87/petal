package com.template;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created on 8/8/2017.
 * launcher activity. change the name and arrange it in some package to get started.
 */

public class MainActivity extends BaseAppActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
