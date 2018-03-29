package com.library.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by agile-01 on 6/30/2017.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ViewScope {
}