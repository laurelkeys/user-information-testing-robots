package com.laurelkeys.userinformationtestingrobots.userinformation.extensions

import android.support.test.espresso.ViewAssertion
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers

/**
 * Created by Tiago in August 2016.
 */
fun visible(): ViewAssertion {
    // As of right now, Kotlin doesn't support static extension functions.
    // They work if declared inside the class' companion object, however, since ViewAssertions is a java class
    // it doesn't have one. Also, as it's final, it can't be extended to a Kotlin one to implement a companion object.
    return ViewAssertions.matches(ViewMatchers.isDisplayed())
}