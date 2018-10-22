package com.m4m.lieroz.m4m_mobile.ui.sensor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.data.network.model.Sensor;
import com.m4m.lieroz.m4m_mobile.ui.base.BaseActivity;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SensorActivity extends BaseActivity implements SensorMvpView {

    @Inject
    SensorMvpPresenter<SensorMvpView> mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));
        setUp();
    }

    private void initGraph() {
        Calendar calendar = Calendar.getInstance();
        int f = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
        int l = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        Date d;
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
        calendar.set(Calendar.DAY_OF_MONTH, f);
        Date first = calendar.getTime();
        Date last = calendar.getTime();

        for (int i = 0; i < l; ++i) {
            last = calendar.getTime();
            d = calendar.getTime();
            series.appendData(new DataPoint(d, getRandom(1000, 5000)), true, l);
            calendar.add(Calendar.DATE, 1);
        }

        series.setDrawDataPoints(true);
        series.setDrawBackground(true);
        graph.addSeries(series);

        graph.getGridLabelRenderer().setHumanRounding(false);
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(graph.getContext()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(4);

        graph.getViewport().setMinX(first.getTime());
        graph.getViewport().setMaxX(last.getTime());
        graph.getViewport().setXAxisBoundsManual(true);

        graph.getViewport().setMinY(0);
        graph.getViewport().setYAxisBoundsManual(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetach();
        super.onDestroy();
    }

    @Override
    protected void setUp() {
        mPresenter.onAttach(this);

        Intent intent = getIntent();
        mToolbar.setTitle(intent.getStringExtra("title"));
        Sensor.Finance.ServiceCompany company = (new Gson()).fromJson(intent.getStringExtra("company"),
                Sensor.Finance.ServiceCompany.class);
        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        mCompanyNameView.setText(company.getName());
        mCompanyAccountView.setText(company.getBankAccountId());
        mCompanyAddressView.setText(company.getAddress());
        mCompanyPhoneView.setText(company.getPhone());

        initGraph();
    }

    Random mRand = new Random();

    private double getRandom(double min, double max) {
        return min + (max - min) * mRand.nextDouble();
    }
}
