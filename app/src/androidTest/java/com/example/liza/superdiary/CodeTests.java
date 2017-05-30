package com.example.liza.superdiary;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.example.liza.superdiary.database.DatabaseRepo;
import com.example.liza.superdiary.database.models.DaoMaster;
import com.example.liza.superdiary.database.models.DaoSession;
import com.example.liza.superdiary.database.models.Note;
import com.example.liza.superdiary.database.models.NoteDao;
import com.example.liza.superdiary.database.models.Notification;
import com.example.liza.superdiary.database.models.NotificationDao;
import com.example.liza.superdiary.database.models.Task;
import com.example.liza.superdiary.database.models.TaskDao;
import com.example.liza.superdiary.database.models.User;
import com.example.liza.superdiary.database.models.UserDao;
import com.example.liza.superdiary.preferences.PreferencesRepo;
import com.example.liza.superdiary.ui.details.DetailsPresenter;

import junit.framework.Assert;

import org.greenrobot.greendao.database.Database;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;

import static com.example.liza.superdiary.R.string.login;
import static org.mockito.BDDMockito.given;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(MockitoJUnitRunner.class)
public class CodeTests {

    public static final String LIZA = "liza";
    public static final String PASSWORD = "1234";
    private Context context;
    @Mock
    DatabaseRepo mockDatabaseRepo;

    @Mock
    PreferencesRepo preferencesRepo;
    private static final String DATABASE_NAME = "app-database";
    private DaoSession daoSession;
    private TaskDao taskDao;
    private NotificationDao notificationDao;
    private NoteDao noteDao;
    private UserDao userDao;

    public CodeTests() {
        context = InstrumentationRegistry.getContext();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(InstrumentationRegistry.getTargetContext(), DATABASE_NAME);
        Database database = devOpenHelper.getWritableDb();
        daoSession = new DaoMaster(database).newSession();
        noteDao = daoSession.getNoteDao();
        taskDao = daoSession.getTaskDao();
        notificationDao = daoSession.getNotificationDao();
        userDao = daoSession.getUserDao();
    }

    @Before
    public void setUp() throws Exception {
        context = InstrumentationRegistry.getContext();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(InstrumentationRegistry.getTargetContext(), DATABASE_NAME);
        Database database = devOpenHelper.getWritableDb();
        daoSession = new DaoMaster(database).newSession();
        noteDao = daoSession.getNoteDao();
        taskDao = daoSession.getTaskDao();
        notificationDao = daoSession.getNotificationDao();
        userDao = daoSession.getUserDao();

    }

    @Test
    public void isAdminInDatabase() {
        //given
        User user = new User();
        user.setId(1L);
        given(mockDatabaseRepo.getUser("admin")).willReturn(Single.just(user));

        //when
        User result = mockDatabaseRepo.getUser("admin").blockingGet();

        //then
        Assert.assertEquals(result, user);
    }

    @Test
    public void addUser() {
        //given
        User user = new User();
        String login = "liza";
        String password = "1234";
        user.setLogin(login);
        user.setPassword(password);

        //when
        userDao.insertOrReplace(user);

        //then
        Assert.assertTrue(userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .where(UserDao.Properties.Password.in(password))
                .unique() != null);
    }

    @Test
    public void userNotConfirmedAfterRegistration() {
        //given
        User user = new User();
        String login = "safa";
        String password = "sefdgvb";
        user.setLogin(login);
        user.setPassword(password);

        //when
        userDao.insertOrReplace(user);

        //then
        Assert.assertTrue(userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .where(UserDao.Properties.Password.in(password))
                .unique().getIsConfirmed() == false);
    }

    @Test
    public void confirmUser() {
        //given
        String login = "liza";

        //when
        userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique().setIsConfirmed(true);

        //then
        Assert.assertTrue(userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique().getIsConfirmed() == true);
    }

    @Test
    public void addNote() {
        addUser();
        //given
        String login = LIZA;

        //when
        User user = userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique();

        Note note = new Note();
        note.setLogin(user.getLogin());
        note.setUserId(user.getId());
        note.setId(noteDao.insert(note));
        note.setText("какой-то текст для заметки");
        user.getNotes().add(note);
        userDao.update(user);

        //then user
        Assert.assertTrue(userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique().getNotes().get(0) != null);
    }

    @Test
    public void addTask() {
        addUser();
        //given
        String login = LIZA;

        //when
        User user = userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique();

        Task task = new Task();
        task.setLogin(user.getLogin());
        task.setUserId(user.getId());
        task.setId(taskDao.insert(task));
        task.setText("какой-то текст для заметки");
        user.getTasks().add(task);
        userDao.update(user);

        //then user
        Assert.assertTrue(userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique().getTasks().get(0) != null);
    }

    @Test
    public void addNotification() {
        addUser();
        //given
        String login = "liza";

        //when
        User user = userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique();

        Notification notification = new Notification();
        notification.setLogin(user.getLogin());
        notification.setUserId(user.getId());
        notification.setId(notificationDao.insert(notification));
        notification.setText("какой-то текст для заметки");
        user.getNotifications().add(notification);
        userDao.update(user);

        //then user
        Assert.assertTrue(userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique().getNotifications().get(0) != null);
    }

    @Test
    public void addThreeNotes() {
        addUser();
        //given
        String login = LIZA;

        //when
        User user = userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique();
        noteDao.queryBuilder()
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();
        user.getNotes().clear();
        userDao.update(user);

        Note note1 = new Note();
        note1.setLogin(user.getLogin());
        note1.setUserId(user.getId());
        note1.setText("какой-то текст для заметки");
        noteDao.insert(note1);

        Note note2 = new Note();
        note2.setLogin(user.getLogin());
        note2.setUserId(user.getId());
        note2.setText("какой-то текст для заметки");
        noteDao.insert(note2);

        Note note3 = new Note();
        note3.setLogin(user.getLogin());
        note3.setUserId(user.getId());
        note3.setText("какой-то текст для заметки");
        noteDao.insert(note3);

        user.getNotes().add(note1);
        user.getNotes().add(note2);
        user.getNotes().add(note3);
        userDao.update(user);

        //then user
        Assert.assertTrue(userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique().getNotes().size() == 3);
    }

    @Test
    public void deleteUser() {
        //given
        addUser();


        //when
        userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(LIZA))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();

        //then
        Assert.assertTrue(userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .where(UserDao.Properties.Password.in(PASSWORD))
                .unique() == null);
    }

    @Test
    public void updateNote() {
        //given
        addNote();
        String login = LIZA;


        //when
        final String newText = "измененный текст";
        userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique().getNotes().get(0).setText(newText);

        //then
        Assert.assertTrue(userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique().getNotes().get(0).getText().equals(newText));
    }

    @Test
    public void updateTask() {
        //given
        addTask();
        String login = LIZA;


        //when
        final String newText = "измененный текст";
        userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique().getTasks()
                .get(0)
                .setText(newText);

        //then
        Assert.assertTrue(userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique()
                .getTasks()
                .get(0)
                .getText()
                .equals(newText));
    }

    @Test
    public void updateNotification() {
        //given
        addNotification();
        String login = LIZA;


        //when
        final String newText = "измененный текст";
        userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique()
                .getNotifications()
                .get(0)
                .setText(newText);

        //then
        Assert.assertTrue(userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique()
                .getNotifications()
                .get(0)
                .getText()
                .equals(newText));
    }

    @Test
    public void deleteNote() {
        //given
        addNote();
        String login = LIZA;
        String text = "какой-то текст для заметки";


        //when
        noteDao.queryBuilder()
                .where(NoteDao.Properties.Login.in(login))
                .where(NoteDao.Properties.Text.in(text))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();

        //then
        Assert.assertTrue(noteDao.queryBuilder()
                .where(NoteDao.Properties.Login.in(login))
                .where(NoteDao.Properties.Text.in(text))
                .unique() == null);
    }

    @Test
    public void deleteTask() {
        //given
        addTask();
        String login = LIZA;
        String text = "какой-то текст для заметки";


        //when
        taskDao.queryBuilder()
                .where(TaskDao.Properties.Login.in(login))
                .where(TaskDao.Properties.Text.in(text))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();

        //then
        Assert.assertTrue(taskDao.queryBuilder()
                .where(TaskDao.Properties.Login.in(login))
                .where(TaskDao.Properties.Text.in(text))
                .unique() == null);
    }

    @Test
    public void deleteNotification() {
        //given
        addNote();
        String login = LIZA;
        String text = "какой-то текст для заметки";


        //when
        notificationDao.queryBuilder()
                .where(NotificationDao.Properties.Login.in(login))
                .where(NotificationDao.Properties.Text.in(text))
                .buildDelete()
                .executeDeleteWithoutDetachingEntities();

        //then
        Assert.assertTrue(notificationDao.queryBuilder()
                .where(NotificationDao.Properties.Login.in(login))
                .where(NotificationDao.Properties.Text.in(text))
                .unique() == null);
    }

    @Test
    public void isDateCorrect() {
        String date = "18 июля";

        //then
        Assert.assertTrue(DetailsPresenter.isDateCorrect(date));
    }

    @Test
    public void isTimeCorrect() {
        String time = "18:30";

        //then
        Assert.assertTrue(DetailsPresenter.isTimeCorrect(time));
    }

    @Test
    public void isTextCorrect() {
        String text = "Текст";

        //then
        Assert.assertTrue(DetailsPresenter.isTextCorrect(text));
    }

    @Test
    public void isTextNotEmpty() {
        String text = "Текст";

        //then
        Assert.assertTrue(DetailsPresenter.isTextNotEmpty(text));
    }


}
