package com.m4m.lieroz.m4m_mobile.ui.sensor;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.m4m.lieroz.m4m_mobile.R;
import com.m4m.lieroz.m4m_mobile.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SensorActivity extends BaseActivity implements SensorMvpView {

    @BindView(R.id.graph)
    GraphView graph;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        getActivityComponent().inject(this);
        setUnBinder(ButterKnife.bind(this));

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

        // set date label formatter
        graph.getGridLabelRenderer().setVerticalLabelsVisible(false);
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(graph.getContext()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(2);

        // set manual x bounds to have nice steps
        graph.getViewport().setMinX(first.getTime());
        graph.getViewport().setMaxX(last.getTime());
        graph.getViewport().setXAxisBoundsManual(true);

        // set manual x bounds to have nice steps
        graph.getViewport().setMinY(0);
        graph.getViewport().setYAxisBoundsManual(true);

        // as we use dates as labels, the human rounding to nice readable numbers
        // is not nessecary
        graph.getGridLabelRenderer().setHumanRounding(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void setUp() {

    }

    Random mRand = new Random();

    private double getRandom(double min, double max) {
        return min + (max - min) * mRand.nextDouble();
    }
}
