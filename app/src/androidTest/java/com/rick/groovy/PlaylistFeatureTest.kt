package com.rick.groovy

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaRecyclerViewAssertions.assertRecyclerViewItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.internal.matcher.DrawableMatcher.Companion.withDrawable
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class PlaylistFeatureTest {

    val activityRule = ActivityTestRule(MainActivity::class.java)
        @Rule get

    @Test
    fun displayScreenTitle() {
        assertDisplayed("Playlists")
    }

    @Test
    fun displaysListOfPlaylists() {
        assertRecyclerViewItemCount(R.id.list, 10)

        // Testing rv is diffcult because of the way they work, so we have to
        // check if the item displayed in a certain position matches our requirement
        // so, reading the below line
        // on given view, allOf parameters
        // with playlist_name, in descendant order of hte view playlist_list, at position 0
        // gets specific view of specific list on specific position

        onView(
            allOf(
                withId(R.id.name),
                isDescendantOfA(nthChildOf(withId(R.id.list), 0))
            )
        ).check(matches(withText("Hard Rock Cafe")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.category),
                isDescendantOfA(nthChildOf(withId(R.id.list), 0))
            )
        ).check(matches(withText("Rock")))
            .check(matches(isDisplayed()))

        onView(
            allOf(
                withId(R.id.image),
                isDescendantOfA(nthChildOf(withId(R.id.list), 0))
            )
        ).check(matches(withDrawable(R.drawable.ic_launcher_foreground)))
            .check(matches(isDisplayed()))
    }

    fun nthChildOf(parentMatcher: Matcher<View>, childPosition: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("position $childPosition of parent")
                parentMatcher.describeTo(description)
            }

            override fun matchesSafely(view: View): Boolean {
                if (view.parent !is ViewGroup) return false
                val parent = view.parent as ViewGroup

                return (parentMatcher.matches(parent)
                        && parent.childCount > childPosition
                        && parent.getChildAt(childPosition) == view
                        )
            }
        }
    }
}