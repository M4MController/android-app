package com.m4m.lieroz.m4m_mobile.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.m4m.lieroz.m4m_mobile.MvpApp;
import com.m4m.lieroz.m4m_mobile.di.component.ActivityComponent;
import com.m4m.lieroz.m4m_mobile.di.component.DaggerActivityComponent;
import com.m4m.lieroz.m4m_mobile.di.module.ActivityModule;

import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private ActivityComponent mActivityComponent;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .applicationComponent(((MvpApp) getApplication()).getComponent())
                .build();
    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    public void setUnBinder(Unbinder unbinder) {
        mUnBinder = unbinder;
    }

    protected abstract void setUp();
}
