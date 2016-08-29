package com.laurelkeys.userinformationtestingrobots.userinformation.robots

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.matcher.ViewMatchers.isRoot
import android.support.test.espresso.matcher.ViewMatchers.withText
import com.laurelkeys.userinformationtestingrobots.userinformation.extensions.visible
import org.hamcrest.Matchers.containsString

/**
 * Created by Tiago in July 2016.
 */
class ResultRobot {

    fun isSuccess(): ResultRobot {
        onView(withText(containsString("Success")))
                .check(visible())
        return this
    }

    fun isFailure(): ResultRobot {
        onView(withText(containsString("Failure")))
                .check(visible())
        return this
    }

    fun returnToUserInformation(): UserInformationRobot {
        onView(isRoot())
                .perform(click())
        return UserInformationRobot()
    }
}
