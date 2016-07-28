package com.laurelkeys.userinformationtestingrobots.userinformation.tests;

import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.laurelkeys.userinformationtestingrobots.userinformation.robots.ResultRobot;
import com.laurelkeys.userinformationtestingrobots.userinformation.robots.UserInformationRobot;
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
 * Created by Tiago in July 2016.
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
    public void typeFirstAndLastNameThenCloseKeyboard() {
        onView(withHint("First name *")).perform(ViewActions.typeText("Tiago"));
        onView(withHint("Last name *")).perform(ViewActions.typeText("Chaves"));
        onView(withHint("Last name *")).perform(ViewActions.closeSoftKeyboard());
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
    public void isReturningToUserInformationScreenAfterLoginSuccessful() {
        new UserInformationRobot()
                .fillInformation(validUserInformation)
                .send()
                .returnToUserInformation();
        isShowingSendButton();
    }

    @Test
    public void isReturningToUserInformationScreenAfterLoginUnsuccessful() {
        new UserInformationRobot()
                .clearFields()
                .send()
                .returnToUserInformation();
        isShowingSendButton();
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
    public void isLettersOnAgeLoginUnsuccessful() {
        UserInformationRobot userInformation = new UserInformationRobot();
        ResultRobot result = userInformation
                .fillInformation(validUserInformation)
                .age("Seventeen")
                .send();

        result.isFailure();
    }

    @Test
    public void isEmptyAgeLoginUnsuccessful() {
        UserInformationRobot userInformation = new UserInformationRobot();
        ResultRobot result = userInformation
                .fillInformation(validUserInformation)
                .age("")
                .send();

        result.isFailure();
    }

    // @Test
    // this test takes quite a bit of time
    public void isAgeFrom0To99LoginSuccessful() {
        for (int age = 0; age < 100; ++age) {
            validLoginWithAge(age)
                    .isSuccess()
                    .returnToUserInformation();
            isShowingSendButton();
        }
    }

    public ResultRobot validLoginWithAge(int age) {
        UserInformationRobot userInformation = new UserInformationRobot();
        ResultRobot result = userInformation
                .fillInformation(validUserInformation)
                .age(String.valueOf(age))
                .send();
        return result;
    }
}
