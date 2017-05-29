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
        runTestOnUiThread(new Runnable() {
            @Override
            public void run() {
                java.util.Collection<Activity> activites = ActivityLifecycleMonitorRegistry.getInstance().getActivitiesInStage(Stage.RESUMED);
                activity[0] = Iterables.getOnlyElement(activites);
            }
        });
        return activity[0];
    }

    //тесты

    //тест является ли данный экран администраторским
    public void testAdminAuthorization() throws Throwable {
        onView(withId(R.id.buttonLogin)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.editTextLogin)).perform(typeText("admin"));
        onView(withId(R.id.editTextPassword)).perform(typeText("admin"));
        onView(withId(R.id.buttonLogin)).perform(click());
        SystemClock.sleep(1000);
        assertEquals("AdminController", ((MainActivity)getCurrentActivity()).getVisibleControllerName());
    }

    //тест на авторизацию админа
//    public void test_2_AuthorizationAdmin() throws Exception {
//        onView(withId(R.id.fragmentLogin_tvLogin)).perform(typeText("admin"));
////        onView(withId(R.id.fragmentLogin_tvPassword)).perform(click());
//        onView(withId(R.id.fragmentLogin_tvPassword)).perform(typeText("1234"));
//        onView(withId(R.id.fragmentLogin_btnLogin)).perform(click());
//        String currentActivity = null;
//        try {
//            currentActivity = getCurrentActivity().getClass().getSimpleName();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        assertEquals("AdminActivity", currentActivity);
//
//    }
//
//    //тест на добавление записи в БД
//    public void test_3_Registration() throws Exception {
//        onView(withId(R.id.fragmentLogin_btnRegister)).perform(click());
//        onView(withId(R.id.fragmentRegister_tvLogin)).perform(typeText(login));
//        onView(withId(R.id.fragmentRegister_tvPassword)).perform(typeText("1111"));
//        onView(withId(R.id.fragmentRegister_tvName)).perform(typeText("Elizaveta"));
//        onView(withId(R.id.fragmentRegister_tvMidname)).perform(typeText("Aleksandrovna"));
//
//        onView(withId(R.id.fragmentRegister_tvEmail)).perform(scrollTo());
//
//        onView(withId(R.id.fragmentRegister_tvLastname)).perform(typeText("Ageeva"));
//        onView(withId(R.id.fragmentRegister_tvEmail)).perform(typeText("liza@mail.ru"));
//
//        onView(withId(R.id.fragmentRegister_btnRegister)).perform(scrollTo());
//
//        onView(withId(R.id.fragmentRegister_tvBirthday)).perform(typeText("22.05.1996"));
//        onView(withId(R.id.fragmentRegister_btnRegister)).perform(click());
//
//        DBMain dbMain = new DBMain();
//        try {
//            dbMain = DBSingletone.getHelper().getDbMainDAO().queryForId(login);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        //чтобы сломать
////        dbMain = null;
//        assertNotSame(null, dbMain);
//
//    }
//
//    //тест на авторизацию пользователя
//    public void test_4_AuthorizationUser() throws Exception {
//        onView(withId(R.id.fragmentLogin_tvLogin)).perform(typeText(login));
//        onView(withId(R.id.fragmentLogin_tvPassword)).perform(typeText("1111"));
//        onView(withId(R.id.fragmentLogin_btnLogin)).perform(click());
//        String currentActivity = null;
//        try {
//            currentActivity = getCurrentActivity().getClass().getSimpleName();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        assertEquals("UserActivity", currentActivity);
//
//    }
}
