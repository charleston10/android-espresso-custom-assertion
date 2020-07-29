package com.charleston.espressocustomassertion

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.matcher.BoundedMatcher
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat


fun withViewAtPosition(position: Int, itemMatcher: Matcher<View?>): Matcher<View?>? {

    return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
        override fun describeTo(description: Description?) {
            itemMatcher.describeTo(description)
        }

        override fun matchesSafely(recyclerView: RecyclerView): Boolean {
            val viewHolder =
                recyclerView.findViewHolderForAdapterPosition(position)
            return viewHolder != null && itemMatcher.matches(viewHolder.itemView)
        }
    }
}

fun recyclerViewItemCountAssertion(expectedCount: Int) : ViewAssertion {

    return ViewAssertion { view, noViewFoundException ->
        if (noViewFoundException != null) {
            throw noViewFoundException
        }

        val recyclerView = view as RecyclerView
        val adapter = recyclerView.adapter

        assertThat(adapter?.itemCount, `is`(expectedCount))
    }
}