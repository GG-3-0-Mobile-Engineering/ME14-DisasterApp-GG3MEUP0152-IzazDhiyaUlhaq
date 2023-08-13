package id.izazdhiya.disasterapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import id.izazdhiya.disasterapp.ui.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MapsFragmentInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testSettingsButtonNavigation() {
        onView(withId(R.id.button_setting)).perform(click())
        onView(withId(R.id.layout_settings)).check(matches(isDisplayed()))
    }

    @Test
    fun testSearchBarNavigation() {
        onView(withId(R.id.search_bar)).perform(click())
        onView(withId(R.id.fragment_search)).check(matches(isDisplayed()))
    }
}

