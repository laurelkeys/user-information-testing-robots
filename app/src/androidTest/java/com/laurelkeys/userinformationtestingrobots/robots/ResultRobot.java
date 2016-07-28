package com.laurelkeys.userinformationtestingrobots.robots;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Tiago on 27/07/2016.
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
}
