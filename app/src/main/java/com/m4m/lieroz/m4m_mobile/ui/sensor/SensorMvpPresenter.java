package com.m4m.lieroz.m4m_mobile.ui.sensor;

import com.m4m.lieroz.m4m_mobile.di.PerActivity;
import com.m4m.lieroz.m4m_mobile.ui.base.MvpPresenter;

@PerActivity
public interface SensorMvpPresenter<V extends SensorMvpView> extends MvpPresenter<V> {

    void getSensorData(int id, String from, String to, boolean current);
}
