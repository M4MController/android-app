package com.m4m.lieroz.m4m_mobile.ui.sensor;

import com.m4m.lieroz.m4m_mobile.data.network.model.SensorDataPeriodResponse;
import com.m4m.lieroz.m4m_mobile.ui.base.MvpView;

import java.util.List;

public interface SensorMvpView extends MvpView {

    void update(List<SensorDataPeriodResponse.Data> data, boolean current);
}
