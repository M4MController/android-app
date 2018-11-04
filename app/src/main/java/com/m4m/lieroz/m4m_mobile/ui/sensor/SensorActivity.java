package com.m4m.lieroz.m4m_mobile.ui.sensor;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.google.gson.Gson;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.data.network.model.Sensor;
import com.m4m.lieroz.m4m_mobile.data.network.model.SensorDataPeriodResponse;
import com.m4m.lieroz.m4m_mobile.ui.auth.AuthActivity;
import com.m4m.lieroz.m4m_mobile.ui.base.BaseActivity;
import com.m4m.lieroz.m4m_mobile.ui.main.MainActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SensorActivity extends BaseActivity implements SensorMvpView {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;

    @Inject
    SensorMvpPresenter<SensorMvpView> mPresenter;

    @BindView(R.id.graph)
    GraphView graph;

    @BindView(R.id.company_name_view)
    TextView mCompanyNameView;

    @BindView(R.id.company_account_view)
    TextView mCompanyAccountView;

    @BindView(R.id.company_address_view)
    TextView mCompanyAddressView;

    @BindView(R.id.company_phone_view)
    TextView mCompanyPhoneView;

    @BindView(R.id.expenses_date_view)
    TextView mExpensesDateView;

    @BindView(R.id.expenses_sum_view)
    TextView mExpensesSumView;

    @BindView(R.id.pay_button)
    Button mPayButton;

    @Inject
    SensorAdapter mAdapter;

    @BindView(R.id.rules_recycler_view)
    RecyclerView mRecyclerView;

    private Sensor mSensor;
    private LineGraphSeries<DataPoint> mCurrentMonthSeries = new LineGraphSeries<>();
    private LineGraphSeries<DataPoint> mPrevYearSeries = new LineGraphSeries<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        setUp();
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

        Intent intent = getIntent();
        mToolbar.setTitle(intent.getStringExtra("title"));
        mToolbar.setSubtitle(intent.getStringExtra("objectName"));
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

        mSensor = (new Gson()).fromJson(intent.getStringExtra("sensor"), Sensor.class);

        mCompanyNameView.setText(mSensor.getFinance().getServiceCompany().getName());
        mCompanyAccountView.setText(mSensor.getFinance().getServiceCompany().getBankAccountId());
        mCompanyAddressView.setText(mSensor.getFinance().getServiceCompany().getAddress());
        mCompanyPhoneView.setText(mSensor.getFinance().getServiceCompany().getPhone());

        DateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
        mExpensesDateView.setText(String.format("%s: %s", getResources().getString(R.string.expenses_on), sdf.format(new Date())));
        mExpensesSumView.setText(String.format(Locale.ENGLISH, "%.2f %s", mSensor.getPayments().getCharge(), getResources().getString(R.string.currency_format)));

        initGraph();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void update(List<SensorDataPeriodResponse.Data> data, boolean current) {
        Calendar calendar = Calendar.getInstance();
        int f = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, f);
        DataPoint[] dataPoints = new DataPoint[data.size()];

        for (int i = 0; i < dataPoints.length; ++i) {
            dataPoints[i] = new DataPoint(calendar.getTime(), Math.round(data.get(i).getValue()));
            calendar.add(Calendar.DATE, 1);
        }

        graph.removeAllSeries();
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        series.setDrawDataPoints(true);

        if (current) {
            series.setColor(getResources().getColor(R.color.colorPrimary));
            series.setDrawBackground(true);
            mCurrentMonthSeries = series;
        } else {
//            series.setColor(getResources().getColor(R.color.colorAccent));
//            mPrevYearSeries = series;
        }

        graph.addSeries(mCurrentMonthSeries);
        graph.addSeries(mPrevYearSeries);
    }

    private void initGraph() {
        mPresenter.getSensorData(mSensor.getId(), "2017-09-01T00:00:00", "2017-09-30T00:00:00", false);
        mPresenter.getSensorData(mSensor.getId(), "2018-09-01T00:00:00", "2018-09-30T00:00:00", true);

        Calendar calendar = Calendar.getInstance();
        int f = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int l = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - 1;

        calendar.set(Calendar.DAY_OF_MONTH, f);
        Date first = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, l);
        Date last = calendar.getTime();

        graph.getGridLabelRenderer().setNumVerticalLabels(5);
        graph.getGridLabelRenderer().setGridStyle(GridLabelRenderer.GridStyle.NONE);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(graph.getContext()));
        graph.getGridLabelRenderer().setVerticalLabelsAlign(Paint.Align.LEFT);
        graph.getGridLabelRenderer().setHorizontalLabelsAngle(150);
        graph.getGridLabelRenderer().setHumanRounding(true);

        graph.getViewport().setMinX(first.getTime());
        graph.getViewport().setMaxX(last.getTime());
        graph.getViewport().setXAxisBoundsManual(true);
    }

    void setupNavMenu() {
        mNavigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();

                        switch (id) {
                            case R.id.nav_main: {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
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
