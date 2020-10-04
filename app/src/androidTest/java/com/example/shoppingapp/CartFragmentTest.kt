package com.example.shoppingapp

import android.os.Bundle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.shoppingapp.ui.CartsFragment
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CartFragmentTest {

    @get:Rule
    val activityTestRule =
        ActivityTestRule(MainActivity::class.java)


    @Test
    fun setUptest() {
        val fragment = CartsFragment()
        activityTestRule.activity.supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit()
    }


}