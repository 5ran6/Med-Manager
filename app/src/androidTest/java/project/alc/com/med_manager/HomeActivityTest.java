package project.alc.com.med_manager;

import android.app.Activity;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import project.alc.com.med_manager.medication.Medications;
import project.alc.com.med_manager.reminder.Medication;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.registerIdlingResources;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by 4ran6 on 05/04/2018.
 */
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {


    @Rule
    // third parameter is set to false which means the activity is not started automatically
    public ActivityTestRule<HomeActivity> rule =
            new ActivityTestRule(HomeActivity.class, true, false);

    @Test
    public void demonstrateIntentPrep() {
        Intent intent = new Intent();
        intent.putExtra("EXTRA", "Test");
        rule.launchActivity(intent);
        onView(withId(R.id.currentText)).check(matches(withText("")));
    }

    @Test
    public void checkTextDisplayedInDynamicallyCreatedFragment() {
        Medications fragment = new Medications();
        fragment.getActivity().getSupportFragmentManager().beginTransaction()
                .add(R.id.fab, fragment).commit();

//        onView(withId(R.id.currentText)).check(matches(withText("")));
    }
}
