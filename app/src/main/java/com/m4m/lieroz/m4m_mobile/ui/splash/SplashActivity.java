package com.m4m.lieroz.m4m_mobile.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.ui.auth.AuthActivity;
import com.m4m.lieroz.m4m_mobile.ui.base.BaseActivity;
import com.m4m.lieroz.m4m_mobile.ui.main.MainActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;

public class SplashActivity extends BaseActivity implements SplashMvpView {

    @Inject
    SplashMvpPresenter<SplashMvpView> mPresenter;

    public static Intent getStartIntent(Context context) {
        return new Intent(context, SplashActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        setUp();
    }

    @Override
    public void openAuthActivity() {
        startActivity(AuthActivity.getStartIntent(SplashActivity.this));
        finish();
    }

    @Override
    public void openMainActivity() {
        startActivity(MainActivity.getStartIntent(SplashActivity.this));
        finish();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
        mPresenter.onAttach(this);
    }
}
