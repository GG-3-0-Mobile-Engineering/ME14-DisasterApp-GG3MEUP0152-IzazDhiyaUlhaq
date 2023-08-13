package id.izazdhiya.disasterapp

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import id.izazdhiya.disasterapp.ui.SettingsActivity
import org.junit.Test

class SettingsActivityInstrumentedTest {

    @Test
    fun testDarkModeToggle() {
        val scenario = ActivityScenario.launch(SettingsActivity::class.java)

        onView(withId(R.id.switchMode)).check(matches(isNotChecked()))

        onView(withId(R.id.switchMode)).perform(click())

        onView(withId(R.id.switchMode)).check(matches(isChecked()))

        onView(withId(R.id.deskripsiMode)).check(matches(withText(R.string.disable_dark_mode)))

        onView(withId(R.id.switchMode)).perform(click())

        onView(withId(R.id.switchMode)).check(matches(isNotChecked()))

        onView(withId(R.id.deskripsiMode)).check(matches(withText(R.string.enable_dark_mode)))

        scenario.close()
    }
}
