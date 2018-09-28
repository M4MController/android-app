package com.m4m.lieroz.m4m_mobile.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.m4m.lieroz.m4m_mobile.di.ActivityContext;
import com.m4m.lieroz.m4m_mobile.di.PerActivity;
import com.m4m.lieroz.m4m_mobile.ui.main.MainMvpPresenter;
import com.m4m.lieroz.m4m_mobile.ui.main.MainMvpView;
import com.m4m.lieroz.m4m_mobile.ui.main.MainPresenter;
import com.m4m.lieroz.m4m_mobile.ui.object.ObjectMvpPresenter;
import com.m4m.lieroz.m4m_mobile.ui.object.ObjectMvpView;
import com.m4m.lieroz.m4m_mobile.ui.object.ObjectPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

    private AppCompatActivity mActivity;

    public ActivityModule(AppCompatActivity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return mActivity;
    }

    @Provides
    @PerActivity
    MainMvpPresenter<MainMvpView> provideMainPresenter(MainPresenter<MainMvpView> presenter) {
        return presenter;
    }

    @Provides
    @PerActivity
    ObjectMvpPresenter<ObjectMvpView> provideObjectPresenter(ObjectPresenter<ObjectMvpView> presenter) {
        return presenter;
    }
}
