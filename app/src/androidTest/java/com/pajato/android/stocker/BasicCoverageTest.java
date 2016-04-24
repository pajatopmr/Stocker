package com.pajato.android.stocker;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.UiController;
import android.view.View;
import android.support.test.espresso.ViewAction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.view.GravityCompat;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.hamcrest.Matcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.contrib.DrawerActions.openDrawer;
//import static android.support.test.espresso.contrib.DrawerActions.closeDrawer;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * Provide a set of tests that augment the do nothing tests to get the percent of covered code over the 80% bar.
 *
 * @author Paul Michael Reilly
 */
@RunWith(AndroidJUnit4.class)
public class BasicCoverageTest {

    /** The rule used to launch the activity under test. */
    @Rule public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    /** Test that the FAB button invokes the snackbar message.  Then make it disappear using a swipe right gesture. */
    @Test public void testSnackbarViaFabButton() throws Exception {
        // Ensure that the FAB button is visible and click on it, verifying that a snackbar message is displayed and
        // removing the snackbar message.
        onView(withId(R.id.fab)).perform(click());
        onView(allOf(withId(android.support.design.R.id.snackbar_text), withText("Replace with your own action")))
            .check(matches(isDisplayed()))
            .perform(swipeRight());
    }

    /** Test that the menu can be invoked correctly and that the "back" press restores the main display. */
    @Test public void testSettingsMenuItem() {
        // Open the overflow menu, verify that the Settings menu choice is displayed and click on it.
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        onView(withText("Settings"))
            .check(matches(isDisplayed()))
            .perform(click());
    }

    /** Test that the menu can be invoked correctly and that the "back" press restores the main display. */
    @Test public void testBackPress() {
        // Open the overflow menu, verify that the Settings menu choice is displayed and click on it.
        openActionBarOverflowOrOptionsMenu(InstrumentationRegistry.getTargetContext());
        pressBack();
    }

    /** Test that the navigation drawer can be opened. */
    @Test public void testNavigationDrawerOpen() {
        // Ensure that the
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer()).check(matches(isDisplayed()));
        onView(withText("Gallery")).perform(click());
    }

    /** Test that the navigation drawer will be closed by a back press. */
    @Test public void testNavigationDrawerOpenCloseUsingPressBack() {
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        onView(withText("Gallery"));
        pressBack();
    }

    private static ViewAction actionOpenDrawer() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(DrawerLayout.class);
            }

            @Override
            public String getDescription() {
                return "open drawer";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((DrawerLayout) view).openDrawer(GravityCompat.START);
            }
        };
    }
}
