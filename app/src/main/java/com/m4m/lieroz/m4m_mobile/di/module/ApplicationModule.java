package com.m4m.lieroz.m4m_mobile.di.module;

import android.app.Application;
import android.content.Context;

import com.m4m.lieroz.m4m_mobile.data.AppDataManager;
import com.m4m.lieroz.m4m_mobile.data.DataManager;
import com.m4m.lieroz.m4m_mobile.data.network.ApiHelper;
import com.m4m.lieroz.m4m_mobile.data.network.AppApiHelper;
import com.m4m.lieroz.m4m_mobile.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }
}
