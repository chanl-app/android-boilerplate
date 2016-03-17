package uk.co.ribot.androidboilerplate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import app.westtabs.chanl.androidboilerplate.data.DataManager;
import app.westtabs.chanl.androidboilerplate.data.local.DatabaseHelper;
import app.westtabs.chanl.androidboilerplate.data.local.PreferencesHelper;
import app.westtabs.chanl.androidboilerplate.data.remote.ApiService;
import app.westtabs.chanl.androidboilerplate.test.common.TestDataFactory;
import app.westtabs.chanl.androidboilerplate.util.EventPosterHelper;
import dao.greenrobot.dao.Repo;
import dao.greenrobot.dao.User;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * This test class performs local unit tests without dependencies on the Android framework
 * For testing methods in the DataManager follow this approach:
 * 1. Stub mock helper classes that your method relies on. e.g. RetrofitServices or DatabaseHelper
 * 2. Test the Observable using TestSubscriber
 * 3. Optionally write a SEPARATE test that verifies that your method is calling the right helper
 * using Mockito.verify()
 */
@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Mock
    DatabaseHelper mMockDatabaseHelper;
    @Mock
    PreferencesHelper mMockPreferencesHelper;
    @Mock
    ApiService mMockRibotsService;
    @Mock
    EventPosterHelper mEventPosterHelper;
    private DataManager mDataManager;

    @Before
    public void setUp() {
        mDataManager = new DataManager(mMockRibotsService, mMockPreferencesHelper,
                mMockDatabaseHelper, mEventPosterHelper);
    }

    @Test
    public void syncUsersEmitsValues() {
        List<Repo> repos = TestDataFactory.makeListRepos(3);
        User user = TestDataFactory.generateUser();
        stubSyncRibotsHelperCalls(user, repos);

        TestSubscriber<Repo> result = new TestSubscriber<>();

        mDataManager.syncUserRepos(user.getLogin())
                .flatMap(Observable::from)
                .subscribe(result);
        result.assertNoErrors();
        result.assertReceivedOnNext(repos);
    }

    @Test
    public void syncUserCallsApiAndDatabase() {

        List<Repo> repos = TestDataFactory.makeListRepos(3);
        User user = TestDataFactory.generateUser();
        stubSyncRibotsHelperCalls(user, repos);

        mDataManager.syncUser(user.getLogin()).subscribe();
        // Verify right calls to helper methods
        verify(mMockRibotsService).getUserRepos(user.getLogin());
        verify(mMockDatabaseHelper).saveUser(user);
    }

    @Test
    public void syncUserDoesNotCallDatabaseWhenApiFails() {
//        when(mMockRibotsService.getUserRepos())
//                .thenReturn(Observable.<List<Ribot>>error(new RuntimeException()));
//
//        mDataManager.syncUser().subscribe(new TestSubscriber<Ribot>());
//        // Verify right calls to helper methods
//        verify(mMockRibotsService).getUserRepos();
//        verify(mMockDatabaseHelper, never()).saveUser(anyListOf(Ribot.class));
    }

    private void stubSyncRibotsHelperCalls(User user, List<Repo> repos) {
//        // Stub calls to the ribot service and database helper.
        doReturn(Observable.just(repos))
                .when(mMockRibotsService)
                .getUserRepos(user.getLogin());

        when(mMockDatabaseHelper.saveUser(user))
                .thenReturn(Observable.just(user));
        doReturn(Observable.just(user))
                .when(mMockRibotsService)
                .getUser(user.getLogin());
    }

}
