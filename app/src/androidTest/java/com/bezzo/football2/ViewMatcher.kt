package com.bezzo.football2

import android.support.test.espresso.matcher.ViewMatchers.isChecked
import android.support.design.internal.BottomNavigationItemView
import android.support.test.espresso.matcher.BoundedMatcher
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import android.support.test.espresso.UiController
import android.support.test.orchestrator.junit.BundleJUnitUtils.getDescription
import android.support.test.espresso.ViewAction




object ViewMatcher {

    fun withBottomNavItemCheckedStatus(isChecked: Boolean): Matcher<View> {
        return object : BoundedMatcher<View, BottomNavigationItemView>(BottomNavigationItemView::class.java) {
            internal var triedMatching: Boolean = false

            override fun describeTo(description: Description) {
                if (triedMatching) {
                    description.appendText("with BottomNavigationItem check status: " + isChecked.toString())
                    description.appendText("But was: " + (!isChecked).toString())
                }
            }

            override fun matchesSafely(item: BottomNavigationItemView): Boolean {
                triedMatching = true
                return item.itemData.isChecked == isChecked
            }
        }
    }

    fun viewWithIndex(matcher: Matcher<View>, index: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            internal var currentIndex = 0

            override fun describeTo(description: Description) {
                description.appendText("with index: ")
                description.appendValue(index)
                matcher.describeTo(description)
            }

            override fun matchesSafely(view: View): Boolean {
                return matcher.matches(view) && currentIndex++ == index
            }
        }
    }

    fun withCustomConstraints(action: ViewAction, constraints: Matcher<View>): ViewAction {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return constraints
            }

            override fun getDescription(): String {
                return action.description
            }

            override fun perform(uiController: UiController, view: View) {
                action.perform(uiController, view)
            }
        }
    }
}