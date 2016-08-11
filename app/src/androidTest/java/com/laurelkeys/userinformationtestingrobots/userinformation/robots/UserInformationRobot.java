package com.laurelkeys.userinformationtestingrobots.userinformation.robots;

import com.laurelkeys.userinformationtestingrobots.userinformation.model.UserInformation;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Tiago in July 2016.
 */
public class UserInformationRobot {

    public UserInformationRobot fillInformation(UserInformation userInformation) {
        firstName(userInformation.getFirstName());
        lastName(userInformation.getLastName());
        if (userInformation.getPhoneNumber() != null) phoneNumber(userInformation.getPhoneNumber());
        email(userInformation.getEmail());
        age(userInformation.getAge());
        return this;
    }

    public UserInformationRobot clearFields() {
        firstName("");
        lastName("");
        phoneNumber("");
        email("");
        age("");
        return this;
    }

    public UserInformationRobot firstName(String firstName) {
        onView(withHint(containsString("First name")))
                .perform(replaceText(firstName), closeSoftKeyboard());
        return this;
    }

    public UserInformationRobot lastName(String lastName) {
        onView(withHint(containsString("Last name")))
                .perform(replaceText(lastName), closeSoftKeyboard());
        return this;
    }

    public UserInformationRobot phoneNumber(String phoneNumber) {
        onView(withHint(containsString("Phone number")))
                .perform(replaceText(phoneNumber), closeSoftKeyboard());
        return this;
    }

    public UserInformationRobot email(String email) {
        onView(withHint(containsString("Email")))
                .perform(replaceText(email), closeSoftKeyboard());
        return this;
    }

    public UserInformationRobot age(String age) {
        onView(withHint(containsString("Age")))
                .perform(replaceText(age), closeSoftKeyboard());
        return this;
    }

    public ResultRobot send() {
        onView(withText(containsString("Send")))
                .perform(click());
        return new ResultRobot();
    }
}
