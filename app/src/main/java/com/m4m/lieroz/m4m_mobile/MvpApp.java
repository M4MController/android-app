package com.m4m.lieroz.m4m_mobile;

import android.app.Application;

import com.m4m.lieroz.m4m_mobile.di.component.ApplicationComponent;
import com.m4m.lieroz.m4m_mobile.di.component.DaggerApplicationComponent;
import com.m4m.lieroz.m4m_mobile.di.module.ApplicationModule;

public class MvpApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this)).build();
        mApplicationComponent.inject(this);
    }

    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }

    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
