package com.example.myproject

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginInstrumentedTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<Login1Activity>()

    @Before
    fun setup() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun testSuccessfulLogin_navigatesToDashboard() {
        // 1. Arrange: Input credentials
        composeRule.onNodeWithTag("email").performTextInput("ram@gmail.com")
        composeRule.onNodeWithTag("password").performTextInput("password")

        // 2. Act: Click login
        composeRule.onNodeWithTag("login_button").performClick()

        /**
         * 3. Assert: Verification
         * Instead of Thread.sleep(), we use awaitIdle().
         * Compose will wait until the UI is no longer changing.
         * Note: If your Repo takes a long time (Network), use waitUntil.
         */
        composeRule.waitForIdle()

        // Check if the intent to DashboardActivity2 was sent
        intended(hasComponent(DashboardActivity2::class.java.name))
    }

    @Test
    fun testFailedLogin_staysOnLoginScreen() {
        // 1. Arrange: Input invalid credentials
        composeRule.onNodeWithTag("email").performTextInput("wrong@test.com")
        composeRule.onNodeWithTag("password").performTextInput("wrong")

        // 2. Act: Click login
        composeRule.onNodeWithTag("login_button").performClick()

        // 3. Assert: Verify the login button is still there (meaning we didn't navigate)
        composeRule.onNodeWithTag("login_button").assertIsDisplayed()

        // Verify Welcome text is still visible
        composeRule.onNodeWithText("Welcome to Marketo").assertIsDisplayed()
    }

    @Test
    fun testPasswordVisibility_ToggleWorks() {
        // Input text
        composeRule.onNodeWithTag("password").performTextInput("my_pass")

        // Find the toggle (it has no tag, so we use text) and click
        composeRule.onNodeWithText("Show").performClick()

        // Verify it toggles to "Hide"
        composeRule.onNodeWithText("Hide").assertIsDisplayed()
    }
}