package sunil.project3;

/**
 * Created by ander on 8/19/2016.
 */
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import android.support.test.espresso.contrib.RecyclerViewActions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    public MainActivityTest() {
        super();
    }

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void RecycleViewIsPresent() throws Exception {
        onView(withId(R.id.rv))
                .check(matches(isDisplayed()));
    }

    @Test
    public void RecycleViewIsNotEmpty() throws Exception {
        onView(withId(R.id.rv))
                .perform(scrollToPosition(1))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }

    @Test
    public void WelcomeScreenIsPresent() throws Exception {
        onView(withId(R.id.toggle))
                .perform(click());
        onView(withId(R.id.webScrapeCardView))
                .check(matches(isDisplayed()));
    }




}