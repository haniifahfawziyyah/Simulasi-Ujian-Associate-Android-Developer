package com.dicoding.courseschedule.ui.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.dicoding.courseschedule.R
import com.dicoding.courseschedule.ui.add.AddCourseActivity
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class HomeActivityTest{

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
    }

    @Test
    fun validateAddTaskActivity(){
        Intents.init()
        Espresso.onView(withId(R.id.action_add)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.action_add)).perform(ViewActions.click())
        Intents.intended(IntentMatchers.hasComponent(AddCourseActivity::class.java.name))
        Intents.release()
    }
}