package uk.co.ribot.androidboilerplate;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.westtabs.chanl.androidboilerplate.data.DataManager;
import app.westtabs.chanl.androidboilerplate.ui.main.MainMvpView;
import app.westtabs.chanl.androidboilerplate.ui.main.MainPresenter;
import app.westtabs.chanl.androidboilerplate.util.RxSchedulersOverrideRule;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock MainMvpView mMockMainMvpView;
    @Mock DataManager mMockDataManager;
    private MainPresenter mMainPresenter;

    @Rule
    public final RxSchedulersOverrideRule mOverrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        mMainPresenter = new MainPresenter(mMockDataManager);
        mMainPresenter.attachView(mMockMainMvpView);
    }

    @After
    public void tearDown() {
        mMainPresenter.detachView();
    }

    @Test
    public void loadRibotsReturnsRibots() {
//        List<Repo> ribots = TestDataFactory.makeListRepos(10);
//        doReturn(Observable.just(ribots))
//                .when(mMockDataManager)
//                .getRepos();
//
//        mMainPresenter.loadRepos();
//        verify(mMockMainMvpView).showRibots(ribots);
//        verify(mMockMainMvpView, never()).showRibotsEmpty();
//        verify(mMockMainMvpView, never()).showError();
    }

    @Test
    public void loadRibotsReturnsEmptyList() {
//        doReturn(Observable.just(Collections.emptyList()))
//                .when(mMockDataManager)
//                .getRepos();
//
//        mMainPresenter.loadRepos();
//        verify(mMockMainMvpView).showRibotsEmpty();
//        verify(mMockMainMvpView, never()).showRibots(anyListOf(Ribot.class));
//        verify(mMockMainMvpView, never()).showError();
    }

    @Test
    public void loadRibotsFails() {
//        doReturn(Observable.error(new RuntimeException()))
//                .when(mMockDataManager)
//                .getRepos();
//
//        mMainPresenter.loadRepos();
//        verify(mMockMainMvpView).showError();
//        verify(mMockMainMvpView, never()).showRibotsEmpty();
//        verify(mMockMainMvpView, never()).showRibots(anyListOf(Ribot.class));
    }
}
