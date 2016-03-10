package app.westtabs.chanl.androidboilerplate.ui.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.westtabs.chanl.androidboilerplate.BoilerplateApplication;
import app.westtabs.chanl.androidboilerplate.injection.component.ActivityComponent;
import app.westtabs.chanl.androidboilerplate.injection.component.DaggerActivityComponent;
import app.westtabs.chanl.androidboilerplate.injection.module.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent mActivityComponent;
    protected String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public ActivityComponent getActivityComponent() {
        if (mActivityComponent == null) {
            mActivityComponent = DaggerActivityComponent.builder()
                    .activityModule(new ActivityModule(this))
                    .applicationComponent(BoilerplateApplication.get(this).getComponent())
                    .build();
        }
        return mActivityComponent;
    }

}
