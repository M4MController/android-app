package com.m4m.lieroz.m4m_mobile.di.component;

import android.app.Application;
import android.content.Context;

import com.m4m.lieroz.m4m_mobile.MvpApp;
import com.m4m.lieroz.m4m_mobile.di.ApplicationContext;
import com.m4m.lieroz.m4m_mobile.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(MvpApp app);

    @ApplicationContext
    Context context();

    Application application();
}
