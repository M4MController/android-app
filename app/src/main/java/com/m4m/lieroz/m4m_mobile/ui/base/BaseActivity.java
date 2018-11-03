package com.m4m.lieroz.m4m_mobile.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.m4m.lieroz.m4m_mobile.MvpApp;
import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.di.component.ActivityComponent;
import com.m4m.lieroz.m4m_mobile.di.component.DaggerActivityComponent;
import com.m4m.lieroz.m4m_mobile.di.module.ActivityModule;
import com.m4m.lieroz.m4m_mobile.utils.CommonUtils;

import butterknife.BindView;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements MvpView {

    @Nullable
    @BindView(R.id.toolbar)
    protected Toolbar mToolbar;

    @Nullable
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @Nullable
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

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
    protected void onResume() {
        super.onResume();
        if (mDrawer != null)
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    protected void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

    void setupNavMenu() {
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();

                        switch (id) {
                            case R.id.nav_camera:
                                return true;
                            case R.id.nav_gallery:
                                return true;
                            case R.id.nav_slideshow:
                                return true;
                            case R.id.nav_manage:
                                return true;
                            case R.id.nav_share:
                                return true;
                            case R.id.nav_send:
                                return true;
                            default:
                                return false;
                        }
                    }
                });
    }

    protected void setUp() {
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
        setupNavMenu();
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
}
