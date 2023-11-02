package com.blumer.msu.criminalintent


import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test

//https://www.vogella.com/tutorials/AndroidTestingEspresso/article.html
//I used this as a framework for this, I used chatGPT to help assist with the errors I was getting
//The main error was mainly due to me not correctly referencing crime in CrimeDetailsFragment
class CrimeDetailFragmentTest {

    @Test
    fun editTextUpdatesCrimeTitle() {

        val scenario: FragmentScenario<CrimeDetailsFragment> =
            launchFragmentInContainer(themeResId = R.style.Theme_CriminalIntent)

        onView(withId(R.id.crime_title)).perform(typeText("New Crime Title"))
        scenario.onFragment { fragment ->
            assertEquals(fragment.crime.title,"New Crime Title")
        }
    }
    @Test
    fun checkBoxUpdatesCrimeSolved() {
        val scenario: FragmentScenario<CrimeDetailsFragment> =
            launchFragmentInContainer(themeResId = R.style.Theme_CriminalIntent)

        onView(withId(R.id.crime_solved)).perform(click())

        scenario.onFragment { fragment ->
            assertTrue(fragment.crime.isSolved)
        }
    }
}