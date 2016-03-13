package app.westtabs.chanl.androidboilerplate.data;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.westtabs.chanl.androidboilerplate.data.local.DatabaseHelper;
import app.westtabs.chanl.androidboilerplate.data.local.PreferencesHelper;
import app.westtabs.chanl.androidboilerplate.data.remote.ApiService;
import app.westtabs.chanl.androidboilerplate.util.EventPosterHelper;
import dao.greenrobot.dao.Repo;
import dao.greenrobot.dao.User;
import rx.Observable;
import rx.functions.Action0;

@Singleton
public class DataManager {

    private final ApiService mRibotsService;
    private final DatabaseHelper mDatabaseHelper;
    private final PreferencesHelper mPreferencesHelper;
    private final EventPosterHelper mEventPoster;

    @Inject
    public DataManager(ApiService ribotsService, PreferencesHelper preferencesHelper,
                       DatabaseHelper databaseHelper, EventPosterHelper eventPosterHelper) {
        mRibotsService = ribotsService;
        mPreferencesHelper = preferencesHelper;
        mDatabaseHelper = databaseHelper;
        mEventPoster = eventPosterHelper;
    }

    public PreferencesHelper getPreferencesHelper() {
        return mPreferencesHelper;
    }

    public Observable<User> syncUser(String username) {
        return mRibotsService.getUser(username)
                .doOnNext(mDatabaseHelper::saveUser);
    }

    public Observable<List<Repo>> syncUserRepos(String username) {
        return mRibotsService.getUserRepos(username)
                .onErrorResumeNext(Observable.just(Collections.emptyList()));
    }

    public Observable<List<Repo>> getRepos() {
        return mDatabaseHelper.getUserRepos();
    }

    public Observable<Repo> saveRepo(Repo repo) {
        return mDatabaseHelper.saveRepo(repo);
    }

    /// Helper method to post events from doOnCompleted.
    private Action0 postEventAction(final Object event) {
        return () -> mEventPoster.postEventSafely(event);
    }

}
