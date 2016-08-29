package com.laurelkeys.userinformationtestingrobots.userinformation.tests

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.withHint
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.laurelkeys.userinformationtestingrobots.userinformation.extensions.visible
import com.laurelkeys.userinformationtestingrobots.userinformation.model.UserInformation
import com.laurelkeys.userinformationtestingrobots.userinformation.robots.ResultRobot
import com.laurelkeys.userinformationtestingrobots.userinformation.robots.UserInformationRobot
import com.laurelkeys.userinformationtestingrobots.userinformation.view.UserInformationActivity
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Tiago in July 2016.
 */
@RunWith(AndroidJUnit4::class)
class UserInformationActivityTest {

    @Rule @JvmField // @JvmField is needed to run Espresso tests when written in Kotlin. @get:Rule can be used as an alternative.
    val activityTestRule: ActivityTestRule<UserInformationActivity> = ActivityTestRule(UserInformationActivity::class.java)

    private val validUserInformation = UserInformation(
            firstName = "Tiago",
            lastName = "Chaves",
            email = "tiagoloureirochaves@gmail.com",
            age = "17")

    @Test
    fun isShowingSendButton() {
        onView(withText("Send"))
                .check(visible())
    }

    @Test
    fun typeFirstAndLastNameThenCloseKeyboard() {
        onView(withHint(containsString("First name")))
                .perform(typeText("Tiago"))
        onView(withHint(containsString("Last name")))
                .perform(typeText("Chaves"))
        onView(withHint(containsString("Last name")))
                .perform(closeSoftKeyboard())
    }

    @Test
    fun isValidLoginSuccessful() {
        UserInformationRobot().userInformation {
            firstName("Tiago")
            lastName("Chaves")
            email("tiagoloureirochaves@gmail.com")
            age("17")
        } send {
            isSuccess()
        }
    }

    @Test
    fun isReturningToUserInformationScreenAfterLoginSuccessful() {
        UserInformationRobot().userInformation {
            fillInformation(validUserInformation)
        } send {
            returnToUserInformation()
        }
        isShowingSendButton()
    }

    @Test
    fun isReturningToUserInformationScreenAfterLoginUnsuccessful() {
        UserInformationRobot().userInformation {
            clearFields()
        } send {
            returnToUserInformation()
        }
        isShowingSendButton()
    }

    @Test
    fun isFirstNameWithAccentsLoginSuccessful() {
        UserInformationRobot().userInformation {
            fillInformation(validUserInformation)
            firstName("João")
        } send {
            isSuccess()
        }
    }

    @Test
    fun isLastNameWithAccentsLoginSuccessful() {
        UserInformationRobot().userInformation {
            fillInformation(validUserInformation)
            lastName("Gonçalves")
        } send {
            isSuccess()
        }
    }

    @Test
    fun isFirstNameWithNumbersLoginUnsuccessful() {
        UserInformationRobot().userInformation {
            fillInformation(validUserInformation)
            firstName("T1460")
        } send {
            isFailure()
        }
    }

    @Test
    fun isLastNameWithNumbersLoginUnsuccessful() {
        UserInformationRobot().userInformation {
            fillInformation(validUserInformation)
            lastName("Ch4v35")
        } send {
            isFailure()
        }
    }

    @Test
    fun isEmptyFirstNameLoginUnsuccessful() {
        UserInformationRobot().userInformation {
            fillInformation(validUserInformation)
            firstName("")
        } send {
            isFailure()
        }
    }

    @Test
    fun isEmptyLastNameLoginUnsuccessful() {
        UserInformationRobot().userInformation {
            fillInformation(validUserInformation)
            lastName("")
        } send {
            isFailure()
        }
    }

    @Test
    fun isLettersOnAgeLoginUnsuccessful() {
        UserInformationRobot().userInformation {
            fillInformation(validUserInformation)
            age("Seventeen")
        } send {
            isFailure()
        }
    }

    @Test
    fun isEmptyAgeLoginUnsuccessful() {
        UserInformationRobot().userInformation {
            fillInformation(validUserInformation)
            age("")
        } send {
            isFailure()
        }
    }

    // @Test
    // this test takes quite a bit of time
    fun isAgeFrom0To99LoginSuccessful() {
        for (age in 0..99) {
            validLoginWithAge(age) {
                isSuccess()
                returnToUserInformation()
            }
            isShowingSendButton()
        }
    }

    fun validLoginWithAge(age: Int, func: ResultRobot.() -> Unit): ResultRobot {
        return (UserInformationRobot().userInformation {
                    fillInformation(validUserInformation)
                    age(age.toString())
                } send { }
                ).apply { func() }
    }
}
