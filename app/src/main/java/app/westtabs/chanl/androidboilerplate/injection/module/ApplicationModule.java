package app.westtabs.chanl.androidboilerplate.injection.module;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import app.westtabs.chanl.androidboilerplate.data.local.DatabaseHelper;
import app.westtabs.chanl.androidboilerplate.data.remote.ApiService;
import dagger.Module;
import dagger.Provides;
import app.westtabs.chanl.androidboilerplate.injection.ApplicationContext;

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
    ApiService provideRibotsService() {
        return ApiService.Creator.newRibotsService();
    }

    @Provides
    @Singleton
    public DatabaseHelper provideDbHelper() {
        return new DatabaseHelper(mApplication);
    }

}
