package com.charleston.espressocustomassertion

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Before
    fun setup() {
        activityRule.launchActivity(null)
    }

    @Test
    fun checkItemsNoFilterIsVisible() {
        onView(withId(R.id.list))
            .check(recyclerViewItemCountAssertion(10))
    }

    @Test
    fun checkItemsFilteredIsVisible() {
        onView(withId(R.id.switch1)).perform(click())

        onView(withId(R.id.list))
            .check(recyclerViewItemCountAssertion(5))
    }

    @Test
    fun checkFieldAgeIsNotVisible() {
        onView(withId(R.id.button)).perform(click())

        repeat((1..10).count()) { index ->
            onView(withId(R.id.list))
                .check(
                    matches(
                        withViewAtPosition(
                            index,
                            hasDescendant(allOf(withId(R.id.textView2), not(isDisplayed())))
                        )
                    )
                )
        }
    }
}