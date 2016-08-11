package com.laurelkeys.userinformationtestingrobots.extensions

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by Tiago in August 2016.
 */
fun AppCompatActivity.attachFragmentToScreen(fragment: Fragment?, @IdRes container: Int, args: Bundle? = null) {
    if (fragment != null) {
        if (args != null) fragment.arguments = args
        supportFragmentManager.replaceAndCommitTransaction(container, fragment, (fragment.javaClass.simpleName))
    }
}