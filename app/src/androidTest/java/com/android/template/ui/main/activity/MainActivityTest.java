package com.android.template.ui.main.activity;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.android.template.R;
import com.android.template.ui.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public final class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testTextNotEmpty() throws Exception {
        Espresso.onView(withId(R.id.activity_main_text_view))
                .check(matches(withText("bla")));

        Espresso.onView(withId(R.id.activity_main_edit_text))
                .check(matches(withText("bla")));

        Espresso.onView(withId(R.id.activity_main_edit_text))
                .perform(typeText("luka"));

        Espresso.onView(withId(R.id.activity_main_text_view))
                .perform(click());

        Espresso.onView(withId(R.id.activity_main_text_view))
                .check(matches(withText("blaluka")));

        Espresso.onView(withId(R.id.activity_main_edit_text))
                .check(matches(withText("blaluka")));
    }
}
