package com.laurelkeys.userinformationtestingrobots.extensions

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

/**
 * Created by Tiago in August 2016.
 */
fun FragmentManager.replaceAndCommitTransaction(@IdRes containerViewId: Int, fragment: Fragment?, tag: String? = null): Int {
    return this.beginTransaction()
            .replace(containerViewId, fragment, tag)
            .commit()
}