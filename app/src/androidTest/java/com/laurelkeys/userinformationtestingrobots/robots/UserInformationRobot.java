package com.laurelkeys.userinformationtestingrobots.robots;

import com.laurelkeys.userinformationtestingrobots.userinformation.model.UserInformation;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Tiago on 27/07/2016.
 */
public class UserInformationRobot {

    public UserInformationRobot fillInformation(UserInformation userInformation) {
        firstName(userInformation.firstName);
        lastName(userInformation.lastName);
        if (userInformation.phoneNumber != null) phoneNumber(userInformation.phoneNumber);
        email(userInformation.email);
        age(userInformation.age);
        return this;
    }

    public UserInformationRobot firstName(String firstName) {
        onView(withHint(containsString("First name")))
                .perform(clearText(), typeText(firstName), closeSoftKeyboard());
        return this;
    }

    public UserInformationRobot lastName(String lastName) {
        onView(withHint(containsString("Last name")))
                .perform(clearText(), typeText(lastName), closeSoftKeyboard());
        return this;
    }

    public UserInformationRobot phoneNumber(String phoneNumber) {
        onView(withHint(containsString("Phone number")))
                .perform(clearText(), typeText(phoneNumber), closeSoftKeyboard());
        return this;
    }

    public UserInformationRobot email(String email) {
        onView(withHint(containsString("Email")))
                .perform(clearText(), typeText(email), closeSoftKeyboard());
        return this;
    }

    public UserInformationRobot age(String age) {
        onView(withHint(containsString("Age")))
                .perform(clearText(), typeText(String.valueOf(age)), closeSoftKeyboard());
        return this;
    }

    public ResultRobot send() {
        onView(withText(containsString("Send")))
                .perform(click());
        return new ResultRobot();
    }
}
