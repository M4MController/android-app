package com.m4m.lieroz.m4m_mobile.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.m4m.lieroz.m4m_mobile.MvpApp;
import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.di.component.ActivityComponent;
import com.m4m.lieroz.m4m_mobile.di.component.DaggerActivityComponent;
import com.m4m.lieroz.m4m_mobile.di.module.ActivityModule;
import com.m4m.lieroz.m4m_mobile.utils.CommonUtils;

import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    private ProgressDialog mProgressDialog;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public ActivityComponent getActivityComponent() {
        return mActivityComponent;
    }

    public void setUnBinder(Unbinder unbinder) {
        mUnBinder = unbinder;
    }

    @Override
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    protected abstract void setUp();
}
