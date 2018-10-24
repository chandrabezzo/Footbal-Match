package com.bezzo.football2

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.bezzo.football2.features.main.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testMainBehaviour(){

        Thread.sleep(2000)

        // check bottom navigation telah muncul
        onView(withId(R.id.bnv_main)).check(matches(isDisplayed()))

        // check navigation match selected
        onView(withId(R.id.nav_match)).check(matches(ViewMatcher
                .withBottomNavItemCheckedStatus(true)))

        // check item previous event telah muncul
        Thread.sleep(2000)
        onView(withId(R.id.rv_next_event)).check(matches(isDisplayed()))

        // scroll previous event hingga ke item 8 (newcastle vs brighton)
        onView(withId(R.id.rv_next_event))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))

        // click item previous event index ke 4 (Chelsea vs Man Utd )
        onView(withId(R.id.rv_next_event)).perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(4, click()))

        // Check Options tombol favorite telah muncul
        Thread.sleep(2000)
        onView(withId(R.id.is_favorite)).check(matches(isDisplayed()))

        // Click tombol favorite untuk ditambahkan ke favorite
        onView(withId(R.id.is_favorite)).perform(click())

        // Kembali ke main menu
        pressBack()

        // Check menu favorite telah muncul
        Thread.sleep(2000)
        onView(withId(R.id.nav_favorites)).check(matches(isDisplayed()))

        // Pindah ke menu favorite event
        onView(withId(R.id.nav_favorites)).perform(click())

        // check favorite match selected
        onView(withId(R.id.nav_favorites)).check(matches(ViewMatcher
                .withBottomNavItemCheckedStatus(true)))

        // check item favorite event telah muncul
        Thread.sleep(2000)
        onView(withId(R.id.rv_favorite_event)).check(matches(isDisplayed()))

        // click item ke 0 yang ditambahkan sebagai favorite
        onView(withId(R.id.rv_favorite_event)).perform(RecyclerViewActions
                .actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // check tombol favorite telah muncul
        Thread.sleep(2000)
        onView(withId(R.id.is_favorite)).check(matches(isDisplayed()))

        // click tombol favorite untuk dihapus dari favorite
        onView(withId(R.id.is_favorite)).perform(click())

        // Kembali ke main menu
        pressBack()

        // pull down untuk refresh data favorite
        onView(withId(R.id.sr_favorite_event)).perform(ViewMatcher.withCustomConstraints(swipeDown(),
                isDisplayingAtLeast(1)))
    }
}