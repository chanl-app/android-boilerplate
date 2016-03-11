package app.westtabs.chanl.androidboilerplate.injection.component;

import android.app.Application;
import android.content.Context;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import app.westtabs.chanl.androidboilerplate.data.DataManager;
import app.westtabs.chanl.androidboilerplate.data.SyncService;
import app.westtabs.chanl.androidboilerplate.data.local.DatabaseHelper;
import app.westtabs.chanl.androidboilerplate.data.local.PreferencesHelper;
import app.westtabs.chanl.androidboilerplate.data.remote.ApiService;
import app.westtabs.chanl.androidboilerplate.injection.ApplicationContext;
import app.westtabs.chanl.androidboilerplate.injection.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(SyncService syncService);

    @ApplicationContext Context context();
    Application application();
    ApiService ribotsService();
    PreferencesHelper preferencesHelper();
    DatabaseHelper databaseHelper();
    DataManager dataManager();
    Bus eventBus();

}
