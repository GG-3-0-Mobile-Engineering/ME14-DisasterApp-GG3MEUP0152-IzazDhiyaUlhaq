package id.izazdhiya.disasterapp
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.PickerActions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import id.izazdhiya.disasterapp.ui.MainActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Matchers
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.Calendar

@RunWith(AndroidJUnit4::class)
class SearchFragmentInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testSearchByDisasterArea() {
        onView(withId(R.id.search_bar)).perform(click())

        onView(withId(R.id.et_search)).perform(click())
        onView(withId(R.id.et_search)).perform(typeText("Aceh"), closeSoftKeyboard())

        onView(allOf(withId(R.id.rv_disasterArea), hasDescendant(withText("Aceh"))))
            .check(matches(isDisplayed()))

        onView(allOf(withId(R.id.rv_disasterArea), hasDescendant(withText("Aceh"))))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.fragment_maps)).check(matches(isDisplayed()))
    }

    @Test
    fun testSearchByDate() {
        onView(withId(R.id.search_bar)).perform(click())

        onView(withId(R.id.etStartDate)).perform(click())
        val calendar = Calendar.getInstance()
        calendar.set(2023, 8, 1)
        onView(withClassName(Matchers.equalTo("android.widget.DatePicker")))
            .perform(PickerActions.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)))
        onView(withId(android.R.id.button1)).perform(click())

        onView(withId(R.id.etEndDate)).perform(click())
        val calendarEnd = Calendar.getInstance()
        calendarEnd.set(2023, 8, 10)
        onView(withClassName(Matchers.equalTo("android.widget.DatePicker")))
            .perform(PickerActions.setDate(calendarEnd.get(Calendar.YEAR), calendarEnd.get(Calendar.MONTH), calendarEnd.get(Calendar.DAY_OF_MONTH)))
        onView(withId(android.R.id.button1)).perform(click())

        onView(withId(R.id.btnSubmit)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.map)).check(matches(isDisplayed()))
    }
}
