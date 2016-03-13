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
import app.westtabs.chanl.androidboilerplate.util.EventPosterHelper;
import dao.greenrobot.dao.Repo;

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
    public void syncRibotsEmitsValues() {
//        List<Ribot> ribots = Arrays.asList(TestDataFactory.makeRibot("r1"),
//                TestDataFactory.makeRibot("r2"));
//        stubSyncRibotsHelperCalls(ribots);
//
//        TestSubscriber<Ribot> result = new TestSubscriber<>();
//        mDataManager.syncUser().subscribe(result);
//        result.assertNoErrors();
//        result.assertReceivedOnNext(ribots);
    }

    @Test
    public void syncRibotsCallsApiAndDatabase() {
//        List<Ribot> ribots = Arrays.asList(TestDataFactory.makeRibot("r1"),
//                TestDataFactory.makeRibot("r2"));
//        stubSyncRibotsHelperCalls(ribots);
//
//        mDataManager.syncUser().subscribe();
//        // Verify right calls to helper methods
//        verify(mMockRibotsService).getUserRepos();
//        verify(mMockDatabaseHelper).saveUser(ribots);
    }

    @Test
    public void syncRibotsDoesNotCallDatabaseWhenApiFails() {
//        when(mMockRibotsService.getUserRepos())
//                .thenReturn(Observable.<List<Ribot>>error(new RuntimeException()));
//
//        mDataManager.syncUser().subscribe(new TestSubscriber<Ribot>());
//        // Verify right calls to helper methods
//        verify(mMockRibotsService).getUserRepos();
//        verify(mMockDatabaseHelper, never()).saveUser(anyListOf(Ribot.class));
    }

    private void stubSyncRibotsHelperCalls(List<Repo> ribots) {
//        // Stub calls to the ribot service and database helper.
//        when(mMockRibotsService.getUserRepos())
//                .thenReturn(Observable.just(ribots));
//        when(mMockDatabaseHelper.saveUser(ribots))
//                .thenReturn(Observable.from(ribots));
    }

}
