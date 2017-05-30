package com.example.liza.superdiary;

import android.app.Activity;
import android.os.SystemClock;
import android.support.test.espresso.core.deps.guava.collect.Iterables;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.test.ActivityInstrumentationTestCase2;

import com.example.liza.superdiary.ui.main.MainActivity;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UITests extends ActivityInstrumentationTestCase2<MainActivity> {
    String login;

    //настройка класса
    public UITests() {
        super(MainActivity.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        login = "liza_ageeva";
        getActivity();
    }

    Activity getCurrentActivity() throws Throwable {
        getInstrumentation().waitForIdleSync();
        final Activity[] activity = new Activity[1];
        runTestOnUiThread(() -> {
            java.util.Collection<Activity> activites = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
            activity[0] = Iterables.getOnlyElement(activites);
        });
        return activity[0];
    }

    //тесты

    //тест является ли данный экран администраторским
    public void test1_Registration() throws Throwable {
        SystemClock.sleep(1000);
        onView(withId(R.id.buttonRegistration)).perform(click());
        SystemClock.sleep(300);
        onView(withId(R.id.editTextLogin)).perform(typeText("user"));
        onView(withId(R.id.editTextPassword)).perform(typeText("1234"));
        onView(withId(R.id.editTextName)).perform(typeText("name"));
        onView(withId(R.id.editTextSurname)).perform(typeText("surname"));
        onView(withId(R.id.editTextPatronymic)).perform(typeText("patronymic"));
        onView(withId(R.id.editTextEmail)).perform(typeText("email"));
        onView(withId(R.id.editTextBirthday)).perform(typeText("22.05.1996"));
        onView(withId(R.id.buttonRegister)).perform(click());
        SystemClock.sleep(1000);
        assertEquals("StartController", ((MainActivity) getCurrentActivity()).getVisibleControllerName());
    }

    public void test2_RegistrationSameLogin() throws Throwable {
        SystemClock.sleep(1000);
        onView(withId(R.id.buttonRegistration)).perform(click());
        SystemClock.sleep(300);
        onView(withId(R.id.editTextLogin)).perform(typeText("user"));
        onView(withId(R.id.editTextPassword)).perform(typeText("1234"));
        onView(withId(R.id.editTextName)).perform(typeText("name"));
        onView(withId(R.id.editTextSurname)).perform(typeText("surname"));
        onView(withId(R.id.editTextPatronymic)).perform(typeText("patronymic"));
        onView(withId(R.id.editTextEmail)).perform(typeText("email"));
        onView(withId(R.id.editTextBirthday)).perform(typeText("22.05.1996"));
        onView(withId(R.id.buttonRegister)).perform(click());
        SystemClock.sleep(1000);
        assertEquals("RegistrationController", ((MainActivity) getCurrentActivity()).getVisibleControllerName());
    }

    public void test3_LoginWithWrongPassword() throws Throwable {
        SystemClock.sleep(1000);
        onView(withId(R.id.buttonGoLogin)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.editTextLogin)).perform(typeText("user"));
        onView(withId(R.id.editTextPassword)).perform(typeText("frg46r3r4fg"));
        onView(withId(R.id.buttonLogin)).perform(click());
        SystemClock.sleep(1000);
        assertEquals("LoginController", ((MainActivity) getCurrentActivity()).getVisibleControllerName());
    }

    public void test4_AdminAuthorization() throws Throwable {
        SystemClock.sleep(1000);
        onView(withId(R.id.buttonGoLogin)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.editTextLogin)).perform(typeText("admin"));
        onView(withId(R.id.editTextPassword)).perform(typeText("admin"));
        onView(withId(R.id.buttonLogin)).perform(click());
        SystemClock.sleep(1000);
        assertEquals("AdminController", ((MainActivity) getCurrentActivity()).getVisibleControllerName());
    }

    public void test5_ConfirmUser() throws Throwable {
        SystemClock.sleep(2000);
        onView(withId(R.id.imageViewConfirm)).perform(click());
        boolean isConfirmed = true;
        SystemClock.sleep(1500);
        onView(withId(R.id.buttonLogout)).perform(click());
        assertTrue(isConfirmed);
    }

    public void test6_RegistrationNewLogin() throws Throwable {
        SystemClock.sleep(1000);
        onView(withId(R.id.buttonRegistration)).perform(click());
        SystemClock.sleep(300);
        onView(withId(R.id.editTextLogin)).perform(typeText("NEW_user"));
        onView(withId(R.id.editTextPassword)).perform(typeText("1234"));
        onView(withId(R.id.editTextName)).perform(typeText("name"));
        onView(withId(R.id.editTextSurname)).perform(typeText("surname"));
        onView(withId(R.id.editTextPatronymic)).perform(typeText("patronymic"));
        onView(withId(R.id.editTextEmail)).perform(typeText("email"));
        onView(withId(R.id.editTextBirthday)).perform(typeText("22.05.1996"));
        onView(withId(R.id.buttonRegister)).perform(click());
        SystemClock.sleep(1000);
        assertEquals("StartController", ((MainActivity) getCurrentActivity()).getVisibleControllerName());
    }

    public void test7_LoginUnconfirmed() throws Throwable {
        SystemClock.sleep(1000);
        onView(withId(R.id.buttonGoLogin)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.editTextLogin)).perform(typeText("NEW_user"));
        onView(withId(R.id.editTextPassword)).perform(typeText("1234"));
        onView(withId(R.id.buttonLogin)).perform(click());
        SystemClock.sleep(1000);
        assertEquals("LoginController", ((MainActivity) getCurrentActivity()).getVisibleControllerName());
    }

    public void test8_LoginConfirmed() throws Throwable {
        SystemClock.sleep(1000);
        onView(withId(R.id.buttonGoLogin)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.editTextLogin)).perform(typeText("user"));
        onView(withId(R.id.editTextPassword)).perform(typeText("1234"));
        onView(withId(R.id.buttonLogin)).perform(click());
        SystemClock.sleep(1000);
        assertEquals("UserController", ((MainActivity) getCurrentActivity()).getVisibleControllerName());

    }


}
