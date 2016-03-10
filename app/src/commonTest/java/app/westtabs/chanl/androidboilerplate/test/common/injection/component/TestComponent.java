package app.westtabs.chanl.androidboilerplate.test.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import app.westtabs.chanl.androidboilerplate.injection.component.ApplicationComponent;
import app.westtabs.chanl.androidboilerplate.test.common.injection.module.ApplicationTestModule;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends ApplicationComponent {

}
