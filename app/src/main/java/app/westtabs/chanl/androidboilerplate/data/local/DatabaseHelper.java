package app.westtabs.chanl.androidboilerplate.data.local;

import android.content.Context;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.westtabs.chanl.androidboilerplate.injection.ApplicationContext;
import dagger.Provides;
import dao.greenrobot.dao.DaoMaster;
import dao.greenrobot.dao.DaoSession;
import rx.Observable;
import rx.Subscriber;
import app.westtabs.chanl.androidboilerplate.data.model.Ribot;

@Singleton
public class DatabaseHelper {

    private final DaoSession daoSession;
    DaoMaster.OpenHelper dbOpenHelper;


    @Inject
    public DatabaseHelper(@ApplicationContext Context context) {
        dbOpenHelper = new DaoMaster.DevOpenHelper(context, "app-db", null);
        DaoMaster daoMaster = new DaoMaster(dbOpenHelper.getWritableDatabase());
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }

    /**
     * Remove all the data from all the tables in the database.
     */
    public Observable<Void> clearTables() {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(Subscriber<? super Void> subscriber) {
                if (subscriber.isUnsubscribed()) return;


                //todo logic to clear table
            }
        });
    }

    public Observable<Ribot> setRibots(final Collection<Ribot> newRibots) {
        return Observable.create(new Observable.OnSubscribe<Ribot>() {
            @Override
            public void call(Subscriber<? super Ribot> subscriber) {
                if (subscriber.isUnsubscribed()) return;

            }
        });
    }

    public Observable<List<Ribot>> getRibots() {
        return Observable.create(new Observable.OnSubscribe<List<Ribot>>() {
            @Override
            public void call(Subscriber<? super List<Ribot>> subscriber) {
                if (subscriber.isUnsubscribed()) return;
            }
        });
    }

}
