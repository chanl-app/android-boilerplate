package uk.co.ribot.androidboilerplate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.Arrays;
import java.util.List;

import app.westtabs.chanl.androidboilerplate.BuildConfig;
import app.westtabs.chanl.androidboilerplate.data.local.DatabaseHelper;
import app.westtabs.chanl.androidboilerplate.test.common.TestDataFactory;
import app.westtabs.chanl.androidboilerplate.util.DefaultConfig;
import dao.greenrobot.dao.Repo;
import dao.greenrobot.dao.User;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests integration with a SQLite Database using Robolectric
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = DefaultConfig.EMULATE_SDK)
public class DatabaseHelperTest {

    private final DatabaseHelper mDatabaseHelper = new DatabaseHelper(RuntimeEnvironment.application);

    @Before
    public void setUp() {
        mDatabaseHelper.clearTables().subscribe();
    }

    @Test
    public void saveUser() {
        User user1 = TestDataFactory.generateUser();
        User user2 = TestDataFactory.generateUser();
        List<User> users = Arrays.asList(user1, user2);

        TestSubscriber<User> result = new TestSubscriber<>();
        Observable.from(users)
                .flatMap(mDatabaseHelper::saveUser)
                .subscribe(result);

        result.assertNoErrors();
        result.assertReceivedOnNext(users);

        List<User> query = mDatabaseHelper.getDaoSession().getUserDao().loadAll();
        assertThat(query.size()).isEqualTo(2);
        assertThat(query).contains(user1, user2);
    }

    @Test
    public void getRepos() {
        List<Repo> repos = TestDataFactory.makeListRepos(2);

        Observable.from(repos)
                .map(mDatabaseHelper::saveRepo)
                .subscribe();

        TestSubscriber<List<Repo>> result = new TestSubscriber<>();
        mDatabaseHelper.getUserRepos().subscribe(result);
        result.assertNoErrors();
        result.assertValue(repos);
    }

}