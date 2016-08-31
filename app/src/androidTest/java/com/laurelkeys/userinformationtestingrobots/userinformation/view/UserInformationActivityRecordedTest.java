package com.laurelkeys.userinformationtestingrobots.userinformation.view;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.laurelkeys.userinformationtestingrobots.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class UserInformationActivityRecordedTest {

    @Rule
    public ActivityTestRule<UserInformationActivity> mActivityTestRule = new ActivityTestRule<>(UserInformationActivity.class);

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    @Test
    public void userInformationActivityRecordedTest() {
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.first_name), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.first_name), isDisplayed()));
        appCompatEditText2.perform(replaceText("Espress"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.last_name), isDisplayed()));
        appCompatEditText3.perform(replaceText("Recording"), closeSoftKeyboard());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.email), isDisplayed()));
        appCompatEditText4.perform(replaceText("test@my.app"), closeSoftKeyboard());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.age), isDisplayed()));
        appCompatEditText5.perform(replaceText("10"), closeSoftKeyboard());

        ViewInteraction cardView = onView(
                allOf(withId(R.id.send_button), isDisplayed()));
        cardView.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("Success!"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.user_information_container),
                                        0),
                                1),
                        isDisplayed()));
        textView.check(matches(withText("Success!")));

    }
}
