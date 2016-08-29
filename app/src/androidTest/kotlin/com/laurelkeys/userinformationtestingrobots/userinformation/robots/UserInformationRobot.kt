package com.laurelkeys.userinformationtestingrobots.userinformation.robots

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import com.laurelkeys.userinformationtestingrobots.R
import com.laurelkeys.userinformationtestingrobots.userinformation.extensions.visible
import com.laurelkeys.userinformationtestingrobots.userinformation.model.UserInformation
import org.hamcrest.Matchers.containsString

/**
 * Created by Tiago in July 2016.
 */
class UserInformationRobot {

    fun userInformation(func: UserInformationRobot.() -> Unit): UserInformationRobot {
        onView(withId(R.id.user_information_container))
                .check(visible())
        return UserInformationRobot().apply { func() }
    }

    fun fillInformation(userInformation: UserInformation): UserInformationRobot {
        return userInformation {
            firstName(userInformation.firstName)
            lastName(userInformation.lastName)
            email(userInformation.email)
            age(userInformation.age)
        }
    }

    fun clearFields(): UserInformationRobot {
        return userInformation {
            firstName("")
            lastName("")
            email("")
            age("")
        }
    }

    fun firstName(firstName: String): UserInformationRobot {
        onView(withHint(containsString("First name")))
                .perform(replaceText(firstName), closeSoftKeyboard())
        return this
    }

    fun lastName(lastName: String): UserInformationRobot {
        onView(withHint(containsString("Last name")))
                .perform(replaceText(lastName), closeSoftKeyboard())
        return this
    }

    fun email(email: String): UserInformationRobot {
        onView(withHint(containsString("Email")))
                .perform(replaceText(email), closeSoftKeyboard())
        return this
    }

    fun age(age: String): UserInformationRobot {
        onView(withHint(containsString("Age")))
                .perform(replaceText(age), closeSoftKeyboard())
        return this
    }

    infix fun send(func: ResultRobot.() -> Unit): ResultRobot {
        onView(withText(containsString("Send")))
                .perform(click())
        return ResultRobot().apply { func() }
    }
}
