package com.laurelkeys.userinformationtestingrobots.userinformation.robots;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Tiago in July 2016.
 */
public class ResultRobot {

    public ResultRobot isSuccess() {
        onView(withText(containsString("Success")))
                .check(matches(isDisplayed()));
        return this;
    }

    public ResultRobot isFailure() {
        onView(withText(containsString("Failure")))
                .check(matches(isDisplayed()));
        return this;
    }

    public UserInformationRobot returnToUserInformation() {
        onView(isRoot()).perform(click());
        return new UserInformationRobot();
    }
}
