package com.example.liza.superdiary;

import com.example.liza.superdiary.database.DatabaseRepo;
import com.example.liza.superdiary.database.models.User;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Single;

import static org.mockito.BDDMockito.given;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class CodeTests {

    @Mock
    DatabaseRepo mockDatabaseRepo;

    @Test
    public void getHistory_shouldReturnTranslationHistory(){
        //given
        User testUser = new User();
        testUser.setId(123l);
        given(mockDatabaseRepo.getUser("admin")).willReturn(Single.just(testUser));

        //when
        User result =  mockDatabaseRepo.getUser("admin").blockingGet();

        //then
        Assert.assertEquals(result, testUser);
//        assertThat(result).isNotEmpty();
//        assertThat(result.size()).isEqualTo(testTranslations.size());
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
