package com.laurelkeys.userinformationtestingrobots.tests;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.laurelkeys.userinformationtestingrobots.robots.ResultRobot;
import com.laurelkeys.userinformationtestingrobots.robots.UserInformationRobot;
import com.laurelkeys.userinformationtestingrobots.userinformation.model.UserInformation;
import com.laurelkeys.userinformationtestingrobots.userinformation.view.UserInformationActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Tiago on 27/07/2016.
 */
@RunWith(AndroidJUnit4.class)
public class UserInformationActivityTest {

    private static final UserInformation validUserInformation = new UserInformation("Tiago", "Chaves", null, "tiagoloureirochaves@gmail.com", "17");

    @Rule
    public final ActivityTestRule<UserInformationActivity> activityTestRule = new ActivityTestRule<>(UserInformationActivity.class);

    @Test
    public void isShowingSendButton() {
        onView(withText("Send")).check(ViewAssertions.matches(isDisplayed()));
    }

    @Test
    public void isValidLoginSuccessful() {
        UserInformationRobot userInformation = new UserInformationRobot();
        ResultRobot result = userInformation
                .firstName("Tiago")
                .lastName("Chaves")
                .email("tiagoloureirochaves@gmail.com")
                .age("17")
                .send();

        result.isSuccess();
    }

    @Test
    public void isFirstNameWithAccentsLoginSuccessful() {
        UserInformationRobot userInformation = new UserInformationRobot();
        ResultRobot result = userInformation
                .fillInformation(validUserInformation)
                .firstName("João")
                .send();

        result.isSuccess();
    }

    @Test
    public void isLastNameWithAccentsLoginSuccessful() {
        UserInformationRobot userInformation = new UserInformationRobot();
        ResultRobot result = userInformation
                .fillInformation(validUserInformation)
                .lastName("Gonçalves")
                .send();

        result.isSuccess();
    }

    @Test
    public void isFirstNameWithNumbersLoginUnsuccessful() {
        UserInformationRobot userInformation = new UserInformationRobot();
        ResultRobot result = userInformation
                .fillInformation(validUserInformation)
                .firstName("T1460")
                .send();

        result.isFailure();
    }

    @Test
    public void isLastNameWithNumbersLoginUnsuccessful() {
        UserInformationRobot userInformation = new UserInformationRobot();
        ResultRobot result = userInformation
                .fillInformation(validUserInformation)
                .lastName("Ch4v35")
                .send();

        result.isFailure();
    }

    @Test
    public void isEmptyFirstNameLoginUnsuccessful() {
        UserInformationRobot userInformation = new UserInformationRobot();
        ResultRobot result = userInformation
                .fillInformation(validUserInformation)
                .firstName("")
                .send();

        result.isFailure();
    }

    @Test
    public void isEmptyLastNameLoginUnsuccessful() {
        UserInformationRobot userInformation = new UserInformationRobot();
        ResultRobot result = userInformation
                .fillInformation(validUserInformation)
                .lastName("")
                .send();

        result.isFailure();
    }

    @Test
    public void typeFirstAndLastNameThenCloseKeyboard() {
        onView(withHint("First name *")).perform(ViewActions.typeText("Tiago"));
        onView(withHint("Last name *")).perform(ViewActions.typeText("Chaves"));
        onView(withHint("Last name *")).perform(ViewActions.closeSoftKeyboard());
    }
}
