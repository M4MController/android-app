package com.m4m.lieroz.m4m_mobile.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.m4m.lieroz.m4m_mobile.data.network.model.Object;
import com.m4m.lieroz.m4m_mobile.data.network.model.Sensor;
import com.m4m.lieroz.m4m_mobile.data.network.model.UserRelationsResponse;
import com.m4m.lieroz.m4m_mobile.di.ActivityContext;
import com.m4m.lieroz.m4m_mobile.di.PerActivity;
import com.m4m.lieroz.m4m_mobile.ui.auth.AuthMvpPresenter;
import com.m4m.lieroz.m4m_mobile.ui.auth.AuthMvpView;
import com.m4m.lieroz.m4m_mobile.ui.auth.AuthPresenter;
import com.m4m.lieroz.m4m_mobile.ui.main.MainAdapter;
import com.m4m.lieroz.m4m_mobile.ui.main.MainMvpPresenter;
import com.m4m.lieroz.m4m_mobile.ui.main.MainMvpView;
import com.m4m.lieroz.m4m_mobile.ui.main.MainPresenter;
import com.m4m.lieroz.m4m_mobile.ui.object.ObjectAdapter;
import com.m4m.lieroz.m4m_mobile.ui.object.ObjectMvpPresenter;
import com.m4m.lieroz.m4m_mobile.ui.object.ObjectMvpView;
import com.m4m.lieroz.m4m_mobile.ui.object.ObjectPresenter;
import com.m4m.lieroz.m4m_mobile.ui.sensor.SensorMvpPresenter;
import com.m4m.lieroz.m4m_mobile.ui.sensor.SensorMvpView;
import com.m4m.lieroz.m4m_mobile.ui.sensor.SensorPresenter;
import com.m4m.lieroz.m4m_mobile.utils.rx.AppSchedulerProvider;
import com.m4m.lieroz.m4m_mobile.utils.rx.SchedulerProvider;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

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
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @PerActivity
    AuthMvpPresenter<AuthMvpView> provideAuthPresenter(AuthPresenter<AuthMvpView> presenter) {
        return presenter;
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

    @Provides
    @PerActivity
    SensorMvpPresenter<SensorMvpView> provideSensorPresenter(SensorPresenter<SensorMvpView> presenter) {
        return presenter;
    }

    @Provides
    MainAdapter provideMainAdapter() {
        return new MainAdapter(new ArrayList<Object>());
    }

    @Provides
    ObjectAdapter provideObjectAdapter() {
        return new ObjectAdapter(new ArrayList<Sensor>());
    }
}
