package uk.co.ribot.androidboilerplate;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import app.westtabs.chanl.androidboilerplate.data.DataManager;
import app.westtabs.chanl.androidboilerplate.test.common.TestDataFactory;
import app.westtabs.chanl.androidboilerplate.ui.main.MainMvpView;
import app.westtabs.chanl.androidboilerplate.ui.main.MainPresenter;
import app.westtabs.chanl.androidboilerplate.util.RxSchedulersOverrideRule;
import dao.greenrobot.dao.Repo;
import dao.greenrobot.dao.User;
import rx.Observable;

import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    MainMvpView mMockMainMvpView;
    @Mock
    DataManager mMockDataManager;
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
    public void loadUser() {
        User user = TestDataFactory.generateUser();
//        TestDataFactory.generateList(User.class, 3);

        doReturn(Observable.just(TestDataFactory.makeListRepos(5)))
                .when(mMockDataManager)
                .syncUserRepos(user.getLogin());

        doReturn(Observable.just(user))
                .when(mMockDataManager)
                .syncUser(user.getLogin());

        mMainPresenter.getUser(user.getLogin());

        verify(mMockMainMvpView).showRepos(anyListOf(Repo.class));

        verify(mMockMainMvpView, never()).showError();
        verify(mMockMainMvpView, never()).showRibotsEmpty();
    }

    public void loadUserFails() {
        doReturn(Observable.empty())
                .when(mMockDataManager)
                .syncUser("");

        mMainPresenter.getUser("");

        verify(mMockMainMvpView).showToast("User not found");
        verify(mMockMainMvpView, never()).showRibotsEmpty();
        verify(mMockMainMvpView, never()).showRepos(anyListOf(Repo.class));
    }
}
