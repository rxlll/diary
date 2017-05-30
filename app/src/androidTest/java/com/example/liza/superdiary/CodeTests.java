package com.example.liza.superdiary;

import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.example.liza.superdiary.database.DatabaseRepo;
import com.example.liza.superdiary.database.models.DaoMaster;
import com.example.liza.superdiary.database.models.DaoSession;
import com.example.liza.superdiary.database.models.Note;
import com.example.liza.superdiary.database.models.NoteDao;
import com.example.liza.superdiary.database.models.NotificationDao;
import com.example.liza.superdiary.database.models.TaskDao;
import com.example.liza.superdiary.database.models.User;
import com.example.liza.superdiary.database.models.UserDao;
import com.example.liza.superdiary.preferences.PreferencesRepo;

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
    public void returnMockAdmin() {
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
        String login = "liza";

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

        //thenuser
        Assert.assertTrue(userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .unique().getNotes().get(0) != null);
    }

    @Test
    public void deleteUser() {
        //given
        addUser();


        //when
        userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(LIZA))
                .buildDelete().executeDeleteWithoutDetachingEntities();

        //then
        Assert.assertTrue(userDao.queryBuilder()
                .where(UserDao.Properties.Login.in(login))
                .where(UserDao.Properties.Password.in(PASSWORD))
                .unique() == null);
    }


//    private ArrayList<Translation> getTestTranslations(){
//        return new ArrayList<Translation>(){{
//            add(new Translation());
//            add(new Translation());
//            add(new Translation());
//            add(new Translation());
//        }};
//    }

//    @Test
//    public void saveChanges_ShouldSaveTranslation(){
//        //given
//        Translation translation = new Translation();
//
//        //when
//        historyInteractor.saveChanges(translation);
//
//        //then
//        verify(mockHistoryRepository).update(translation);
//    }
//
//    @Test
//    public void delete_shouldDeleteTranslation(){
//        //given
//        Translation translation  = new Translation();
//
//        //when
//        historyInteractor.delete(translation);
//
//        //then
//        verify(mockHistoryRepository).delete(translation);
//    }

}
