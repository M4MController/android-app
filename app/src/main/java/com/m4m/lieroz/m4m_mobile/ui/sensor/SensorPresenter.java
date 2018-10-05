package com.m4m.lieroz.m4m_mobile.ui.sensor;

import com.m4m.lieroz.m4m_mobile.ui.base.BasePresenter;

import javax.inject.Inject;

public class SensorPresenter<V extends SensorMvpView> extends BasePresenter<V> implements SensorMvpPresenter<V> {

    @Inject
    public SensorPresenter() {

    }
}
