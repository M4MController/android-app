package com.m4m.lieroz.m4m_mobile.ui.sensor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.data.network.model.Sensor;
import com.m4m.lieroz.m4m_mobile.data.network.model.SensorDataPeriodResponse;
import com.m4m.lieroz.m4m_mobile.ui.base.BaseActivity;

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

    private void initGraph() {
        mPresenter.getSensorData(mSensor.getId(), "2017-10-01T00:00:00", "2017-10-31T00:00:00", false);
//        mPresenter.getSensorData(mSensor.getId(), "2018-10-01T00:00:00", "2018-10-31T00:00:00", true);

        Calendar calendar = Calendar.getInstance();
        int f = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
//        int l = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//        Date d;

        calendar.set(Calendar.DAY_OF_MONTH, f);
        Date first = calendar.getTime();
        Date last = calendar.getTime();

//        for (int i = 0; i < l; ++i) {
//            last = calendar.getTime();
//            d = calendar.getTime();
//            series.appendData(new DataPoint(d, getRandom(1000, 5000)), true, l);
//            calendar.add(Calendar.DATE, 1);
//        }

        graph.getGridLabelRenderer().setHumanRounding(false);
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(graph.getContext()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(4);

        graph.getViewport().setMinX(first.getTime());
        calendar.add(Calendar.DATE, 30);
        last = calendar.getTime();
        graph.getViewport().setMaxX(last.getTime());
        graph.getViewport().setXAxisBoundsManual(true);
//
//        graph.getViewport().setMinY(0);
//        graph.getViewport().setYAxisBoundsManual(true);
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
        mToolbar.setSubtitle(intent.getStringExtra("address"));
        mSensor = (new Gson()).fromJson(intent.getStringExtra("sensor"), Sensor.class);

        mCompanyNameView.setText(mSensor.getFinance().getServiceCompany().getName());
        mCompanyAccountView.setText(mSensor.getFinance().getServiceCompany().getBankAccountId());
        mCompanyAddressView.setText(mSensor.getFinance().getServiceCompany().getAddress());
        mCompanyPhoneView.setText(mSensor.getFinance().getServiceCompany().getPhone());

        DateFormat sdf = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH);
        mExpensesDateView.setText(String.format("%s: %s", getResources().getString(R.string.expenses_on), sdf.format(new Date())));
        mExpensesSumView.setText(String.format(Locale.ENGLISH, "%.2f %s", mSensor.getPayments().getCharge(), getResources().getString(R.string.currency_format)));

        initGraph();
        super.setUp();
    }

    @Override
    public void update(List<SensorDataPeriodResponse.Data> data, boolean current) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
//        series.setDrawDataPoints(true);
//        series.setDrawBackground(true);

        Calendar calendar = Calendar.getInstance();
        int f = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
//        int l = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//        Date d;

        calendar.set(Calendar.DAY_OF_MONTH, f);
//        Date d;
        DataPoint[] dataPoints = new DataPoint[data.size()];

        for (int i = 0; i < dataPoints.length; ++i) {
            dataPoints[i] = new DataPoint(calendar.getTime(), i);
            calendar.add(Calendar.DATE, 1);
        }

//        if (current) {
//            graph.removeSeries(mCurrentMonthSeries);
//            mCurrentMonthSeries = series;
//        } else {
//            graph.removeSeries(mPrevYearSeries);
//            mPrevYearSeries = series;
//        }

        graph.removeAllSeries();
        graph.addSeries(new LineGraphSeries(dataPoints));
    }
}
