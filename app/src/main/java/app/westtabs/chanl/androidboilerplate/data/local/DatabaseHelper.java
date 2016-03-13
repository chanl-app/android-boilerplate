package app.westtabs.chanl.androidboilerplate.data.local;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.westtabs.chanl.androidboilerplate.injection.ApplicationContext;
import dao.greenrobot.dao.DaoMaster;
import dao.greenrobot.dao.DaoSession;
import dao.greenrobot.dao.Repo;
import dao.greenrobot.dao.User;
import dao.greenrobot.dao.UserDao;
import rx.Observable;
import rx.Subscriber;

@Singleton
public class DatabaseHelper {

    private final DaoSession daoSession;
    DaoMaster.DevOpenHelper dbOpenHelper;

    @Inject
    public DatabaseHelper(@ApplicationContext Context context) {
        dbOpenHelper = new DaoMaster.DevOpenHelper(context, "app-db", null);
        DaoMaster daoMaster = new DaoMaster(dbOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    public Observable<User> saveUser(User user) {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                UserDao userDao = getDaoSession().getUserDao();
                if (userDao.insert(user) > 0) subscriber.onNext(user);
                subscriber.onCompleted();
            }
        });
    }

    public Observable<List<Repo>> getUserRepos() {
        return Observable.create(new Observable.OnSubscribe<List<Repo>>() {
            @Override
            public void call(Subscriber<? super List<Repo>> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                subscriber.onNext(
                        getDaoSession()
                                .getRepoDao()
                                .queryBuilder()
                                .list()
                );
            }
        });
    }

    public Observable<Repo> saveRepo(Repo repo) {
        return Observable.create((Observable.OnSubscribe<Repo>) subscriber -> {
            if (subscriber.isUnsubscribed()) return;
            long id = getDaoSession().getRepoDao().insert(repo);
            if (id > 0) subscriber.onNext(repo);
        });
    }

    public Observable<Void> clearTables() {
        return Observable.create((Observable.OnSubscribe<Void>) subscriber -> {
            if (subscriber.isUnsubscribed()) return;
            getDaoSession().getRepoDao().deleteAll();
            getDaoSession().getUserDao().deleteAll();
        });
    }
}
