package app.westtabs.chanl.androidboilerplate.injection.module;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import app.westtabs.chanl.androidboilerplate.data.local.DatabaseHelper;
import dagger.Module;
import dagger.Provides;
import app.westtabs.chanl.androidboilerplate.data.remote.RibotsService;
import app.westtabs.chanl.androidboilerplate.injection.ApplicationContext;
import dao.greenrobot.dao.DaoMaster;
import dao.greenrobot.dao.DaoSession;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    Bus provideEventBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    RibotsService provideRibotsService() {
        return RibotsService.Creator.newRibotsService();
    }

    @Provides
    @Singleton
    public DatabaseHelper provideDbHelper() {
        return new DatabaseHelper(mApplication);
    }

}
