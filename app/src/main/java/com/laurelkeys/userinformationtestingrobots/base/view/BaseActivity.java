package com.laurelkeys.userinformationtestingrobots.base.view;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Tiago in July 2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static final String TAG = BaseActivity.class.getSimpleName();

    protected void attachFragmentToScreen(@Nullable final Fragment fragment, @IdRes int container) {
        attachFragmentToScreen(fragment, container, null);
    }

    protected void attachFragmentToScreen(@Nullable final Fragment fragment, @IdRes int container, @Nullable Bundle args) {
        if (fragment != null) {
            if (args != null) fragment.setArguments(args);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(container, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}