package app.westtabs.chanl.androidboilerplate.injection.component;

import app.westtabs.chanl.androidboilerplate.ui.nfc.NFCActivity;
import dagger.Component;
import app.westtabs.chanl.androidboilerplate.injection.PerActivity;
import app.westtabs.chanl.androidboilerplate.injection.module.ActivityModule;
import app.westtabs.chanl.androidboilerplate.ui.main.MainActivity;

/**
 * This component inject dependencies to all Activities across the application
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);
    void inject(NFCActivity nfcActivity);

}
