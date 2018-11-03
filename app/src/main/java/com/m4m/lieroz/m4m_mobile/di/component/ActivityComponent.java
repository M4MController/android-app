package com.m4m.lieroz.m4m_mobile.di.component;

import com.m4m.lieroz.m4m_mobile.di.PerActivity;
import com.m4m.lieroz.m4m_mobile.di.module.ActivityModule;
import com.m4m.lieroz.m4m_mobile.ui.auth.AuthActivity;
import com.m4m.lieroz.m4m_mobile.ui.main.MainActivity;
import com.m4m.lieroz.m4m_mobile.ui.object.ObjectActivity;
import com.m4m.lieroz.m4m_mobile.ui.sensor.SensorActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(AuthActivity activity);

    void inject(MainActivity activity);

    void inject(ObjectActivity activity);

    void inject(SensorActivity activity);
}
