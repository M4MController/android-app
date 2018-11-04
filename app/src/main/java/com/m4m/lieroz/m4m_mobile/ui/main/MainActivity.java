package com.m4m.lieroz.m4m_mobile.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.data.network.model.Object;
import com.m4m.lieroz.m4m_mobile.ui.auth.AuthActivity;
import com.m4m.lieroz.m4m_mobile.ui.base.BaseActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements MainMvpView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @Inject
    MainMvpPresenter<MainMvpView> mPresenter;

    @Inject
    MainAdapter mAdapter;

    @BindView(R.id.expenses_date_view)
    TextView mExpensesDateView;

    @BindView(R.id.expenses_sum_view)
    TextView mExpensesSumView;

    @BindView(R.id.pay_button)
    Button mPayButton;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        setUp();

        mPresenter.getUserObjects();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mDrawer != null) {
            mDrawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
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
    protected void setUp() {
        mPresenter.onAttach(this);

        mToolbar.setTitle(R.string.app_title);
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

        DateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
        mExpensesDateView.setText(String.format("%s: %s", getResources().getString(R.string.expenses_on), sdf.format(new Date())));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void update(List<Object> objects) {
        double total = 0;
        for (Object o : objects) {
            total += o.getPayments().getCurrentMonth();
        }

        mExpensesSumView.setText(String.format(Locale.ENGLISH, "%.2f %s", total, getResources().getString(R.string.currency_format)));
        mAdapter.setUserObjectsResponseList(objects);
    }

    void setupNavMenu() {
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();

                        switch (id) {
                            case R.id.nav_main: {
                                mDrawer.closeDrawer(GravityCompat.START);
                                return true;
                            }
                            case R.id.nav_finances:
                                return true;
                            case R.id.nav_settings:
                                return true;
                            case R.id.nav_exit: {
                                mPresenter.logOut();
                                finishAffinity();
                                Intent intent = new Intent(getApplicationContext(), AuthActivity.class);
                                startActivity(intent);
                                return true;
                            }
                            default:
                                return false;
                        }
                    }
                });
    }
}
